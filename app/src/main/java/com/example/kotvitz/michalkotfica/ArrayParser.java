package com.example.kotvitz.michalkotfica;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class ArrayParser {

    private final ArrayList<ElementInfo> elements = new ArrayList<>();

    public ArrayList<ElementInfo> parseElementsArray(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray elementsArray = object.getJSONArray(AppConstant.JSON_ARRAY_NAME);

            for (int i = 0; i < elementsArray.length(); i++) {
                elements.add(new ElementInfo(elementsArray.getJSONObject(i).getString(AppConstant.JSON_ELEMENT_TITLE),
                        elementsArray.getJSONObject(i).getString(AppConstant.JSON_ELEMENT_DESCRIPTION),
                        elementsArray.getJSONObject(i).getString(AppConstant.JSON_ELEMENT_IMAGE_URL)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return elements;
    }
}
