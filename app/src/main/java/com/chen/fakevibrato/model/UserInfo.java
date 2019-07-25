package com.chen.fakevibrato.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Created by CHEN on 2019/7/24
 * @email 188669@163.com
 *
 * 用户详情
 */
public class UserInfo {
    /**
     * id
     */
    private String id;
    /**
     * 头像
     */
    private String portraitUri;
    /**
     * 名称
     */
    private String name;
    /**
     * 电话
     */
    private String phoneNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
