package app.kirinbettadapur.com.weatherdroid;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Kirin on 6/21/2016.
 */
public class DayView extends RelativeLayout {

    public TextView day;
    public ImageView icon;
    public TextView temp;

    public DayView(Context context) {
        super(context);
        init();
    }

    public DayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DayView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.day_view, this);
        day = (TextView)findViewById(R.id.dayId);
    }
}
