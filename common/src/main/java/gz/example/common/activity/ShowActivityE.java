package gz.example.common.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import gz.example.common.R;
import gz.example.common.abs.AbsActivity;
import gz.example.common.util.LogUtil;
import gz.example.common.view.CustomView;
import gz.example.common.view.CustomViewGroup;

public class ShowActivityE extends AbsActivity {
    private CustomViewGroup customViewGroup;
    private CustomView customView;
    private ViewStub viewStub;

    private Thread  pThread = new ProductThread();
    private Thread cThread = new ConSumerThread();
    private static Looper looper = Looper.getMainLooper();
    private static Handler pHandler;
    private static Handler cHandler;


    private  static class ProductThread extends Thread {
        @Override
        public void run() {
            pHandler = new Handler(looper) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    switch (msg.what) {
                        case 111:
                            LogUtil.getInstance().info("Handler",
                                    Thread.currentThread().getName() + "   " + msg.getTarget().toString()
                                            + "   " + msg.what);
                            Message message1 = Message.obtain();
                            message1.what = 222;
                            cHandler.sendMessage(message1);
                            break;
                        case 333:
                            LogUtil.getInstance().info("Handler",
                                    Thread.currentThread().getName() + "   " + msg.getTarget().toString()
                                            + "   " + msg.what);
                            Message message3 = Message.obtain();
                            message3.what = 444;
                            cHandler.sendMessage(message3);
                            break;
                        default:

                            break;
                    }
                }
            };


        }
    }

    private static class ConSumerThread extends Thread {
        @Override
        public void run() {
            cHandler = new Handler(looper) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    switch (msg.what) {
                        case 222:
                            LogUtil.getInstance().info("Handler",
                                    Thread.currentThread().getName() + "   " + msg.getTarget().toString()
                                            + "   " + msg.what);
                            Message message2 = Message.obtain();
                            message2.what = 333;
                            pHandler.sendMessage(message2);
                            break;
                        case 444:
                            LogUtil.getInstance().info("Handler",
                                    Thread.currentThread().getName() + "   " + msg.getTarget().toString()
                                            + "   " + msg.what);
//                            msg.what = 333;
//                            cHandler.sendMessage(msg);
                            break;
                        default:
                            break;
                    }
                }
            };
            Message message = Message.obtain();
            message.what = 111;
            pHandler.sendMessage(message);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_activity);

        customViewGroup = findViewById(R.id.testView);
        customView = customViewGroup.findViewById(R.id.cust);
        viewStub = findViewById(R.id.stub);

//        customView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                pThread.start();
//                cThread.start();
//            }
//        }, 4000);


//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                ImageView imageView=findViewById(R.id.image_comment_stub);
//                ImageView imageView = (ImageView) viewStub.inflate();
//                imageView.setImageResource(R.mipmap.ic_launcher);
//            }
//        }, 5000);

//        handler.postDelayed()


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
