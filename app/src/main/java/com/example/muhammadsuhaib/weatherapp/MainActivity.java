package com.example.muhammadsuhaib.weatherapp;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

import Data.WeatherHttpRequest;
import Data.jsonWeatherParser;
import Model.Weather;
import Model.currentCondition;
import Util.Utils;

public class MainActivity extends AppCompatActivity {

    // here the textviews ....

    private TextView cityName;
    private TextView temp;
    private TextView wind;
    private TextView  humidity;
    private TextView pressure;
    private TextView sunrise;
    private TextView sunset;
    private TextView updated;
    private TextView description;
    private ImageView iconImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the views ....
        cityName = findViewById(R.id.cityText);
        temp = findViewById(R.id.tempText);
         description = findViewById(R.id.cloudText);
        wind = findViewById(R.id.windText);
        humidity = findViewById(R.id.humidityText);
        sunrise = findViewById(R.id.sunriseText);
        sunset = findViewById(R.id.sunsetText);
        updated = findViewById(R.id.lastUpdatedDate);
        pressure = findViewById(R.id.pressureText);
        iconImage = findViewById(R.id.imageIcon);

        // here call the render data function ...
        renderWeatherData("Gojra,PK");
    }

    public void renderWeatherData(String city)
    {
          weatherAsyncTask weatherAsyncTask = new weatherAsyncTask();
          weatherAsyncTask.execute(new String[]{city + "&appid=84fc36f982edc786012d2b938228c95b"});
    }

    // here the async task that use for the background task/long running activity .....

    private class weatherAsyncTask extends AsyncTask<String , Void ,Weather >
    {


        @Override
        protected Weather doInBackground(String... params) {
            // here the code to call the httpRequest method and get the data in the string format as response ...

            WeatherHttpRequest httpRequest = new WeatherHttpRequest();
            String response = httpRequest.getWeatherJsonData(params[0]);

            Weather weather =  jsonWeatherParser.getWeatherData(response);


            // here display data in logcat ....


            Log.v("Data" ,weather.current.getDescription());

            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

          DateFormat df = DateFormat.getTimeInstance();

          String sunriseDate = df.format(new Date((long) weather.place.getSunrise()));
          String sunsetDate = df.format(new Date((long) weather.place.getSunset()));
          String updateDate = df.format(new Date((long) weather.place.getLastUpdate()));

            DecimalFormat decimalFormat = new DecimalFormat("00.#");
            String tempFormat = decimalFormat.format(weather.current.getTemp());

            // here the code that sets the incoming response into the UI text views....
            cityName.setText(weather.place.getCity() + "," + weather.place.getCountry());
            temp.setText( " " + tempFormat + "°C" );
            pressure.setText("Pressure: " + weather.current.getPressure() +"hPa");
            humidity.setText("Humidity: " + weather.current.getHumidity() +"%");
            wind.setText("Wind: " + weather.wind.getSpeed());
            sunrise.setText("Sunrise: " + sunriseDate);
            sunset.setText("Sunset: " + sunsetDate);
            updated.setText("Last Updated: " + updateDate);
            description.setText("Clouds: " + weather.current.getCondition() + "(" + weather.current.getDescription() + ")");

            // here the code that load the image from url ...

            String url = Utils.ICON_URL + weather.current.getIcon() + ".png";

            // here use the picasoo library to load the image from the url ...
           Picasso.with(MainActivity.this).load(url).placeholder(R.drawable.ic_launcher_background) // optional

                    .error(R.drawable.ic_launcher_background)  // on optional
                    .into(iconImage , new com.squareup.picasso.Callback(){

                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {

                        }
                    });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       getMenuInflater().inflate( R.menu.main_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       int id =  item.getItemId();
       if(id == R.id.city_Settings)
       {
             showDialog();
       }
        return super.onOptionsItemSelected(item);
    }

    // here the method that shows input dialog ...

    public void showDialog()
    {

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Change City");
        final EditText cityInput = new EditText(MainActivity.this);
        cityInput.setHint("Lahore,PK");
        cityInput.setInputType(InputType.TYPE_CLASS_TEXT);
        dialog.setView(cityInput);
        dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

              String  newCity = cityInput.getText().toString();
              renderWeatherData(newCity);

            }
        });
        dialog.show();




    }
}
