package app.kirinbettadapur.com.weatherdroid;

/**
 * Created by Kirin on 6/30/2016.
 */
public class Hour {
    private long time;
    private String icon;
    private Double temp;
    private Double rainChance;
    private Double windSpeed;

    public Hour(long time, String icon, Double temp, Double rainChance, Double windSpeed) {
        this.time = time;
        this.icon = icon;
        this.temp = temp;
        this.rainChance = rainChance;
        this.windSpeed = windSpeed;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getRainChance() {
        return rainChance;
    }

    public void setRainChance(Double rainChance) {
        this.rainChance = rainChance;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
