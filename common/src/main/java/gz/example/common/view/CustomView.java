package gz.example.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

public class CustomView extends View {
    private float lastX = 0;
    private float lastY = 0;
    private final String TAG="CustomView";
    private int count=0;
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();
        final int action = event.getAction();
        boolean result=false;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                count=0;
                lastX = x;
                lastY = y;
                result=true;
                getParent().requestDisallowInterceptTouchEvent(true);
                Log.i("view事件分发", "CustomView onTouchEvent: ACTION_DOWN"+result);
                break;
            case MotionEvent.ACTION_MOVE:
                count++;
                if(count==10){
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                int offsetx = (int) (x - lastX);
                int offsety = (int) (y - lastY);

                RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) getLayoutParams();
                layoutParams.leftMargin=getLeft()+offsetx;
                layoutParams.topMargin=getTop()+offsety;
                setLayoutParams(layoutParams);

//                offsetLeftAndRight(offsetx);
//                offsetTopAndBottom(offsety);
                result=false;
                //layout(getLeft() + offsetx, getTop() + offsety, getRight() + offsetx, getBottom() + offsety);
                Log.i("view事件分发", "CustomView onTouchEvent: ACTION_MOVE"+result);
                break;
            case MotionEvent.ACTION_UP:
                result= performClick();
                Log.i("view事件分发", "CustomView onTouchEvent: ACTION_UP"+result);
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("view事件分发", "CustomView onTouchEvent: ACTION_CANCEL"+result);
                break;
            default:
                break;
        }

        return result;

//        return super.onTouchEvent(event);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int maxHeight = 0;
        int maxWidth = 0;
        int childState = 0;


        ViewGroup.LayoutParams params=   getLayoutParams();
        // Account for padding too
//        maxWidth += params.getPaddingLeftWithForeground() + getPaddingRightWithForeground();
//        maxHeight += getPaddingTopWithForeground() + getPaddingBottomWithForeground();

        // Check against our minimum height and width
        maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());
        childState=getMeasuredState();

        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState),
                resolveSizeAndState(maxHeight, heightMeasureSpec,
                        childState << MEASURED_HEIGHT_STATE_SHIFT));
    }
    public static int resolveSizeAndState(int size, int measureSpec, int childMeasuredState) {
        final int specMode = MeasureSpec.getMode(measureSpec);
        final int specSize = MeasureSpec.getSize(measureSpec);
        final int result;
        switch (specMode) {
            case MeasureSpec.AT_MOST:
                if (specSize < size) {
                    result = specSize | MEASURED_STATE_TOO_SMALL;
                } else {
                    result = size;
                }
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                result = size;
        }
        return result | (childMeasuredState & MEASURED_STATE_MASK);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getWidth();
        getMeasuredWidth();
    }
}
