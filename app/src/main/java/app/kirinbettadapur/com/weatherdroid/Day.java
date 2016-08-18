package app.kirinbettadapur.com.weatherdroid;

/**
 * Created by Kirin on 6/19/2016.
 */
public class Day {

    private double highTemp;
    private double lowTemp;
    private String iconSource;
    private long time;

    public Day(double highTemp, double lowTemp, String iconSource, long time) {
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.iconSource = iconSource;
        this.time = time;
    }

    public double getHighTemp() {
        return highTemp;
    }

    public double getLowTemp() {
        return lowTemp;
    }

    public String getIconSource() {
        return iconSource;
    }

    public long getTime() {
        return this.time;
    }

    public void setHighTemp(double value) {
        this.highTemp = value;
    }

    public void setLowTemp(double value) {
        this.lowTemp = value;
    }

    public void setIconSource(String value) {
        this.iconSource = value;
    }

    public void setTime(long value) {
        this.time = value;
    }
}
