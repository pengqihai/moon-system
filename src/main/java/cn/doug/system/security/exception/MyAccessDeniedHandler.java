package cn.doug.system.security.exception;

import cn.doug.system.common.result.ResultCode;
import cn.doug.system.common.util.ResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Spring Security访问异常处理器
 *
 * @author pengqihai
 * @since 2022/10/18
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        ResponseUtils.writeErrMsg(response, ResultCode.ACCESS_UNAUTHORIZED);
    }
}
