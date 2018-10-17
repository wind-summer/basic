package com.basic.core.mvc.filter;

import com.basic.core.module.sys.entity.SysUserToken;
import com.basic.core.module.sys.service.SysUserService;
import com.basic.core.module.sys.service.SysUserTokenService;
import com.basic.core.utils.CurrentUserUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据token获取用户登陆信息
 * Created by wenlf on 2018/10/17
 */
@Component
@Slf4j
@AllArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
    private final SysUserTokenService sysUserTokenService;

    private final SysUserService sysUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //获取请求token，如果token存在，获取用户登陆信息，除去登录url
        String token = getRequestToken(request);
        if(!StringUtils.isEmpty(token)) {
            //查询token信息
            SysUserToken sysUserToken = sysUserTokenService.queryByToken(token);
            if (sysUserToken != null && sysUserToken.getExpireTime().getTime() > System.currentTimeMillis()) {
                //设置loginId到request里，后续根据loginId，获取用户登陆信息
                request.setAttribute(CurrentUserUtils.LOGIN_ID, sysUserToken.getUserId());
                CurrentUserUtils.setLogin(sysUserService.selectById(sysUserToken.getUserId()));
                log.info("Current Login User:{}", CurrentUserUtils.getLogin());
            }

        }
        super.doFilter(request,response,filterChain);

    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest){
        //从header中获取token
        String token = httpRequest.getHeader("token");

        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = httpRequest.getParameter("token");
        }

        return token;
    }
}
