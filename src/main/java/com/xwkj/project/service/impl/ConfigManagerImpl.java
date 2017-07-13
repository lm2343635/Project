package com.xwkj.project.service.impl;

import com.xwkj.project.component.config.Global;
import com.xwkj.project.service.ConfigManager;
import com.xwkj.project.service.common.ManagerTemplate;
import net.sf.json.JSONObject;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RemoteProxy(name = "ConfigManager")
public class ConfigManagerImpl extends ManagerTemplate implements ConfigManager {

    @RemoteMethod
    public boolean refreshConfig(HttpSession session) {
        if (!checkAdminSession(session)) {
            return false;
        }
        configComponent.load();
        return true;
    }

    @RemoteMethod
    public JSONObject getConfigObject(HttpSession session) {
        if (!checkAdminSession(session)) {
            return null;
        }
        return configComponent.configTool.getJSONConfig();
    }

    @RemoteMethod
    public boolean saveConfig(String jsonString, HttpSession session) {
        if (!checkAdminSession(session)) {
            return false;
        }
        configComponent.configTool.setJSONConfig(JSONObject.fromObject(jsonString));
        configComponent.configTool.writeObject();
        return true;
    }

    @RemoteMethod
    public Global getGlobal() {
        return configComponent.global;
    }

}
