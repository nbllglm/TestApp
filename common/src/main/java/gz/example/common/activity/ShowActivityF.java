package gz.example.common.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;

import gz.example.common.IListener;
import gz.example.common.IRemote;
import gz.example.common.R;
import gz.example.common.abs.AbsActivity;
import gz.example.common.service.ShowIntentService;
import gz.example.common.service.ShowMessengerService;
import gz.example.common.service.ShowServiceA;
import gz.example.common.service.ShowServiceB;
import gz.example.common.service.ShowServiceC;
import gz.example.common.service.binder.BinderB;
import gz.example.common.util.LogUtil;

public class ShowActivityF extends AbsActivity implements View.OnClickListener {
    private IRemote mAidlProxy;
    private TextView responseCText;
    private TextView responseMessengerText;
    private IListener callback = new CallbackStub();
    private Messenger ServerMessengerProxy;
    private Messenger ClientMessenger;

    private class ClientHandler extends Handler {

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 456:
                    Bundle bundle=msg.getData();
                    responseMessengerText.setText(bundle.getString(" "));
                    break;
                default:
                    break;
            }
        }
    }

    private class CallbackStub extends IListener.Stub {
        @Override
        public void onResponse(String str) throws RemoteException {
            responseCText.setText(str);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_activity);
        findViewById(R.id.startServiceA).setOnClickListener(this);
        findViewById(R.id.stopServiceA).setOnClickListener(this);
        findViewById(R.id.startServiceB).setOnClickListener(this);
        findViewById(R.id.stopServiceB).setOnClickListener(this);
        findViewById(R.id.bindServiceB).setOnClickListener(this);
        findViewById(R.id.unbindServiceB).setOnClickListener(this);
        findViewById(R.id.bindServiceC).setOnClickListener(this);
        findViewById(R.id.unbindServiceC).setOnClickListener(this);
        findViewById(R.id.startIntentService).setOnClickListener(this);
        findViewById(R.id.sendCText).setOnClickListener(this);
        responseCText = findViewById(R.id.responseCText);

        findViewById(R.id.bindMessengerService).setOnClickListener(this);
        findViewById(R.id.unbindMessengerService).setOnClickListener(this);
        findViewById(R.id.sendMessengerText).setOnClickListener(this);
        responseMessengerText = findViewById(R.id.responseMessengerText);

        ClientMessenger = new Messenger(new ClientHandler());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setPackage(getPackageName());
        if (v.getId() == R.id.startServiceA) {
            intent.setClass(this, ShowServiceA.class);
            startService(intent);
//            this.startForegroundService(intent);
        } else if (v.getId() == R.id.stopServiceA) {
            intent.setClass(this, ShowServiceA.class);
            stopService(intent);
        } else if (v.getId() == R.id.startServiceB) {
            intent.setClass(this, ShowServiceB.class);
            startService(intent);
        } else if (v.getId() == R.id.stopServiceB) {
            intent.setClass(this, ShowServiceB.class);
            stopService(intent);
        } else if (v.getId() == R.id.bindServiceB) {
            intent.setClass(this, ShowServiceB.class);
            bindService(intent, connectionB, Context.BIND_AUTO_CREATE);
        } else if (v.getId() == R.id.unbindServiceB) {
            unbindService(connectionB);
        } else if (v.getId() == R.id.bindServiceC) {
            intent.setClass(this, ShowServiceC.class);
//            intent.setAction("android.intent.action.ShowServiceC");
//            intent.addCategory("android.intent.category.DEFAULT");

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                startForegroundService(intent);
//            }

            bindService(intent, connectionC, Context.BIND_AUTO_CREATE);

//            startService(intent);
        } else if (v.getId() == R.id.unbindServiceC) {
            unbindService(connectionC);

//            intent.setClass(this, ShowServiceC.class);
//            stopService(intent);
        } else if (v.getId() == R.id.startIntentService) {
            intent.setClass(this, ShowIntentService.class);
            startService(intent);
        } else if (v.getId() == R.id.sendCText) {
            int[] request = {1, 2, 123456};
            try {
                mAidlProxy.SerTestOut(request);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            LogUtil.getInstance().info("gzRemote", Arrays.toString(request));
        } else if (v.getId() == R.id.bindMessengerService) {
            intent.setClass(this, ShowMessengerService.class);
            bindService(intent, connectionMessenger, Context.BIND_AUTO_CREATE);
        } else if (v.getId() == R.id.unbindMessengerService) {
            unbindService(connectionMessenger);
        } else if (v.getId() == R.id.sendMessengerText) {
            Message message = new Message();
//            message.obj=new ParceString("asdasd");
            message.what=123;
            Bundle bundle=new Bundle();
            bundle.putString(" ","qweqwe");
            message.setData(bundle);
            message.replyTo=ClientMessenger;
            try {
                ServerMessengerProxy.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
//            LogUtil.getInstance().info("gzRemote",Arrays.toString(request));
        }


    }

    private ServiceConnection connectionB = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ShowServiceB showServiceB = ((BinderB) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private ServiceConnection connectionC = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAidlProxy = IRemote.Stub.asInterface(service);
            try {
                mAidlProxy.registerListener((IBinder) callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    private ServiceConnection connectionMessenger = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ServerMessengerProxy = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
