package com.github.binarywang.demo.wx.miniapp.vo;

import lombok.Data;

@Data
public class EncryptedDataVo {

    String encryptedData;
    String iv;
    String sessionKey;
}
