package com.aiwac.dao;

import java.util.List;

import com.aiwac.model.WeChatPicture;

public interface WeChatPictureDao {
    public WeChatPicture getPicture(WeChatPicture picture);
    public List<WeChatPicture> getPictureWithSceneId(int pictureId);
    public void addPicture(WeChatPicture picture);
    public void updatePicture(WeChatPicture picture);
    public void deletePicture(int pictureId);
}
