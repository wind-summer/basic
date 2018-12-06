package com.basic.core.utils;

import java.util.Collection;

/**
 * <p>
 *
 * </p>
 *
 * @author wenlongfei
 * @since 2018/12/5
 */
public class Util {

    /**
     * 判断集合是否为空
     * @param collection
     * @return
     */
    public static Boolean isEmpty(Collection<?> collection){
        return (collection == null || collection.isEmpty()) ? true : false;
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static Boolean isEmpty(String str){
        return (str == null || "".equals(str)) ? true : false;
    }
}
