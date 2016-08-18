package app.kirinbettadapur.com.weatherdroid;

/**
 * Created by Kirin on 6/19/2016.
 */
public class Daily {

    private Day[] days;

    public Daily() {
        days = new Day[7];
    }

    public Day[] getDays() {
        return days;
    }

    public void setDays(Day[] value) {
        this.days = value;
    }
}
