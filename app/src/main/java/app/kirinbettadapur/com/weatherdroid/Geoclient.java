package app.kirinbettadapur.com.weatherdroid;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kirin on 5/13/2016.
 */
public class Geoclient {
    private Retrofit retrofit;
    private static Geoclient instance;
    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/geocode/";
    private static final String API_KEY = "AIzaSyArQaXOKLUqXidrefuymzByJebc-rNE3o8";

    public static Geoclient getInstance() {
        if (instance == null) {
            instance = new Geoclient();
        }
        return instance;
    }

    private Geoclient() {

    }

    public void getCoordinates(String city) {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Geoservice geo = retrofit.create(Geoservice.class);
        Call<Geocode> call = geo.getCoordinates("Atlanta", API_KEY);
        call.enqueue(new Callback<Geocode>() {
            @Override
            public void onResponse(Call<Geocode> call, Response<Geocode> response) {
                Log.d("Response", new Gson().toJson(response.body()));

            }

            @Override
            public void onFailure(Call<Geocode> call, Throwable t) {
                Log.d("Tag", "False");
            }
        });
    }
}
