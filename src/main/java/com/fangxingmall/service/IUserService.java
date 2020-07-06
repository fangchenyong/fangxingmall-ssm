package com.fangxingmall.service;

import com.fangxingmall.common.ServerResponse;
import com.fangxingmall.pojo.User;

public interface IUserService {
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    ServerResponse<User> login(String username, String password);

    /**
     * 用户注册
     * @param user
     * @return
     */
    ServerResponse<String> register(User user);

    /**
     * 用户校验
     * @param str
     * @param type
     * @return
     */
    ServerResponse<String> checkValid(String str, String type);

    /**
     * 问题查询
     * @param username
     * @return
     */
    ServerResponse selectQuestion(String username);

    /**
     * 检查答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    ServerResponse<String> checkAnswer(String username,String question,String answer);

    /**
     * 重置密码
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);

    /**
     * 修改密码
     * @param passwordOld
     * @param passwordNew
     * @param user
     * @return
     */
    ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    ServerResponse<User> updateInformation(User user);

    /**
     * 获得用户信息
     * @param userId
     * @return
     */
    ServerResponse<User> getInformation(Integer userId);

    /**
     * 是否为管理员
     * @param user
     * @return
     */
    ServerResponse checkAdminRole(User user);
}
