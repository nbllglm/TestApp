package gz.example.common.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import gz.example.common.R;
import gz.example.common.abs.AbsFragment;
import gz.example.common.adapter.JokeAdapter;
import gz.example.common.entity.Joke;
import gz.example.common.entity.S2CJoke;
import gz.example.common.util.MMKVUtil;
import gz.example.common.util.OkUtil;
import gz.example.common.util.ToastUtil;


public class JokeFragment extends AbsFragment {

    private RecyclerView rvJoke;
    private View view;
    private JokeAdapter jokeAdapter;
    private List<Joke> jokes = new ArrayList<>();
    private static Handler handler = new Handler();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (getArguments() != null) {
//            ToastUtil.Show(getArguments().getString("parent"));
        }
        if (view == null) {
            view = inflater.inflate(R.layout.joke_fragment, container, false);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvJoke = view.findViewById(R.id.rv_joke_list);
        rvJoke.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        jokeAdapter = new JokeAdapter(jokes, getContext());
        rvJoke.setAdapter(jokeAdapter);


        String json = MMKVUtil.getInstance().getJsonData("json");
        S2CJoke s2CJoke = new Gson().fromJson(json, S2CJoke.class);
        jokes.clear();
        jokes.addAll(s2CJoke.getResult());
        jokeAdapter.notifyDataSetChanged();

//        new OkUtil().setUrl("http://v.juhe.cn/joke/randJoke.php")
//                .isPost()
//                .setParam("key", "33561c168fd782d595e4b5ba1bb85271")
//                .setParam("page" ,"1")
//                .setParam("pagesize","4")
//                .setCallBack(new OkUtil.GzCallBack<S2CJoke>() {
//                    @Override
//                    public void success(S2CJoke s2CJoke) {
//                        jokes.clear();
//                        jokes.addAll(s2CJoke.getResult());
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                jokeAdapter.notifyDataSetChanged();
//                            }
//                        });
//                    }
//                }).build();


    }

    public void qwe(String qwe) {
        ToastUtil.Show(qwe);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode==NewsFragment.REQUEST_CODE){
                ToastUtil.Show(data.getIntExtra("key",0)+"");
            }
        }
    }
}