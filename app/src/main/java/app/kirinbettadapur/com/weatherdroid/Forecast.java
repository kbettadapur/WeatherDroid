package app.kirinbettadapur.com.weatherdroid;

/**
 * Created by Kirin on 5/11/2016.
 */
public class Forecast {

    private Double lat;
    private Double lng;
    private static Forecast instance;

    public static Forecast getInstance() {
        if (instance == null) {
            instance = new Forecast(0.0,0.0);
        }
        return instance;
    }

    public Forecast(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public void setLat(Double value) {
        this.lat = value;
    }

    public void setLng(Double value) {
        this.lng = value;
    }

    public Double getLat() {
        return lat;
    }
}

