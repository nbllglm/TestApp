package gz.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class MainService extends Service {

    private List<Person> list;

    @Override
    public void onCreate() {
        super.onCreate();
        list = new ArrayList<>();
    }

    private PersonManager.Stub stub = new PersonManager.Stub() {
        @Override
        public List<Person> getPersonList() throws RemoteException {
            Log.d("asdasdasd" + "   " + this.getClass().getName(), list.toString());
            return list;
        }

        @Override
        public void addPerson(Person person) throws RemoteException {
            Log.d("asdasdasd" + "   " + this.getClass().getName(), person.toString());
            list.add(person);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
//        return null;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
