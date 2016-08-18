package app.kirinbettadapur.com.weatherdroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {

    public SearchView searchUtil;
    public TextView cityName;
    public TextView currentTemperature;
    public CurrentStatView windChill;
    public TextView currentSummary;
    public TextView coordinates;
    public Toolbar navBar;
    public HorizontalScrollView dailyItems;

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        navBar = (Toolbar) findViewById(R.id.navBar);
        setSupportActionBar(navBar);
        cityName = (TextView) findViewById(R.id.cityName);
        currentTemperature = (TextView) findViewById(R.id.currentTemperature);
        windChill = (CurrentStatView) findViewById(R.id.windChill);
        coordinates = (TextView) findViewById(R.id.coordinates);
        currentSummary = (TextView) findViewById(R.id.currentSummary);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.search_bar);
        searchUtil = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchUtil.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Geoclient.getInstance().getForecast(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /**if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    public static Context getContext() {
        return context;
    }

    public void getWeather(String city) {

    }
}
