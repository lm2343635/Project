package com.xwkj.project.component;

import com.xwkj.common.util.JsonTool;
import com.xwkj.project.component.config.Global;
import org.springframework.stereotype.Component;

@Component
public class ConfigComponent {

    public static final String ConfigPath = "/WEB-INF/config.json";

    public String rootPath;
    public JsonTool configTool = null;
    public Global global;


    public ConfigComponent() {
        rootPath = this.getClass().getClassLoader().getResource("/").getPath().split("WEB-INF")[0];
        load();
    }

    public void load() {
        String pathname = rootPath + ConfigPath;
        configTool = new JsonTool(pathname);
        global = new Global(configTool.getJSONObject("global"));
    }

}
