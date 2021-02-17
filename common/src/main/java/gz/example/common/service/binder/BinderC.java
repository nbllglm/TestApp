package gz.example.common.service.binder;

import android.os.IBinder;
import android.os.RemoteException;

import gz.example.common.IListener;
import gz.example.common.IRemote;


public class BinderC extends IRemote.Stub {
    private IListener callBackListener;

    @Override
    public void registerListener(IBinder iBinder) throws RemoteException {
        this.callBackListener = IListener.Stub.asInterface(iBinder);
    }

    @Override
    public void unregisterListener(IBinder iBinder) throws RemoteException {
        if (this.callBackListener == IListener.Stub.asInterface(iBinder)) {
            this.callBackListener = null;
        }
    }

    @Override
    public void SerTestOneWay(int[] pa) throws RemoteException {
        pa[0] = 999;
        pa[1] = 888;
    }

    @Override
    public int SerTestIn(int[] pa) throws RemoteException {
        pa[0] = 999;
        pa[1] = 888;
        if (callBackListener != null) {
            callBackListener.onResponse(pa[2] + "");
        }
        return 1;
    }

    @Override
    public int SerTestOut(int[] pa) throws RemoteException {
        pa[0] = 999;
        pa[1] = 888;
        if (callBackListener != null) {
            callBackListener.onResponse(pa[2] + "");
        }
        return 2;
    }

    @Override
    public int SerTestInout(int[] pa) throws RemoteException {
        pa[0] = 999;
        pa[1] = 888;
        if (callBackListener != null) {
            callBackListener.onResponse(pa[2] + "");
        }
        return 3;
    }
}
