package cn.doug.system.controller.auth;

import cn.doug.common.plugin.annotation.WebLog;
import cn.doug.common.result.Result;
import cn.doug.system.model.dto.SysCaptchaResult;
import cn.doug.system.model.dto.LoginResult;
import cn.doug.system.service.SysAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Tag(name = "01.认证中心")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthController {

    private final SysAuthService authService;

    @WebLog(value = "登录")
    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result<LoginResult> login(
            @Parameter(description = "用户名", example = "admin") @RequestParam String username,
            @Parameter(description = "密码", example = "123456") @RequestParam String password
    ) {
        LoginResult loginResult = authService.login(username, password);
        return Result.success(loginResult);
    }

    @WebLog(value = "注销")
    @Operation(summary = "注销")
    @DeleteMapping("/logout")
    public Result logout() {
        authService.logout();
        return Result.success();
    }

    @WebLog(value = "获取验证码")
    @Operation(summary = "获取验证码")
    @GetMapping("/captcha")
    public Result<SysCaptchaResult> getCaptcha() {
        SysCaptchaResult captcha = authService.getCaptcha();
        return Result.success(captcha);
    }

}
