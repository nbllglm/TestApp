package gz.example.common.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import gz.example.common.R;
import gz.example.common.abs.AbsActivity;
import gz.example.common.databinding.D_Binding;

public class ShowActivityD extends AbsActivity {
    private Context context;
    private D_Binding d_binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_activity);
        d_binding = DataBindingUtil.setContentView(this, R.layout.d_activity);
        d_binding.setHandle(new ShowActivityD.ShowDHandler());
        context = this;
    }

    public class ShowDHandler {

        public void goA() {
            Intent intent = new Intent();
            intent.setClass(context, ShowActivityA.class);
            context.startActivity(intent);
        }

        public void goB() {
            Intent intent = new Intent();
            intent.setClass(context, ShowActivityB.class);
            context.startActivity(intent);
        }

        public void goC() {
            Intent intent = new Intent();
            intent.setClass(context, ShowActivityC.class);
            context.startActivity(intent);
        }

        public void goD() {
            Intent intent = new Intent();
            intent.setClass(context, ShowActivityD.class);
            context.startActivity(intent);
        }
    }

}
