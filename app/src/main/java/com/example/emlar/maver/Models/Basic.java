package com.example.emlar.maver.Models;

public class Basic <T> {
    private String status;
    private int code;
    private T data;

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }


}
