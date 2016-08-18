package app.kirinbettadapur.com.weatherdroid;

/**
 * Created by Kirin on 6/5/2016.
 */
public class Current {

    private Double lat;
    private Double lng;
    private Double temperature;
    private String currentState;

    public void setLat(Double value) {
        this.lat = value;
    }

    public void setLng(Double value) {
        this.lng = value;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double value) {
        this.temperature = value;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }
}
