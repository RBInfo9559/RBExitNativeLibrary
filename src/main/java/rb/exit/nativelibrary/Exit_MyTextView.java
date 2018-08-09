package rb.exit.nativelibrary;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class Exit_MyTextView extends TextView {

    public Exit_MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public Exit_MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Exit_MyTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Roboto-Regular.ttf");
            setTypeface(tf);
        }
    }

}