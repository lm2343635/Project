package com.xwkj.project.component.config;

import net.sf.json.JSONObject;
import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class Global {

    public String name;
    public String [] attributes;
    public String content;

    public Global(JSONObject object) {
        this.attributes = object.getString("attributes").split("/");
        this.name = object.getString("name");
        this.content = object.getString("content");
    }

}
