package cn.doug.system.service;

import cn.doug.system.model.dto.SysCaptchaResult;
import cn.doug.system.model.dto.LoginResult;

/**
 * 认证服务接口
 *
 * @author pengqihai
 * @since 2.4.0
 */
public interface SysAuthService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    LoginResult login(String username, String password);

    /**
     * 登出
     */
    void logout();

    /**
     * 获取验证码
     *
     * @return 验证码
     */
    SysCaptchaResult getCaptcha();
}
