package com.basic.admin.module.sys;

import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.module.sys.entity.request.SysLogin;
import com.basic.core.module.sys.service.SysUserService;
import com.basic.core.module.sys.service.SysUserTokenService;
import com.basic.core.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登陆控制器
 * Created by wenlf on 2018/10/15
 */
@RestController
@RequestMapping("/sys/login")
@AllArgsConstructor
@Api(description = "登陆管理")
public class SysLoginController {

    private SysUserService sysUserService;
    private SysUserTokenService sysUserTokenService;

    /**
     * 用户登陆操作
     * @param login
     * @return
     */
    @ApiOperation("登陆")
    @PostMapping("/sign_in")
    public ApiResult login(@RequestBody SysLogin login) {
        /*String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!captcha.equalsIgnoreCase(kaptcha)){
            return ApiResult.error("验证码不正确");
        }*/

        //用户信息
        SysUser user = sysUserService.queryByUserName(login.getUsername());

        //账号不存在、密码错误
        if(user == null || !user.getPassword().equals(new Sha256Hash(login.getPassword(), user.getSalt()).toHex())) {
            return ApiResult.error("账号或密码不正确");
        }

        //账号锁定
        if(user.getStatus() == 0){
            return ApiResult.error("账号已被锁定,请联系管理员");
        }

        //生成token，并保存到数据库
        ApiResult r = sysUserTokenService.createToken(user.getId());
        return r;
    }

    /**
     * 用户登陆操作
     * @param login
     * @return
     */
    @ApiOperation("登陆")
    @PostMapping("/sign_out")
    public ApiResult logOut(@RequestBody SysLogin login) {

        return ApiResult.ok();
    }
}
