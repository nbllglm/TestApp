package gz.example.common.service.binder;

import android.os.Binder;

import java.lang.ref.WeakReference;

import gz.example.common.abs.AbsService;
import gz.example.common.service.ShowServiceB;

public class BinderB extends Binder {
    private WeakReference<ShowServiceB> showServiceBWeakReference;

    public BinderB(ShowServiceB service) {
        this.showServiceBWeakReference = new WeakReference<>(service);
    }

    public ShowServiceB getService() {
        return showServiceBWeakReference.get();
    }
}
