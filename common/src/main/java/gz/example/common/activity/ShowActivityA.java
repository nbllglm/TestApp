package gz.example.common.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gz.example.common.R;
import gz.example.common.abs.AbsActivity;
import gz.example.common.databinding.A_Binding;
import gz.example.common.entity.Joke;
import gz.example.common.util.ToastUtil;
import gz.example.common.view.CustomView;
import gz.example.common.viewmodel.ShowAViewModel;

public class ShowActivityA extends AbsActivity {

    private A_Binding aBinding;
    private ShowAViewModel viewModel;
    private AHandler handler = new AHandler();
    private CustomView customView;
    private List<String> arrays = new ArrayList<>();
    private Map<Integer, String> hashmnap = new HashMap<>();


    private static class AHandler extends Handler{
    }

    private void makeData() {
        arrays.clear();
        hashmnap.clear();
        char a = 100;
        for (int i = 0; i < 10; i++) {
            arrays.add(String.valueOf(a + i));
            hashmnap.put(i, arrays.get(i));
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Joke joke = bundle.getParcelable("joke");

        aBinding = DataBindingUtil.setContentView(this, R.layout.a_activity);
//        getLifecycle().addObserver(new LifecycleEventObserver() {
//            @Override
//            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
//
//            }
//        });
        aBinding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this, new ShowAViewModel.Factory(this)).get(ShowAViewModel.class);
        initObserver();

        viewModel.getJokeData().setValue(joke);
        aBinding.setViewModel(viewModel);
        customView = findViewById(R.id.customView);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewModel.update();
                //   customView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.translate));
            }
        }, 10000);
    }

    private void initObserver() {
        viewModel.getJokeData().observe(this, new Observer<Joke>() {
            @Override
            public void onChanged(Joke joke) {
                aBinding.setJoke(joke);
            }
        });
    }

    public void shuaxin() {
//        customView.invalidate();
        customView.requestLayout();
    }

    public void custClick() {
        ToastUtil.Show("asdasd");
    }

}
