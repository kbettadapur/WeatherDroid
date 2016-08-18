package app.kirinbettadapur.com.weatherdroid;

/**
 * Created by Kirin on 6/30/2016.
 */
public class Hourly {
    private Hour[] hours;

    public Hourly() {
        hours = new Hour[49];
    }

    public Hour[] getHours() {
        return hours;
    }

    public void setHours(Hour[] value) {
        this.hours = value;
    }
}
