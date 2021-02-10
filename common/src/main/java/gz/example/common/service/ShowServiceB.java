package gz.example.common.service;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import gz.example.common.abs.AbsService;
import gz.example.common.service.binder.BinderB;

public class ShowServiceB extends AbsService {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, getClass().getSimpleName() + "    onBind: ");
        return new BinderB(this);
    }


    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, getClass().getSimpleName() + "    onUnbind: ");
        return true;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
