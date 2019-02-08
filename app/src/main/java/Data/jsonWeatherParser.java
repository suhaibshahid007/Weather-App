package Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Model.Place;
import Model.Weather;
import Util.Utils;

/**
 * Created by Muhammad Suhaib on 8/10/2018.
 */

public class jsonWeatherParser {

    public static Weather getWeatherData(String jsonData ){


        Weather weather = new Weather();
        // now create the json object ...
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            Place place = new Place();

            // now extract the coord obj from the json object ....
            JSONObject coorObj = Utils.getObject("coord" , jsonObject);
            place.setLon(Utils.getFloat("lon" , coorObj));
            place.setLat(Utils.getFloat("lat" , coorObj));
            //now extract the  sys object from the json object ...
            JSONObject sysObj = Utils.getObject("sys",jsonObject);
            weather.place.setCountry(Utils.getString("country",sysObj));
            weather.place.setSunrise(Utils.getFloat("sunrise",sysObj));
            weather.place.setSunset(Utils.getFloat("sunset",sysObj));
            weather.place.setLastUpdate(Utils.getInt("dt",jsonObject));
            weather.place.setCity(Utils.getString("name",jsonObject));


            // get the weather  object ...

            JSONArray jsonArray = jsonObject.getJSONArray("weather");

            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
            weather.current.setWeaterId(Utils.getInt("id",jsonObject1));
            weather.current.setCondition(Utils.getString("main",jsonObject1));
            weather.current.setDescription(Utils.getString("description",jsonObject1));
            weather.current.setIcon(Utils.getString("icon",jsonObject1));


            // get the wind object ....
            JSONObject windObject = Utils.getObject("wind",jsonObject);
            weather.wind.setSpeed(Utils.getFloat("speed",windObject));
            weather.wind.setDeg(Utils.getFloat("deg",windObject));

            // get the clouds object ....
            JSONObject cloudObject = Utils.getObject("clouds",jsonObject);
            weather.clouds.setPrecipitation(Utils.getInt("all" , cloudObject));

            // get the main object ...
            JSONObject mainObj = Utils.getObject("main",jsonObject);
            weather.current.setTemp(Utils.getDouble("temp" , mainObj));
            weather.current.setPressure(Utils.getInt("pressure",mainObj));
            weather.current.setHumidity(Utils.getInt("humidity",mainObj));




            return weather;

        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return  null;

    }
}
