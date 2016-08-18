package app.kirinbettadapur.com.weatherdroid;

/**
 * Created by Kirin on 5/11/2016.
 */
public class Forecast {


    private String cityName;
    private String timezone;
    private Current currentForecast;
    private Daily dailyForecast;
    private Hourly hourlyForecast;

    private static Forecast instance;

    public static Forecast getInstance() {
        if (instance == null) {
            instance = new Forecast();
        }
        return instance;
    }

    private Forecast() {
        dailyForecast = new Daily();
        hourlyForecast = new Hourly();
        currentForecast = new Current();
        timezone = "";
    }

    public void setCityName(String value) {
        this.cityName = value;
    }

    public String getCityName() {
        return this.cityName;
    }

    public String getTimezone() {
        return this.timezone;
    }

    public void setTimezone(String value) {
        this.timezone = value;
    }

    public Current getCurrentForecast() {
        return currentForecast;
    }

    public Daily getDailyForecast() {
        return dailyForecast;
    }

    public Hourly getHourlyForecast() { return hourlyForecast; }
}

