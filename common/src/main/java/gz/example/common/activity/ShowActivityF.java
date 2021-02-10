package gz.example.common.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Arrays;

import gz.example.common.IListener;
import gz.example.common.IRemote;
import gz.example.common.R;
import gz.example.common.abs.AbsActivity;
import gz.example.common.service.ShowIntentService;
import gz.example.common.service.ShowServiceA;
import gz.example.common.service.ShowServiceB;
import gz.example.common.service.ShowServiceC;
import gz.example.common.service.binder.BinderB;
import gz.example.common.util.LogUtil;

public class ShowActivityF extends AbsActivity implements View.OnClickListener {
    private IRemote mAidlProxy;
    private TextView textView;
    private IListener callback = new CallbackStub();

    private class CallbackStub extends IListener.Stub {
        @Override
        public void onResponse(String str) throws RemoteException {
            textView.setText(str);
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
        textView = findViewById(R.id.responseText);
        findViewById(R.id.sendText).setOnClickListener(this);
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
        } else if (v.getId() == R.id.sendText) {
            int[] request = {1, 2, 123456};
            try {
                mAidlProxy.SerTestOut(request);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            LogUtil.getInstance().info("gzRemote",Arrays.toString(request));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
