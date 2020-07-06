package com.fangxingmall.common;

/**
 * @ClassName: Const
 * @Description: TODO
 * @Author: 10136
 * @Date: 2020/7/6 22:02
 **/
public class Const {

    public static final String CURRENT_USER ="currentUser";

    public  static final String EMAIL = "email";
    public  static final String USERNAME = "username";

    public interface Role{
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;
    }
}
