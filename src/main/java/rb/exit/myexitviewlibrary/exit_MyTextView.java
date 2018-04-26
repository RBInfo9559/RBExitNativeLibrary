package rb.exit.myexitviewlibrary;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;


public class exit_MyTextView extends android.support.v7.widget.AppCompatTextView {

    public exit_MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public exit_MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public exit_MyTextView(Context context) {
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