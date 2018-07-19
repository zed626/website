package com.aiwac.dao;


import java.util.List;

import com.aiwac.model.WeChatScene;

public interface WeChatSceneDao {
    public WeChatScene getScene(WeChatScene scene);
    public WeChatScene getSceneWithId(int sceneId);
    public WeChatScene getSceneByName(WeChatScene scene);
    public List<WeChatScene> getAllScene();
    public List<WeChatScene> getAvailableScene();
    public int addScene(WeChatScene scene);
    public void updateScene(WeChatScene scene);
    public void deleteScene(int sceneId);
    public int getSceneCount();
    public List<WeChatScene> getPageScene(int startrow,int pagesize);
}
