package com.aiwac.dao;

import com.aiwac.model.WeChatBusinessSql;

public interface WeChatBusinessDao {
    public WeChatBusinessSql getbusiness(WeChatBusinessSql business);
    public WeChatBusinessSql getbusinessWithId(int businessId);
    public void addBusiness(WeChatBusinessSql business);
    public void deleteBusiness(int businessId);
}
