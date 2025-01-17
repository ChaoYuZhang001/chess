package cn.gly.auth.security.model.response;

import cn.gly.auth.security.exception.RequestValidationException;

/**
 * 数据效验失败时返回的响应
 */
public class ValidationFailResponse extends BasicResponse {

    public ValidationFailResponse(int code, String message) {
        super(code, message);
    }

    public static ValidationFailResponse from(RequestValidationException e) {
        return new ValidationFailResponse(e.getCode(), e.getMessage());
    }

}
