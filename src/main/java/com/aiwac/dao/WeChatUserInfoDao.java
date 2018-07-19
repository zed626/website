package com.aiwac.dao;

import com.aiwac.model.WeChatUserInfo;

public interface WeChatUserInfoDao {
    public WeChatUserInfo getUser(WeChatUserInfo user);
    public WeChatUserInfo getUserWithId(WeChatUserInfo user);
    public void addUser(WeChatUserInfo user);
    public void updateUser(WeChatUserInfo user);
    public void deleteUser(String UserId);
}
