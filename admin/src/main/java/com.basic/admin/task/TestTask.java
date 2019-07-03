package com.basic.admin.task;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.core.module.sys.entity.SysConfig;
import com.basic.core.module.sys.entity.response.DictionaryDTO;
import com.basic.core.module.sys.service.SysConfigService;
import com.basic.core.utils.HttpClientUtil;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author wenlongfei
 * @since 2019/5/7
 */
@Component("testTask")
@Slf4j
public class TestTask {
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    JavaMailSender jms;

    public void test(){
        log.info("我是带参数的test方法，正在被执行，参数为：");

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //SysUser user = sysUserService.queryObject(1L);
        //System.out.println(ToStringBuilder.reflectionToString(user));

    }

    /**
     * 温龙飞下班那打卡
     */
    public void wenlfEndWork(){
        Long sleepMi = RandomUtil.randomLong(1,6000);
        try {
            Thread.sleep(sleepMi);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SysConfig config = sysConfigService.getSysConfigByCode("wenlf");
        if(config == null) {
            return;
        }
        String value = config.getValue();
        JSONObject jsonObject = JSONObject.parseObject(value);
        String url = jsonObject.getString("url");
        JSONObject header = jsonObject.getJSONObject("header");
        JSONObject params = jsonObject.getJSONObject("params");
        params.put("Entity.WorkDate", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        String result = HttpClientUtil.doPostNoParams(url, null, params, header);
        log.info("打印："+result);
        if(result.contains("Login")){
            //说明登录失败，发送邮件
            sendEmail("打卡失败。","下班打卡");
        }
    }

    /**
     * 温龙飞上班那打卡
     */
    public void wenlfStartWork(){
        Long sleepMi = RandomUtil.randomLong(1,100000);
        try {
            Thread.sleep(sleepMi);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SysConfig config = sysConfigService.getSysConfigByCode("wenlf");
        if(config == null) {
            return;
        }
        String value = config.getValue();
        JSONObject jsonObject = JSONObject.parseObject(value);
        JSONObject header = jsonObject.getJSONObject("header");
        JSONObject search = jsonObject.getJSONObject("search");
        String dateId = searchData(header,search);
        log.info("返回结果：{}", dateId);

        if(dateId.length()>0){
            JSONObject clockIn = jsonObject.getJSONObject("clockIn");
            clockIn(header, clockIn, dateId);
        }

    }

    /**
     * 查询当天的打卡日期的id
     * @param header
     * @param search
     */
    public String searchData(JSONObject header, JSONObject search){
        String id = "";
        String url = search.getString("url");
        JSONObject params = search.getJSONObject("params");
        String result = HttpClientUtil.doPostNoParams(url, null, params, header);
        if(result.contains("Login")){
            //说明登录失败，发送邮件
            sendEmail("查询当日打卡数据失败。","打卡");
        }
        JSONObject resultJson= JSONObject.parseObject(result);
        JSONArray listData = resultJson.getJSONArray("ListData");
        if(listData.isEmpty()){
            sendEmail("当日没有数据。","打卡");
        }
        JSONObject today = listData.getJSONObject(0);
        id = today.getString("ID");
        sendEmail("当日json数据：\n"+today.toJSONString(),"打卡");
        return id;

    }

    /**
     * 打卡
     * @param header
     * @param clockIn
     * @param dateId
     */
    public void clockIn(JSONObject header, JSONObject clockIn, String dateId){
        String url = clockIn.getString("url");
        JSONObject params = clockIn.getJSONObject("params");
        params.put("Entity.ID", dateId);
        params.put("Entity.WorkDate", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        String result = HttpClientUtil.doPostNoParams(url, null, params, header);
        if(result.contains("Login")){
            //说明登录失败，发送邮件
            sendEmail("打卡调用接口失败。","打卡");
        }else if(result.contains("签到成功")){
            sendEmail("今日打卡成功。","打卡");
        }
    }

    public void sendEmail(String content, String subject){
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom("wenlongfeia@163.com");
        //接收者
        mainMessage.setTo("wenlf@litsoft.com.cn");
        //发送的标题
        mainMessage.setSubject(subject);
        //发送的内容
        mainMessage.setText(content);
        jms.send(mainMessage);
    }

}
