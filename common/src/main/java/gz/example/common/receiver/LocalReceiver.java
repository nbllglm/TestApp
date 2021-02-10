package gz.example.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import gz.example.common.entity.Person;
import gz.example.common.util.ToastUtil;

public class LocalReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        Person person=bundle.getParcelable("person");
        ToastUtil.Show(getClass().getSimpleName()+person.toString());
//        abortBroadcast();
    }
}
