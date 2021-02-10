package gz.example.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import gz.example.common.entity.Person;
import gz.example.common.util.ToastUtil;

public class StickyBroadcastReceiver extends BroadcastReceiver {
    private static final String PERMISSION = "gz.example.common.permission.sticky.receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        int checkCallingOrSelfPermission = context.checkCallingOrSelfPermission(PERMISSION);
        if (PackageManager.PERMISSION_GRANTED == checkCallingOrSelfPermission) //权限判断
        {
            ToastUtil.Show("授权成功");
        } else {
            ToastUtil.Show("授权失败");
            throw new RuntimeException("permission denied");
        }
        Bundle bundle=intent.getExtras();
        Person person=bundle.getParcelable("person");
        ToastUtil.Show(getClass().getSimpleName()+person.toString());

    }
}
