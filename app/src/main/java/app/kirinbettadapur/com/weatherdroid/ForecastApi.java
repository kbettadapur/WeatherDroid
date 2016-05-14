package app.kirinbettadapur.com.weatherdroid;

import android.app.Activity;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Kirin on 5/11/2016.
 */
public class ForecastApi {
    private String baseUrl;
    private String apiKey;

    public ForecastApi() {
        baseUrl = "https://api.forecast.io/forecast";
        apiKey = "70c6d8448f6fc4a51c2ab2e4c4d2daeb";
    }


}
