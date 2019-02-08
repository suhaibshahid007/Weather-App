package Util;

import org.json.JSONObject;

/**
 * Created by Muhammad Suhaib on 8/10/2018.
 */

public class Utils {

    // here the static variable contains the url api of weather ...
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String ICON_URL = "http://openweathermap.org/img/w/";

    // here the method to get the json object ...
    public static  JSONObject getObject(String tagName , JSONObject jsonObject) throws Exception {

        JSONObject jsonObject1 =  jsonObject.getJSONObject(tagName);
        return  jsonObject1;
    }
    // here the method to get the string from the json object ..
    public static  String getString(String tagName , JSONObject jsonObject) throws Exception{

            return  jsonObject.getString(tagName);
    }
    // here the method to get the float  from the json object ..
    public static  float getFloat(String tagName , JSONObject jsonObject) throws Exception{

        return  (float)jsonObject.getDouble(tagName);
    }

    public static  int getInt(String tagName , JSONObject jsonObject) throws Exception{

        return  jsonObject.getInt(tagName);
    }

    public static double getDouble(String tagName , JSONObject jsonObject) throws Exception
    {
        return  jsonObject.getDouble(tagName);
    }
}