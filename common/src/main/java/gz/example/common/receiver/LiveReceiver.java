package gz.example.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import gz.example.common.service.ShowServiceC;

public class LiveReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        intent.setClass(context, ShowServiceC.class);
        context.startService(intent);
    }
}
