package com.example.model.enums;

import lombok.Getter;

/**
 * 是或者否的枚举
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
@Getter
public enum YesOrNoEnum {
    YES(true, "是", 1),
    NO(false,"否", 2);

    private Integer code;
    private String desc;
    private Boolean flag;

    YesOrNoEnum(Boolean flag, String desc, Integer code){
        this.flag = flag;
        this.desc = desc;
        this.code = code;
    }

    public static String valueOf(Integer code){
        if(null == code){
            return "";
        } else {
            for (YesOrNoEnum en :
                    YesOrNoEnum.values()) {
                if(en.getCode().equals(code)){
                    return en.getDesc();
                }
            }
            return "";
        }
    }
}
