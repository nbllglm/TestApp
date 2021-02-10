package gz.example.common.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class UiUtil {

    private static volatile UiUtil instance;

    private Context context;
    private float displaywidth;
    private float displayheight;
    private static final String DIME_CLASS = "com.android.internal.R$dimen";

    private int getSystemBarHeight(Context context) {
        return getValue(context, DIME_CLASS, "System_bar_height", 48);
    }

    private int getValue(Context context, String dimeClass, String system_bar_height, int ddd) {
        try {
            Class<?> clz = Class.forName(dimeClass);
            Object object = clz.newInstance();
            Field field = clz.getField(system_bar_height);
            int id = Integer.parseInt(field.get(object).toString());
            return context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ddd;
    }

    public UiUtil(Context context) {
        this.context = context;

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (displaywidth == 0.0F || displayheight == 0.0F) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int barHeight = getSystemBarHeight(context);
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                displaywidth = displayheight;
                displayheight = displayMetrics.widthPixels - barHeight;
            } else {
                displaywidth = displayMetrics.widthPixels;
                displayheight = displayMetrics.heightPixels - barHeight;
            }
        }


    }

    public float getDisplaywidth() {
        return displaywidth;
    }

    public void setDisplaywidth(float displaywidth) {
        this.displaywidth = displaywidth;
    }

    public float getDisplayheight() {
        return displayheight;
    }

    public void setDisplayheight(float displayheight) {
        this.displayheight = displayheight;
    }

    public static UiUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (UiUtil.class) {
                if (instance == null) {
                    instance = new UiUtil(context);
                }
            }
        }
        return instance;
    }
}
