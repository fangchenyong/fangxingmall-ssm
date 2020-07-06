package com.fangxingmall.common;

/**
 * @ClassName: ResponseCode
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/7/6 21:28
 **/
public enum ResponseCode {

    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGEL_ARGUMNT(2,"ILLEGEL_ARGUMNT");

    private  final  int code;
    private  final  String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
