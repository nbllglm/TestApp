package gz.example.common.service.binder;

import android.os.Binder;
import android.os.IBinder;

import gz.example.common.abs.AbsService;

public class BinderA extends Binder {
    private AbsService service;

    public BinderA(AbsService service) {
        this.service = service;
    }

}
