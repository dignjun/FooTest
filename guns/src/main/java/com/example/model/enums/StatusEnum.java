package com.example.model.enums;

import lombok.Getter;

/**
 * 启用禁用标识
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
@Getter
public enum StatusEnum {

    ENABLE(1,"启用"),
    DISABLE(2,"禁用");

    private Integer code;
    private String desc;

    StatusEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static String getNameByCode(Integer code){
        if(null == code){
            return "";
        } else {
            for (StatusEnum status :
                    StatusEnum.values()) {
                if (status.getCode().equals(code)) {
                    return status.getDesc();
                }
            }
            return "";
        }
    }

    public static StatusEnum toEnum(Integer code){
        if(null == code) {
            return null;
        } else {
            for (StatusEnum status :
                    StatusEnum.values()) {
                if (status.getCode().equals(code)) {
                    return status;
                }
            }
            return null;
        }
    }
}
















