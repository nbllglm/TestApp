package gz.example.common;

import android.app.Application;
import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;


public class CommonApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        CrashReport.initCrashReport(getApplicationContext(), BuildConfig.buglyID, true);
    }
}
