package com.example.kotvitz.michalkotfica;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class ElementInfo {

    private String title;
    private String desc;
    private String url;

    private ElementInfo(String title, String desc, String url) {
        this.title = title;
        this.desc = desc;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static ArrayList<ElementInfo> createElementsList(String jsonString) {
        ArrayList<ElementInfo> elements = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray elementsArray = object.getJSONArray("array");

            for (int i = 0; i < elementsArray.length(); i++) {
                elements.add(new ElementInfo(elementsArray.getJSONObject(i).getString("title"),
                        elementsArray.getJSONObject(i).getString("desc"),
                        elementsArray.getJSONObject(i).getString("url")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return elements;
    }
}
