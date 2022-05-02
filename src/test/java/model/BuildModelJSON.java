package model;

import com.google.gson.Gson;

public class BuildModelJSON {
    public static String parseJSONString(POSTBody postBoy){
        if(postBoy==null){
            throw new IllegalArgumentException("Post body cant be null");
        }
        return new Gson().toJson(postBoy);
    }
}
