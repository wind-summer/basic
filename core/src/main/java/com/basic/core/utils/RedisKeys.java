package com.basic.core.utils;

/**
 * Redis所有Keys
 *
 * @author wenlongfei
 *
 * @date 2019-04-25 19:51
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

    public static String getSmsVerificationKey(String key){
        return "sms:verification:"+key;
    }

    public static String getCustomerRedisKey(){
        return "CUSTOMER_BROWSE_KEY";
    }

    public static String getErrorLoginRedisKey(){
        return "ERROR_LOGIN_KEY";
    }

    public static String getProhibitLoginRedisKey(){
        return "PROHIBIT_LOGIN_CUSTOMER";
    }

    public static long getErrorLongTime(){
        return 60 * 60 * 24;
    }

    public static long getProhibitLoginTime(){
        return 60 * 60 * 1;
    }
}
