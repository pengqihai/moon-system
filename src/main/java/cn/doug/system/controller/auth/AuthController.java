package cn.doug.system.controller.auth;

import cn.doug.common.annotation.WebLog;
import cn.doug.common.result.vo.ResultVO;
import cn.doug.system.model.dto.CaptchaResult;
import cn.doug.system.model.dto.LoginResult;
import cn.doug.system.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "01.认证中心")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @WebLog(value = "登录")
    @Operation(summary = "登录")
    @PostMapping("/login")
    public ResultVO<LoginResult> login(
            @Parameter(description = "用户名", example = "admin") @RequestParam String username,
            @Parameter(description = "密码", example = "123456") @RequestParam String password
    ) {
        LoginResult loginResult = authService.login(username, password);
        return ResultVO.success(loginResult);
    }

    @WebLog(value = "注销")
    @Operation(summary = "注销")
    @DeleteMapping("/logout")
    public ResultVO logout() {
        authService.logout();
        return ResultVO.success();
    }

    @WebLog(value = "获取验证码")
    @Operation(summary = "获取验证码")
    @GetMapping("/captcha")
    public ResultVO<CaptchaResult> getCaptcha() {
        CaptchaResult captcha = authService.getCaptcha();
        return ResultVO.success(captcha);
    }

}
