package cn.edu.zjut.exception;

import cn.edu.zjut.entity.resp.CommonResult;
import cn.edu.zjut.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ApiException.class})
    public CommonResult<String> handleApiException(ApiException e) {
        return CommonResult.error(e.getResultCode().getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {Exception.class})
    public CommonResult<String> handleException(Exception e) {
        // 记录异常日志
        log.info("根异常发生了: ", e);

        // 返回适当的HTTP响应
        return CommonResult.any(ResultCode.UNKNOWN_EXCEPTION,"未知异常",null);
    }
}
