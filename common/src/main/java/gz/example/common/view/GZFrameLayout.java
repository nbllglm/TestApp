package gz.example.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import gz.example.common.util.LogUtil;

public class GZFrameLayout extends FrameLayout {

    public GZFrameLayout(@NonNull Context context) {
        super(context);
    }

    public GZFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int count = getChildCount();
        if (mode == 1073741824) {
            LogUtil.getInstance().info(this.getClass().getSimpleName() + "宽mode" + "EXTACLY");
        } else if (mode == -2147483648) {
            LogUtil.getInstance().info(this.getClass().getSimpleName() + "宽mode" + "AT_MOTST");
        }
        LogUtil.getInstance().info(this.getClass().getSimpleName() + "宽size" + size);
        LogUtil.getInstance().info(this.getClass().getSimpleName() + "子count" + count);
        LogUtil.getInstance().info(this.getClass().getSimpleName() + "            ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();
        LogUtil.getInstance().info(this.getClass().getSimpleName() + "测量宽size" + getMeasuredWidth());
        LogUtil.getInstance().info(this.getClass().getSimpleName() + "子count" + count);
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
