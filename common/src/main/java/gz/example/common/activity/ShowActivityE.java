package gz.example.common.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import gz.example.common.R;
import gz.example.common.abs.AbsActivity;
import gz.example.common.util.ToastUtil;

public class ShowActivityE extends AbsActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_activity);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i("view事件分发", "activity onTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("view事件分发", "activity onTouchEvent: ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("view事件分发", "activity onTouchEvent: ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("view事件分发", "activity onTouchEvent: ACTION_CANCEL");

                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i("view事件分发", "                                    ");
                Log.i("view事件分发", "activity dispatchTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("view事件分发", "                                    ");
                Log.i("view事件分发", "activity dispatchTouchEvent: ACTION_MOVE");

                break;
            case MotionEvent.ACTION_UP:
                Log.i("view事件分发", "                                    ");
                Log.i("view事件分发", "activity dispatchTouchEvent: ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("view事件分发", "                                    ");
                Log.i("view事件分发", "activity dispatchTouchEvent: ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
