package gz.example.common.service;

import android.content.Intent;

import gz.example.common.abs.AbsService;

public class ShowServiceA extends AbsService {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        haoshi();
        return super.onStartCommand(intent, flags, startId);
    }
    private void haoshi(){
        try {
            Thread.sleep(30*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
