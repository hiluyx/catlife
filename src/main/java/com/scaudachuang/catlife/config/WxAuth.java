package com.scaudachuang.catlife.config;

/**
 * @author hiluyx
 * @since 2021/7/11 21:13
 **/

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("wx")
@Data
public class WxAuth {
    private String appId;
    private String appSecret;
}
