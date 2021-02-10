package gz.example.common.util;

import android.util.Log;

import gz.example.common.BuildConfig;

public class LogUtil {
    private LogUtil() {

    }

    private static class InstanceClass {
        public static final LogUtil logUtil = new LogUtil();
    }

    public static LogUtil getInstance() {
        return InstanceClass.logUtil;
    }

    public <T> void info(String TAG, T message) {
        Log.i(TAG, message + "");
    }

    public <T> void info(T message) {
        Log.i(BuildConfig.viewTAG, message + "");
    }

    public <T> void debug(String TAG, T message) {
        Log.d(TAG, message + "");
    }

    public <T> void debug(T message) {
        Log.d(BuildConfig.viewTAG, message + "");
    }
}
