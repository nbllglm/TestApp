package gz.example.common.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import gz.example.common.R;
import gz.example.common.abs.AbsActivity;
import gz.example.common.databinding.C_Binding;

public class ShowActivityC extends AbsActivity {
    private Context context;
    private C_Binding c_binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.c_activity);
        c_binding = DataBindingUtil.setContentView(this, R.layout.c_activity);
        c_binding.setHandle(new ShowActivityC.ShowCHandler());
        context = this;
    }

    public class ShowCHandler {

        public void goC() {

            Intent intent = new Intent();
            intent.setClass(context, ShowActivityC.class);
            context.startActivity(intent);

        }
    }

}
