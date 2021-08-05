package com.scaudachuang.catlife.model.wx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginParams {
    private String code;
    private String iv;
    private String encryptedData;
}