package com.basic.admin.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
}
