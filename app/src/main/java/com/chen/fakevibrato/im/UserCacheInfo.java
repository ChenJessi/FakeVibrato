package com.chen.fakevibrato.im;

import com.chen.fakevibrato.model.UserInfo;

/**
 * @author Created by CHEN on 2019/7/24
 * @email 188669@163.com
 * userinfo 缓存类
 */
public class UserCacheInfo extends UserInfo {

    private String loginToken;
    private String password;

    public UserCacheInfo(){
    }

    public UserCacheInfo(String id) {
        setId(id);
    }

    public UserCacheInfo(String id, String loginToken, String phoneNumber, String password, String region) {
        setId(id);
        setPhoneNumber(phoneNumber);
        setLoginToken(loginToken);
        setPassword(password);
    }

    public void setUserInfo(UserInfo info) {
        if (getId() != null && info != null && !getId().equals(info.getId())) {
            return;
        }
        setId(info.getId());
        setPortraitUri(info.getPortraitUri());
        setName(info.getName());
        setPhoneNumber(info.getPhoneNumber());

    }

    public void setUserCacheInfo(UserCacheInfo info) {
        setId(info.getId());
        setPortraitUri(info.getPortraitUri());
        setName(info.getName());
        setPhoneNumber(info.getPhoneNumber());
        setLoginToken(info.getLoginToken());
        setPassword(info.getPassword());
    }



    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
