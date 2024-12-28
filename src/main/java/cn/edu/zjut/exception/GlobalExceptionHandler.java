package cn.edu.zjut.exception;

import cn.edu.zjut.entity.resp.CommonResult;
import cn.edu.zjut.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

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
    // 处理验证失败的异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 提取所有的验证错误信息
        List<String> errorMessages = ex.getBindingResult().getAllErrors().stream()
                .map(objectError -> {
                    // 获取字段错误信息
                    if (objectError instanceof FieldError) {
                        FieldError fieldError = (FieldError) objectError;
                        return fieldError.getField() + ": " + fieldError.getDefaultMessage();
                    }
                    return objectError.getDefaultMessage();
                })
                .collect(Collectors.toList());

        // 返回验证错误信息
        return CommonResult.any(ResultCode.INVALID_PARAM, "验证失败", errorMessages);
    }
}
