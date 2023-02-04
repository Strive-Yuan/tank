package com.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.api.configuration.CorsConfigConstant.*;

/**
 * 解决跨域的过滤器
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 添加映射路径
        registry
                .addMapping(ADD_MAPPING)
                // 是否发送Cookie
                .allowCredentials(ALLOW_CREDENTIALS)
                // 设置放行哪些原始域   SpringBoot2.4.4下低版本使用.allowedOrigins("*")
                .allowedOriginPatterns(ALLOWED_ORIGIN_PATTERNS)
                // 放行哪些请求方式
                .allowedMethods(ALLOWED_METHODS_GET, ALLOWED_METHODS_POST, ALLOWED_METHODS_PUT, ALLOWED_METHODS_DELETE)
                // 或者放行全部
                .allowedMethods(ALLOW_ALL)
                // 放行哪些原始请求头部信息
                .allowedHeaders(ALLOW_ALL)
                // 暴露哪些原始请求头部信息
                .exposedHeaders(ALLOW_ALL);
    }
}
