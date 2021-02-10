package gz.example.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

import gz.example.common.abs.AbsActivity;
import gz.example.myapplication.R;


public class SplashActivity extends AbsActivity {
    private Context context = this;
    private int code = 123;

    private static SplashActivity activity;

    private static class xxhandler extends Handler {
        private final WeakReference<SplashActivity> weakReference;

        public xxhandler(SplashActivity splashActivity) {
            weakReference = new WeakReference<SplashActivity>(splashActivity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    }

    private static class xxTask implements Runnable {
        @Override
        public void run() {

        }
    }

    //    private Handler handler=new Handler();
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler = new xxhandler(this);
        //activity=this;
//        Toast.makeText(this, "我是引导页", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
//        intent.setAction("gz.example.common.home");
//        intent.setAction("gz.example.common.home1");
//        intent.setAction("gz.example.common.page");
//        intent.addCategory("gz.example.common.look1");
//        intent.addCategory("gz.example.common.look2");
//        intent.addCategory("gz.example.common.look3");

        intent.setClassName(this, "gz.example.common.activity.HomeActivity");

        intent.putExtra("A", 1);
        intent.putExtra("name", getClass().getSimpleName());
        startActivityForResult(intent, code);


//        handler.postDelayed(new xxTask(),50000);
//        handler.sendMessageDelayed(Message.obtain(), 50000);


        Log.i("finish", "1" + this);
        finish();
        Log.i("finish", "2" + this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == code) {
                Log.d("SplashActivity", "onActivityResult: ");
            }
        }
    }

    private void asd() {

    }

    public void qwe() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("finish", "3" + this);
    }
}
