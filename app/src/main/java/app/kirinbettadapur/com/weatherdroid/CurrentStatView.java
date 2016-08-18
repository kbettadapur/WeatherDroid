package app.kirinbettadapur.com.weatherdroid;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Kirin on 6/27/2016.
 */
public class CurrentStatView extends RelativeLayout {

    public CurrentStatView(Context context) {
        super(context);
        init();
    }

    public CurrentStatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CurrentStatView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.current_stat_view, this);
    }
}
