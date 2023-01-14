package com.api.configuration;

/**
 * 设置解决跨域问题的常量
 */
public class CorsConfigConstant {

    public static final String ADD_MAPPING = "/api/**";

    public static final boolean ALLOW_CREDENTIALS = true;

    /**
     * 配置允许访问的域名
     *
     * <ul>
     *   <li>https://*.domain1.com -- 以domain1.com域名结尾
     *   <li>https://*.domain1.com:[8080,8081] -- 以domain1.com结尾的端口号为8080或8081
     *   <li>https://*.domain1.com:[*] -- 以domain1.com结尾的任意端口号
     * </ul>
     */
    public static final String ALLOWED_ORIGIN_PATTERNS = "http://*:[*]";

    public static final String ALLOWED_METHODS_GET = "GET";

    public static final String ALLOWED_METHODS_POST = "POST";

    public static final String ALLOWED_METHODS_PUT = "PUT";

    public static final String ALLOWED_METHODS_DELETE = "DELETE";

    public static final String ALLOW_ALL = "*";
}
