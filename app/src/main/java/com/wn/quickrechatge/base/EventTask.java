package com.wn.quickrechatge.base;

/**
 * Created by ds on 2017/2/17.
 */
public  class EventTask<T> {
    private int code;
    private T data;

    public EventTask(T data, int code) {
        this.data = data;
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }
}
