package gz.example.common.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import gz.example.common.R;
import gz.example.common.abs.AbsActivity;
import gz.example.common.databinding.B_Binding;

public class ShowActivityB extends AbsActivity {
    private Context context;
    private B_Binding b_binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b_binding = DataBindingUtil.setContentView(this, R.layout.b_activity);
        b_binding.setHandle(new ShowBHandler());
        context = this;
    }

    public class ShowBHandler {

        public void goD() {
            Intent intent = new Intent();
            intent.setClass(context, ShowActivityD.class);
            context.startActivity(intent);
        }
    }

}
