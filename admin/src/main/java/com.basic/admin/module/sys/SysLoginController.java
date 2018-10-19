package com.basic.admin.module.sys;

import com.basic.core.module.sys.entity.SysUser;
import com.basic.core.module.sys.entity.request.SysLogin;
import com.basic.core.module.sys.service.SysUserService;
import com.basic.core.module.sys.service.SysUserTokenService;
import com.basic.core.utils.ApiResult;
import com.basic.core.utils.ShiroUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登陆控制器
 * Created by wenlf on 2018/10/15
 */
@RestController
@RequestMapping("/sys/login")
@AllArgsConstructor
@Api(description = "登陆管理")
public class SysLoginController {

    private Producer producer;
    private SysUserService sysUserService;
    private SysUserTokenService sysUserTokenService;

    @ApiOperation(value = "获取验证码图片-接口")
    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response) throws ServletException,IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

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
        return r.put("msg","登录成功").put("user",user);
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
