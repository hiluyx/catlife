package com.scaudachuang.catlife.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hiluyx
 * @since 2021/7/11 21:15
 **/
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Resource
    private LoginInterceptor loginHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandlerInterceptor)
                .addPathPatterns("/**") // 拦截路径
                .excludePathPatterns(
                        "/**/wxLogin"
                ); // 不拦截
    }
}
