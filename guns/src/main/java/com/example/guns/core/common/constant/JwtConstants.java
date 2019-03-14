package com.example.guns.core.common.constant;

/**
 * jwt相关配置
 *
 * @author DINGJUN
 * @date 2019.03.14
 */
public interface JwtConstants {
    String AUTH_HEADER = "Authorization";

    String SECRET = "defaultSecret";

    Long EXPIRATION = 604800L;

    String AUTH_PATH = "/gunsApi/auth";

}
