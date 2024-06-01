package com.simple.cloud.utils;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Charles
 * @create 2024-04-01-14:04
 */

@Data
@Accessors(chain = true)
public class ResultData<T> implements Serializable {
    private String code;
    private String message;
    private T data;
    //记录当前时间戳
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T>     success(T data){
        ResultData<T> rd = new ResultData<>();

        rd.setCode(ResultCodeEnum.RC200.getCode());
        rd.setMessage(ResultCodeEnum.RC200.getMessage());
        rd.setData(data);

        return rd;
    }

    public static <T> ResultData<T> fail(String code, String message){
        ResultData<T> rd = new ResultData<>();

        rd.setCode(code);
        ResultCodeEnum definedMessage = ResultCodeEnum.getResultCodeEnumByCode(code);

        rd.setMessage(message != null?
                message : (definedMessage == null ? null : definedMessage.getMessage()));

        return rd;
    }

}
