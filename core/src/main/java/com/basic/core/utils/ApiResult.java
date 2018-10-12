package com.my.common.utils;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台返回数据
 * @author wenlf
 * @since 2018/4/26
 */
public class ApiResult extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;

    public ApiResult() {
        put("code", 0);
        put("msg","操作成功");
    }

    public static ApiResult error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
    }

    public static ApiResult error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static ApiResult error(int code, String msg) {
        ApiResult apiResult = new ApiResult();
        apiResult.put("code", code);
        apiResult.put("msg", msg);
        return apiResult;
    }

    public static ApiResult ok(String msg) {
        ApiResult apiResult = new ApiResult();
        apiResult.put("msg", msg);
        return apiResult;
    }

    public static ApiResult ok(Map<String, Object> map) {
        ApiResult apiResult = new ApiResult();
        apiResult.putAll(map);
        return apiResult;
    }

    public static ApiResult ok(Object data){
        ApiResult apiResult = new ApiResult();
        apiResult.put("data",data);
        return apiResult;
    }

    public static ApiResult ok() {
        return new ApiResult();
    }

    @Override
    public ApiResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
