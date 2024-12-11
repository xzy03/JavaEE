package cn.edu.zjut.enums;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    UNKNOWN_EXCEPTION(402,"未知异常"),
    FORBIDDEN(403, "没有相关权限"),

    BUSINESS_EXCEPTION(4000,"业务异常");
    private int code;
    @Getter
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
