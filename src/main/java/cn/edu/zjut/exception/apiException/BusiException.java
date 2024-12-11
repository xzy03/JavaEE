package cn.edu.zjut.exception.apiException;

import cn.edu.zjut.enums.ResultCode;
import cn.edu.zjut.exception.ApiException;

import java.io.Serializable;


public class BusiException extends ApiException implements Serializable {
    public BusiException(){
        super(null, ResultCode.BUSINESS_EXCEPTION);

    }
    public BusiException(String msg, ResultCode resultCode) {
        super(msg,resultCode);
    }

    public BusiException(String msg) {
        super(msg, ResultCode.BUSINESS_EXCEPTION);
    }


    public BusiException(ResultCode resultCode) {
        super(resultCode.getMessage(),resultCode);
    }
}
