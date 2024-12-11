package cn.edu.zjut.exception;

import cn.edu.zjut.enums.ResultCode;

public class ApiException extends RuntimeException {
    private final ResultCode resultCode;

    public ApiException(String message, ResultCode resultCode) {
        super(message);
        this.resultCode = resultCode;
    }

    public ApiException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public ApiException(String message, Throwable ex) {
        super(message, ex);
        this.resultCode= ResultCode.UNKNOWN_EXCEPTION;
    }
}
