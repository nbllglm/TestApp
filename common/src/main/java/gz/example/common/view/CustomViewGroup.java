package gz.example.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class CustomViewGroup extends RelativeLayout {
//dispatchTouchEvent

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        boolean result = false;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i("view事件分发", "CustomViewGroup onTouchEvent: ACTION_DOWN" + result);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("view事件分发", "CustomViewGroup onTouchEvent: ACTION_MOVE" + result);

                break;
            case MotionEvent.ACTION_UP:
//                result=false;
                Log.i("view事件分发", "CustomViewGroup onTouchEvent: ACTION_UP" + result);
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("view事件分发", "CustomViewGroup onTouchEvent: ACTION_CANCEL" + result);
                break;
            default:
                break;
        }
//        return result;
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        final int action = ev.getAction();
        boolean result = false;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
//                result=true;
                Log.i("view事件分发", "CustomViewGroup onInterceptTouchEvent: ACTION_DOWN" + result);
                break;
            case MotionEvent.ACTION_MOVE:
                result=true;
                Log.i("view事件分发", "CustomViewGroup onInterceptTouchEvent: ACTION_MOVE" + result);
                break;
            case MotionEvent.ACTION_UP:
                Log.i("view事件分发", "CustomViewGroup onInterceptTouchEvent: ACTION_UP" + result);
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("view事件分发", "CustomViewGroup onInterceptTouchEvent: ACTION_CANCEL" + result);
                break;
            default:
                break;
        }
        return result;
//        return super.onInterceptTouchEvent(ev);
    }
}
