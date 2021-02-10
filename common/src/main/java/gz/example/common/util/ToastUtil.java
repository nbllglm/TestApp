package gz.example.common.util;

import android.widget.Toast;

import gz.example.common.CommonApplication;

public class ToastUtil {
    public static void Show(String value){
        Toast.makeText(CommonApplication.context,value,Toast.LENGTH_SHORT).show();
    }
}
