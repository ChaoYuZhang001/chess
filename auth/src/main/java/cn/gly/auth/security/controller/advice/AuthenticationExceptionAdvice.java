package cn.gly.auth.security.controller.advice;

import cn.gly.auth.security.exception.RequestValidationException;
import cn.gly.auth.security.exception.SecurityAuthException;
import cn.gly.auth.security.model.response.AuthenticationFailResponse;
import cn.gly.auth.security.model.response.ValidationFailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class AuthenticationExceptionAdvice {


    /**
     * 捕获到的异常 SecurityAuthException 不抛出报错,而是返回 AuthenticationFailResponse
     *
     * @param e SecurityAuthException
     * @return SecurityAuthException json
     */
    @ExceptionHandler({SecurityAuthException.class})
    public ResponseEntity<?> handleSecurityAuthException(SecurityAuthException e) {
        return ResponseEntity.ok().body(AuthenticationFailResponse.asResponse(e));
    }

    @ExceptionHandler({RequestValidationException.class})
    public ResponseEntity<?> handleRequestValidationException(RequestValidationException e) {
        return ResponseEntity.ok().body(ValidationFailResponse.from(e));
    }
}
