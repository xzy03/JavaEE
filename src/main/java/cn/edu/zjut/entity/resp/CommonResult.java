package cn.edu.zjut.entity.resp;


import cn.edu.zjut.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 创建成功响应，默认消息为"操作成功"
     *
     * @param data 响应数据
     * @param <T> 响应数据类型
     * @return 成功的CommonResult实例
     */
    public static <T> CommonResult<T> success(T data) {
        return success(data, "操作成功");
    }

    /**
     * 创建成功响应，自定义消息
     *
     * @param data 响应数据
     * @param message 自定义消息
     * @param <T> 响应数据类型
     * @return 成功的CommonResult实例
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return CommonResult.<T>builder()
                .code(200)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * 创建错误响应
     *
     * @param code 错误状态码
     * @param message 错误消息
     * @param <T> 响应数据类型
     * @return 错误的CommonResult实例
     */
    public static <T> CommonResult<T> error(int code, String message) {
        return CommonResult.<T>builder()
                .code(code)
                .message(message)
                .build();
    }

    /**
     * 创建错误响应，默认状态码为500
     *
     * @param message 错误消息
     * @param <T> 响应数据类型
     * @return 错误的CommonResult实例
     */
    public static <T> CommonResult<T> error(String message) {
        return error(500, message);
    }

    /**
     * 创建自定义响应
     *
     * @param code 状态码
     * @param message 响应消息
     * @param data 响应数据
     * @param <T> 响应数据类型
     * @return 自定义的CommonResult实例
     */
    public static <T> CommonResult<T> any(int code, String message, T data) {
        return CommonResult.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
    public static <T> CommonResult<T> any(ResultCode resultCode, String message, T data) {
        return any(resultCode.getCode(), message, data);
    }
}

