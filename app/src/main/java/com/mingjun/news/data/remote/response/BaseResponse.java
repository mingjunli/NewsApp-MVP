package com.mingjun.news.data.remote.response;

/**
 * Created by mingjun on 16/6/28.
 */
public class BaseResponse<T> {

    public int code;
    public T data;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
