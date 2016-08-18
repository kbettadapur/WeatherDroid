package app.kirinbettadapur.com.weatherdroid;
import android.app.ActionBar;
import android.app.Application;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;

import com.squareup.okhttp.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


/**
 * Created by Kirin on 5/13/2016.
 */
public class Geoclient extends Application {
    private static Geoclient instance;
    private static final String GEOCODE_BASE_URL = "https://maps.googleapis.com/maps/api/geocode/";
    private static final String GEOCODE_API_KEY = "AIzaSyArQaXOKLUqXidrefuymzByJebc-rNE3o8";
    private static final String FORECAST_BASE_URL = "https://api.forecast.io/forecast/";
    private static final String FORECAST_API_KEY = "70c6d8448f6fc4a51c2ab2e4c4d2daeb";



    public static Geoclient getInstance() {
        if (instance == null) {
            instance = new Geoclient();
        }
        return instance;
    }

    private Geoclient() {

    }

    public void getForecast(String city) {
        if (city.equals("") || city.equals(null)) {
            return;
        }
        OkHttpClient client = new OkHttpClient();
        String url = GEOCODE_BASE_URL + "json?address=" + city + "&key=" + GEOCODE_API_KEY;
        url = url.replaceAll(" ", "");
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("Tag", "Failed");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String jsonString = response.body().string();
                try {
                    parseGeoResponse(jsonString);
                    OkHttpClient forecastClient = new OkHttpClient();
                    Request forecastRequest = new Request.Builder().url(FORECAST_BASE_URL + FORECAST_API_KEY + "/"
                            + Forecast.getInstance().getCurrentForecast().getLat()
                            + "," + Forecast.getInstance().getCurrentForecast().getLng()).build();
                    forecastClient.newCall(forecastRequest).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            Log.d("Tag", "Weather fail");
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            String weatherJson = response.body().string();
                            parseForecastResponse(weatherJson);
                        }
                    });

                } catch (Exception e) {
                    Log.d("Tag", "Exception");
                }
            }
        });
    }

    public void parseGeoResponse(String jsonString) {
        try {
            JSONObject obj = new JSONObject(jsonString);
            JSONArray results = (JSONArray) obj.get("results");
            JSONObject result = (JSONObject) results.get(0);
            JSONObject geometry = (JSONObject) result.get("geometry");
            JSONArray address_components = (JSONArray) result.get("address_components");
            JSONObject location = (JSONObject) geometry.get("location");
            JSONObject cityNames = (JSONObject) address_components.get(0);
            Double lat = 0.0;
            Double lng = 0.0;
            try {
                lat = (Double) location.get("lat");
            } catch (ClassCastException cce) {
                lat = ((Integer)location.get("lat")).doubleValue();
            }
            try {
                lng = (Double)(location.get("lng"));
            } catch (ClassCastException cce) {
                lng = ((Integer)location.get("lng")).doubleValue();
            }
            Forecast.getInstance().setCityName(cityNames.get("short_name").toString());
            Forecast.getInstance().getCurrentForecast().setLat(lat);
            Forecast.getInstance().getCurrentForecast().setLng(lng);
        } catch (Exception e) {
            Log.d("Tag", "Exception");
        }
    }

    public void parseForecastResponse(String jsonString) {
        try {
            JSONObject weatherObj = new JSONObject(jsonString);

            Forecast.getInstance().setTimezone(weatherObj.getString("timezone"));

            //Currently
            JSONObject currently = (JSONObject) weatherObj.get("currently");
            Forecast.getInstance().getCurrentForecast().setTemperature((int) currently.getDouble("temperature"));
            Forecast.getInstance().getCurrentForecast().setWindChill((int) currently.getDouble("apparentTemperature"));
            try {
                Forecast.getInstance().getCurrentForecast().setRainChance((Double) currently.get("precipProbability"));
            } catch (ClassCastException cce) {
                Forecast.getInstance().getCurrentForecast().setRainChance(0.0);
            }
            Forecast.getInstance().getCurrentForecast().setCurrentState(currently.get("summary").toString());
            Forecast.getInstance().getCurrentForecast().setCurrentStateImgSrc(currently.getString("icon"));
            Forecast.getInstance().getCurrentForecast().setWindSpeed((int) currently.getDouble("windSpeed"));
            Forecast.getInstance().getCurrentForecast().setDewPoint((int) currently.getDouble("dewPoint"));
            Forecast.getInstance().getCurrentForecast().setHumidity((int) (currently.getDouble("humidity") * 100));
            Forecast.getInstance().getCurrentForecast().setCloudCover((int) (currently.getDouble("cloudCover") * 100));

            //Daily
            JSONObject daily = (JSONObject) weatherObj.get("daily");
            JSONArray data = (JSONArray) daily.get("data");
            for (int i = 0; i < 7; i++) {
                JSONObject day = (JSONObject) data.get(i);
                double high = day.getDouble("temperatureMax");
                double low = day.getDouble("temperatureMin");
                String icon = day.getString("icon");
                long time = day.getLong("time");
                Forecast.getInstance().getDailyForecast().getDays()[i] = new Day(high, low, icon, time);
            }

            //Hourly
            updateView();
            JSONObject hourly = (JSONObject) weatherObj.get("hourly");
            data = (JSONArray) hourly.get("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject hour = (JSONObject) data.get(i);
                long time = hour.getLong("time");
                String icon = hour.getString("icon");
                Double temperature = hour.getDouble("temperature");
                Double rainChance = 0.0;
                try {
                    rainChance = hour.getDouble("precipProbability");
                } catch (ClassCastException cce) {
                    cce.printStackTrace();
                }
                Double windSpeed = hour.getDouble("windSpeed");
                Forecast.getInstance().getHourlyForecast().getHours()[i] = new Hour(time, icon, temperature, rainChance, windSpeed);
            }
        } catch (Exception ex) {
            Log.d("Tag", "Weather Exception");
        }
    }

    public void updateView() {
        final MainActivity currentActivity = (MainActivity) MainActivity.getContext();
        currentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                currentActivity.currentTemperature.setText(Forecast.getInstance().getCurrentForecast().getTemperature() + " °F");
                currentActivity.cityName.setText(Forecast.getInstance().getCityName().toString());
                currentActivity.coordinates.setText(Forecast.getInstance().getCurrentForecast().getLat() + ", " + Forecast.getInstance().getCurrentForecast().getLng());
                currentActivity.currentSummary.setText(Forecast.getInstance().getCurrentForecast().getCurrentState());
                ImageView currentSummaryImg = (ImageView) currentActivity.findViewById(R.id.currentSummaryIcon);
                int csiId = currentActivity.getResources().getIdentifier(Forecast.getInstance().getCurrentForecast().getCurrentStateImgSrc(), "drawable", currentActivity.getPackageName());
                currentSummaryImg.setImageResource(csiId);

                int[] views = {R.id.rainChance, R.id.windChill, R.id.windSpeed, R.id.dewpoint, R.id.humidity, R.id.cloudcover};
                String[] values = {Forecast.getInstance().getCurrentForecast().getRainChance() + "%",
                                    Forecast.getInstance().getCurrentForecast().getWindChill() + " °F",
                                    Forecast.getInstance().getCurrentForecast().getWindSpeed() + " mph",
                                    Forecast.getInstance().getCurrentForecast().getDewPoint() + " °F",
                                    Forecast.getInstance().getCurrentForecast().getHumidity() + "%",
                                    Forecast.getInstance().getCurrentForecast().getCloudCover() + "%"};
                String[] icons = {"drop", "wind", "windspeed", "dew_point", "humidity", "cloud_cover"};

                for (int i = 0; i < views.length; i++) {
                    CurrentStatView csv = (CurrentStatView) currentActivity.findViewById(views[i]);
                    ImageView csvImg = (ImageView) csv.findViewById(R.id.currentStatIcon);
                    TextView csvTxt = (TextView) csv.findViewById(R.id.stat_text);
                    csvTxt.setText(values[i]);
                    int csvImgId = currentActivity.getResources().getIdentifier(icons[i], "drawable", currentActivity.getPackageName());
                    csvImg.setImageResource(csvImgId);
                }

                LinearLayout ll = (LinearLayout) currentActivity.findViewById(R.id.weekStats);
                ll.removeAllViews();
                TimeZone tz = TimeZone.getTimeZone(Forecast.getInstance().getTimezone());
                Calendar c = Calendar.getInstance(tz);
                c.setTimeInMillis(Forecast.getInstance().getDailyForecast().getDays()[0].getTime() * 1000);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setTimeZone(tz);
                int dayIndex = c.get(Calendar.DAY_OF_WEEK) - 1;
                String[] days = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
                for (int i = 1; i < 8; i++) {
                    DayView dv = new DayView(currentActivity.getApplicationContext());
                    TextView day = (TextView) dv.findViewById(R.id.dayId);
                    day.setText(days[dayIndex++]);
                    if (dayIndex == 7) { dayIndex = 0; }
                    ImageView icon = (ImageView) dv.findViewById(R.id.icon);
                    String iconSrc = Forecast.getInstance().getDailyForecast().getDays()[i - 1].getIconSource().replace('-','_');
                    int srcId = currentActivity.getResources().getIdentifier(iconSrc, "drawable", currentActivity.getPackageName());
                    icon.setImageResource(srcId);

                    TextView hiTv = (TextView) dv.findViewById(R.id.hi);
                    TextView loTv = (TextView) dv.findViewById(R.id.lo);
                    Double hi = Forecast.getInstance().getDailyForecast().getDays()[i - 1].getHighTemp();
                    Double lo = Forecast.getInstance().getDailyForecast().getDays()[i - 1].getLowTemp();
                    hiTv.setText(hi.intValue() + "");
                    loTv.setText(lo.intValue() + "");

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(50,0,0,0);
                    dv.setLayoutParams(params);
                    ll.addView(dv);
                }


            }
        });
    }
}