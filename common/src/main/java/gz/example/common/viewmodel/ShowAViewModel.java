package gz.example.common.viewmodel;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;

import gz.example.common.CommonApplication;
import gz.example.common.R;
import gz.example.common.activity.ShowActivityA;
import gz.example.common.activity.ShowActivityD;
import gz.example.common.entity.Joke;

public class ShowAViewModel extends ViewModel {
    private MutableLiveData<Joke> jokeData = new MutableLiveData<>();

    private WeakReference<ShowActivityA> showActivityAWeakReference;
    public  static String url="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1558026985,2729915815&fm=26&gp=0.jpg";

    ShowAViewModel(WeakReference<ShowActivityA> activity) {
        showActivityAWeakReference = activity;
    }

    public static class Factory implements ViewModelProvider.Factory {
        private WeakReference<ShowActivityA> showActivityAWeakReference;

        public Factory(ShowActivityA activity) {
            showActivityAWeakReference = new WeakReference<ShowActivityA>(activity);
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ShowAViewModel(showActivityAWeakReference);
        }
    }

    public MutableLiveData<Joke> getJokeData() {
        return jokeData;
    }

    public void goA() {

        Intent intent = new Intent();
        intent.setClass(showActivityAWeakReference.get(), ShowActivityA.class);
        showActivityAWeakReference.get().startActivity(intent);

    }

    public void update() {
        getJokeData().getValue().setContent("跳转A");
    }

    @BindingAdapter({"url"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(CommonApplication.context).load(url).into(view);
    }

    public void shua() {
        showActivityAWeakReference.get().shuaxin();
    }

    public void cust() {
        showActivityAWeakReference.get().custClick();
    }
}
