package com.basic.core.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenlongfei
 * @since 2018/10/23
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 500);
        map.put("msg", "系统错误");
        return map;
    }

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map errorHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors != null && fieldErrors.size() > 0){
            errorMessage.append(fieldErrors.get(0).getDefaultMessage());
        }else{
            errorMessage.append("系统错误");
        }
        Map map = new HashMap();
        map.put("code", 500);
        map.put("msg", errorMessage.toString());
        return map;
    }

    /**
     * 拦截捕捉自定义异常 BizException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = com.my.common.exception.BizException.class)
    public Map myErrorHandler(com.my.common.exception.BizException ex) {
        Map map = new HashMap();
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        return map;
    }
}
