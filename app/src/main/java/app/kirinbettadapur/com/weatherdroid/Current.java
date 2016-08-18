package app.kirinbettadapur.com.weatherdroid;

/**
 * Created by Kirin on 6/5/2016.
 */
public class Current {

    private Double lat;
    private Double lng;
    private int temperature;
    private int windChill;
    private int windSpeed;
    private String currentState;
    private String currentStateImgSrc;
    private int rainChance;
    private int dewPoint;
    private int humidity;
    private int cloudCover;

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

    public int getTemperature() { return temperature; }
    public void setTemperature(int value) {
        this.temperature = value;
    }

    public int getWindChill() { return windChill; }
    public void setWindChill(int value) { this.windChill = value; }

    public int getWindSpeed() { return windSpeed; }
    public void setWindSpeed(int value) { this.windSpeed = value; }

    public String getCurrentState() {
        return currentState;
    }
    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public int getRainChance() { return rainChance; }
    public void setRainChance(Double value) {
        rainChance = ((Double)(value * 100)).intValue();
    }

    public String getCurrentStateImgSrc() { return currentStateImgSrc; }
    public void setCurrentStateImgSrc(String value) { currentStateImgSrc = value.replace('-','_'); }

    public int getDewPoint() { return dewPoint; }
    public void setDewPoint(int value) { this.dewPoint = value; }

    public int getHumidity() { return humidity; }
    public void setHumidity(int value) { this.humidity = value; }

    public int getCloudCover() { return cloudCover; }
    public void setCloudCover(int value) { this.cloudCover = value; }
}
