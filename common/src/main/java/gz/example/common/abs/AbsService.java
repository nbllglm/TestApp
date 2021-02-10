package gz.example.common.abs;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import gz.example.common.BuildConfig;

public class AbsService extends Service {
    public static String TAG = BuildConfig.serviceTAG;

    @Override
    public void onCreate() {
        Log.i(TAG, getClass().getSimpleName() + "    onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, getClass().getSimpleName() + "    onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, getClass().getSimpleName() + "    onRebind: ");
        super.onRebind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, getClass().getSimpleName() + "    onBind: ");
        return null;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, getClass().getSimpleName() + "    onUnbind: ");
        return super.onUnbind(intent);
    }


    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + "    onDestroy: ");
        super.onDestroy();
    }
}
