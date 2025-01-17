package cn.gly.auth.security.model.response;


import cn.gly.auth.security.exception.SecurityAuthException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;

/**
 * 认证失败时返回给前台的响应
 */
public class AuthenticationFailResponse extends BasicResponse {


    public AuthenticationFailResponse(int code, String message) {
        super(code, message);
    }

    public static AuthenticationFailResponse asResponse(AuthenticationException e) {
        AuthenticationFailResponse unauthorized;

        if (e instanceof InternalAuthenticationServiceException) {
            e = (AuthenticationException) e.getCause();
        }

        if (e instanceof SecurityAuthException) {
            SecurityAuthException ex = (SecurityAuthException) e;
            unauthorized = new AuthenticationFailResponse(ex.getCode(), ex.getMessage());

        } else if (e instanceof DisabledException) {
            unauthorized = new AuthenticationFailResponse(401, "User is disabled!");

        } else if (e instanceof BadCredentialsException) {
            unauthorized = new AuthenticationFailResponse(401, "Wrong credentials!");

        } else {
            unauthorized = new AuthenticationFailResponse(401, "Unauthorized");
        }

        return unauthorized;
    }

}
