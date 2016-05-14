package app.kirinbettadapur.com.weatherdroid;
import retrofit2.*;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kirin on 5/13/2016.
 */
public interface Geoservice {

    @GET("json")
    Call<Geocode> getCoordinates(@Query("address") String city, @Query("key") String key);
}
