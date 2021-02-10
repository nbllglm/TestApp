package gz.example.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import gz.example.common.util.DpUtil;


/**
 * @author 15600
 */
public class FlowLayout extends ViewGroup {
    private int hor = DpUtil.dp2px(this, 16);
    private int ver = DpUtil.dp2px(this, 8);

    public FlowLayout(Context context) {
        super(context);
    }

    //反射
    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode;
        int widthSize;
        int heightMode;
        int HeightSize;
        int width=0;
        int height=0;


        //先度量孩子
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }

        //度量自己
        setMeasuredDimension(width,height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
