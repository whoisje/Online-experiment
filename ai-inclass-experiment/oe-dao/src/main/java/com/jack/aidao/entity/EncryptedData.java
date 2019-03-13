package com.jack.aidao.entity;

import lombok.Data;

/**
 * @author wangwj
 * @data 2018/12/22
 */
@Data
public class EncryptedData implements BaseEntity{

    private String encryptedData;
    private String code;
    private String iv;
}
