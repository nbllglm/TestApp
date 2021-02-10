package gz.example.common.service;

import android.os.HandlerThread;

public class ShowHandlerThread extends HandlerThread {
    public ShowHandlerThread(String name) {
        super(name);
    }

    public ShowHandlerThread(String name, int priority) {
        super(name, priority);
    }
}
