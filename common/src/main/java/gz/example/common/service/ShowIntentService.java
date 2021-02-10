package gz.example.common.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

import androidx.annotation.Nullable;

import gz.example.common.util.LogUtil;

public class ShowIntentService extends IntentService {

    public ShowIntentService(String name) {
        super(name);
    }

    public ShowIntentService() {
        super("gz");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        for (int i = 0; i < 30; i++) {
            LogUtil.getInstance().info("ShowIntentService", "    " + i);
            SystemClock.sleep(1 * 1000);
        }
    }
}
