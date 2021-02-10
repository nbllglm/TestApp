package gz.example.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import gz.example.common.abs.AbsActivity;


public class MainActivity extends AbsActivity {

    private TextView textView;
    private TextView asd;
    private Context context = this;
    private static boolean qqq = true;
    private String imagURl = "https://dfzximg02.dftoutiao.com//news//20210110//20210110214139_b17a1dd4c8953b1ab93787c45a3b25d1_0_mwpm_03201609.jpeg";

    private class gzhandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int parentWidthSpec = View.MeasureSpec.makeMeasureSpec(500, View.MeasureSpec.EXACTLY);
            int parentHeightSpec = View.MeasureSpec.makeMeasureSpec(300, View.MeasureSpec.EXACTLY);
            int childWidthSpec = ViewGroup.getChildMeasureSpec(parentWidthSpec, 0, ViewGroup.LayoutParams.WRAP_CONTENT);
            int childHeightSpec = ViewGroup.getChildMeasureSpec(parentHeightSpec, 0, ViewGroup.LayoutParams.WRAP_CONTENT);
//                textView.measure(childWidthSpec,childHeightSpec);
//                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(View.MeasureSpec.getSize(childWidthSpec),
//                        View.MeasureSpec.getSize(childHeightSpec));

            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            layoutParams.width = View.MeasureSpec.getSize(childWidthSpec);
            layoutParams.height = View.MeasureSpec.getSize(childHeightSpec);
            // textView.measure(childWidthSpec,childHeightSpec);
            textView.setLayoutParams(layoutParams);
//                textView.invalidate();
            qqq = true;
        }
    }

    private gzhandler gzhandler = new gzhandler();
    private PersonManager personManager;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            personManager = PersonManager.Stub.asInterface(service);
            try {
                personManager.addPerson(new Person("A", 1));
                personManager.addPerson(new Person("B", 2));
                personManager.addPerson(new Person("C", 3));
                SystemClock.sleep(2000);
                personManager.getPersonList();
                SystemClock.sleep(200000);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getIntent();
        Intent intent = new Intent(this, MainService.class);
//        Intent intent1 = new Intent();
//        intent1.setAction("a");
//        intent1.setAction("b");
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
//        System.out.println(BuildConfig.debug);
//        System.out.println(NetMainActivity.net == 1);
//        ((TextView) findViewById(R.id.asd)).setText(String.valueOf(NetMainActivity.net == 1));

        asd = findViewById(R.id.asd);
        FrameLayout rootView = findViewById(android.R.id.content);

        textView = new TextView(this);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(200, 100);
        textView.setLayoutParams(layoutParams);

        textView.setText(String.valueOf(BuildConfig.isrelease));

        textView.setTextColor(Color.parseColor("#000000"));
        textView.setBackgroundColor(Color.parseColor("#41b24e"));
        textView.setTextSize(20);


        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        FrameLayout.LayoutParams layoutParams1 = new FrameLayout.LayoutParams(500, 500);

        layoutParams1.setMargins(400, 400, 0, 0);
        imageView.setLayoutParams(layoutParams1);

//        Glide.with(this).load(imagURl).into(imageView);
//        decorView.addView(textView, layoutParams);

        rootView.addView(textView);
        rootView.addView(imageView);
        //asd.setText(textView.getMeasuredWidth() + "   " + textView.getMeasuredHeight());
        ViewTreeObserver observer = textView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                asd.setText(textView.getMeasuredWidth() + "   " + textView.getMeasuredHeight());
                if (qqq) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.d("nnnnnnnn", "qweqwe");
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            gzhandler.sendEmptyMessage(0);
                        }
                    }).start();
                    qqq = false;
                }


            }
        });


//        findViewById(R.id.c)
        //  asd();
//        qwe();
//        zxc();
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }

    public void zxc() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                gzhandler.sendMessage(message);
            }
        }).start();

    }

    public void qwe() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(Data.data);
            Toast.makeText(this, jsonObject.getString("reason"), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


//    public void asd() {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        RequestBody requestBody = new FormBody.Builder()
//                .add("key", "1dd307b94c39a4ac3682cc2d3622b672")
//                .add("type", "top")
//                .build();
//        final Request request = new Request.Builder().url("http://v.juhe.cn/toutiao/index").post(requestBody).build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                Log.d("asdasdasd", e.getMessage());
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                Log.d("asdasdasd", response.body().string());
//            }
//        });
//
//    }

}