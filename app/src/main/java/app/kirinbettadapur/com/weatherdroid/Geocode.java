package app.kirinbettadapur.com.weatherdroid;

import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Kirin on 5/13/2016.
 */
public class Geocode {

    private Double lat;
    private Double lng;


    public Geocode() {

    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }
}
