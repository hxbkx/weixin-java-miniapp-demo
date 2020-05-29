package com.github.binarywang.demo.wx.miniapp.vo;

import lombok.Data;

@Data
public class RestResult<T> {
    private String msg;
    private Boolean succeed = true;
    private T data;
    private String code = "200";
    private String requestId;
    
}

