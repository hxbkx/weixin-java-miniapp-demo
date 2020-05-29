package com.github.binarywang.demo.wx.miniapp.vo;

public class Result<T> extends RestResult<T>{
    public static Result of(RestExStatus status) {
        return new Result(status);
    }

    public static Result of(String code, String message) {
        return new Result(code, message);
    }

    public static Result of(String message) {
        return new Result(message);
    }

    public static  <T> Result of(T data) {
        return new Result("查询成功",data);
    }

    public static <T> Result<T> of(String message, T data) {
        return new Result(message, data);
    }

    public Result(String message) {
        this();
        this.setMsg(message);
    }

    public Result(RestExStatus status) {
        this.setSucceed(status.getValue() == 200);
        this.setCode(String.valueOf(status.getValue()));
        this.setMsg(status.getReasonPhrase());
    }

    public Result(RestExStatus status, T data) {
        this.setData(data);
        this.setSucceed(status.getValue() == 200);
        this.setCode(String.valueOf(status.getValue()));
        this.setMsg(status.getReasonPhrase());
    }

    public Result(String code, String message, T data) {
        this.setData(data);
        this.setSucceed(String.valueOf(RestExStatus.SUCCESS.getValue()).equals(code));
        this.setCode(code);
        this.setMsg(message);
    }

    public Result(String message, T data) {
        this.setData(data);
        this.setSucceed(true);
        this.setCode(String.valueOf(RestExStatus.SUCCESS.getValue()));
        this.setMsg(message);
    }

    public Result(String code, String message) {
        this.setSucceed(String.valueOf(RestExStatus.SUCCESS.getValue()).equals(code));
        this.setCode(code);
        this.setMsg(message);
    }

    public Result() {
        this.setSucceed(true);
        this.setCode(String.valueOf(RestExStatus.SUCCESS.getValue()));
        this.setMsg(RestExStatus.SUCCESS.getReasonPhrase());
    }
}
