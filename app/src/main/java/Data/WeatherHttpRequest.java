package Data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Util.Utils;

/**
 * Created by Muhammad Suhaib on 8/10/2018.
 */

public class WeatherHttpRequest {

    // here the method that hits the url and get the json data from the server .....
    public String getWeatherJsonData(String place)
    {
        URL url = null;
        HttpURLConnection urlConnection =null;
        InputStream inputStream = null;
        try{
            url = new URL(Utils.BASE_URL+place);
            // now open the connection ..
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.connect();
            // now read the response coming after hittig the server ...
            StringBuffer stringBuffer = new StringBuffer();
            String line ;
            inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            // now get the data ...
            while((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line + "\r\n");
            }
            inputStream.close();
            urlConnection.disconnect();
            //retrun the response ...
            return  stringBuffer.toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return  null;
    }
}