package com.basic.admin.module.sys;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  主页管理
 * </p>
 *
 * @author wenlongfei
 * @since 2019/5/13
 */
@RestController
@RequestMapping("/sys/main")
@AllArgsConstructor
@Api(description = "菜单管理")
public class MainController {

    @ApiOperation("图标信息数据")
    @GetMapping("/dashboard")
    public JSONObject dashboard(){
        JSONObject jsonObject = new JSONObject();

        JSONObject basicAreaChartData = new JSONObject();
        basicAreaChartData.put("title","上周收入折线图(单位/元)");
        basicAreaChartData.put("itemNames", new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"});
        Random ran1 = new Random();
        List<Integer> itemDatas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            itemDatas.add(ran1.nextInt(10000));
        }
        basicAreaChartData.put("items", itemDatas);

        JSONObject pieChartData = new JSONObject();
        List<Integer> itemData1s = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            itemData1s.add(ran1.nextInt(10000));
        }
        pieChartData.put("items", itemData1s);


        jsonObject.put("pieChartData", pieChartData);
        jsonObject.put("basicAreaChartData", basicAreaChartData);
        return jsonObject;
    }
}
