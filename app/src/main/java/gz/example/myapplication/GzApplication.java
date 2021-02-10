package gz.example.myapplication;

import android.app.Application;


public class GzApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        MMKV.initialize(this);
    }
}
