package gz.example.common.service;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import gz.example.common.abs.AbsService;

public class ShowMessengerService extends AbsService {
    private ServiceHandler handler;
    private Messenger ServerMessenger;

    private class ServiceHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
            switch (msg.what){
                case 123:
                    msg.what=456;
                    try {
                        msg.replyTo.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }

        public ServiceHandler() {
            super();
        }

        public ServiceHandler(@NonNull Looper looper) {
            super(looper);
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Thread current=Thread.currentThread();
//        if(current.equals(Looper.getMainLooper().getThread())){
            handler=new ServiceHandler();
//        }else {
//            HandlerThread handlerThread=new HandlerThread("ShowMessengerService");
//            handler=new ServiceHandler(handlerThread.getLooper());
//        }
        ServerMessenger=new Messenger(handler);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return ServerMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
