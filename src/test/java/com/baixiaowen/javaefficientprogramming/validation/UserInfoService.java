package com.baixiaowen.javaefficientprogramming.validation;

import javax.validation.Valid;

/**
 * 用户信息服务类
 */
public class UserInfoService {

    /**
     * UserInfo 作为输入参数
     * @param userInfo
     */
    public void setUserInfo(@Valid UserInfo userInfo){}

    /**
     * UserInfo 作为输出参数
     * @return userInfo
     */
    public @Valid UserInfo getUserInfo(){
        return new UserInfo();
    }

    // 默认构造函数
    public UserInfoService(){}

    // 接收UserInfo作为参数的构造函数
    public UserInfoService(@Valid UserInfo userInfo){}
}