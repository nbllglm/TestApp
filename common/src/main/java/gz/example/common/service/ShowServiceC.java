package gz.example.common.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import gz.example.common.R;
import gz.example.common.abs.AbsService;
import gz.example.common.service.binder.BinderB;
import gz.example.common.service.binder.BinderC;

public class ShowServiceC extends AbsService {
    private int id = 1;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    void qiantai() {
        String CHANNEL_ONE_ID = "gz.example.common";
        String CHANNEL_ONE_NAME = "serviceC";
        NotificationChannel notificationChannel;
//进行8.0的判断
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(CHANNEL_ONE_ID,
                    CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.createNotificationChannel(notificationChannel);
            }
            Notification notification = new Notification.Builder(this, CHANNEL_ONE_ID).setChannelId(CHANNEL_ONE_ID)
                    .setTicker("Nature")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("这是一个测试标题")
                    .setContentText("这是一个测试内容")
                    .build();
            notification.flags |= Notification.FLAG_NO_CLEAR;
            startForeground(1, notification);
        }
//        Intent intent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent= PendingIntent.getActivity(this, 0, intent, 0);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
     //   qiantai();
        Log.i(TAG, getClass().getSimpleName() + "    onStartCommand: ");
        return START_STICKY;
//        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, getClass().getSimpleName() + "    onBind: ");
        return new BinderC();
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
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            stopForeground(Service.STOP_FOREGROUND_REMOVE);
//        }
//        Intent intent=new Intent();
//        intent.setPackage(getPackageName());
//        intent.setAction("gz.example.common.broad2");
//        sendBroadcast(intent);
        super.onDestroy();
    }
}
