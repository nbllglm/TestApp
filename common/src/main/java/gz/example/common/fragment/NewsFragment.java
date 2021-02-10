package gz.example.common.fragment;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import gz.example.common.R;
import gz.example.common.abs.AbsFragment;
import gz.example.common.activity.RemoteActivity;
import gz.example.common.activity.ShowActivityA;
import gz.example.common.activity.ShowActivityB;
import gz.example.common.activity.ShowActivityC;
import gz.example.common.activity.ShowActivityE;
import gz.example.common.activity.ShowActivityF;
import gz.example.common.entity.Joke;
import gz.example.common.entity.Person;

public class NewsFragment extends AbsFragment implements View.OnClickListener {
    private View view;
    private Context context;
    private LocalBroadcastManager localBroadcastManager;
    private Handler handler = new Handler();
    public static int REQUEST_CODE = 1111;
    private TextView tvSend;

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
            view = inflater.inflate(R.layout.news_fragment, container, false);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        localBroadcastManager = LocalBroadcastManager.getInstance(context);

        view.findViewById(R.id.tv_broadcast1).setOnClickListener(this);

        view.findViewById(R.id.tv_broadcast2).setOnClickListener(this);

        view.findViewById(R.id.tv_broadcast3).setOnClickListener(this);

        view.findViewById(R.id.tv_broadcast4).setOnClickListener(this);
        view.findViewById(R.id.tv_A).setOnClickListener(this);

        view.findViewById(R.id.tv_B).setOnClickListener(this);

        view.findViewById(R.id.tv_C).setOnClickListener(this);

        view.findViewById(R.id.tv_E).setOnClickListener(this);

        view.findViewById(R.id.tv_F).setOnClickListener(this);
        view.findViewById(R.id.tv_remote).setOnClickListener(this);
        tvSend=view.findViewById(R.id.tv_send);
        tvSend.setOnClickListener(this);
        view.findViewById(R.id.tv_fragment).setOnClickListener(this);

        view.findViewById(R.id.tv_anr).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        if (v.getId() == R.id.tv_broadcast1) {
            intent.setAction("gz.example.common.broad1");
            Person person = new Person("A", 1);
            Bundle bundle = new Bundle();
            bundle.putParcelable("person", person);
            intent.putExtras(bundle);
            context.sendBroadcast(intent);
        } else if (v.getId() == R.id.tv_broadcast2) {
            intent.setAction("gz.example.common.broad1");
            Person person = new Person("A", 1);
            Bundle bundle = new Bundle();
            bundle.putParcelable("person", person);
            intent.putExtras(bundle);
            context.sendOrderedBroadcast(intent, null);
        } else if (v.getId() == R.id.tv_broadcast3) {
            intent.setAction("gz.example.common.broad1");
            Person person = new Person("A", 1);
            Bundle bundle = new Bundle();
            bundle.putParcelable("person", person);
            intent.putExtras(bundle);
            localBroadcastManager.sendBroadcast(intent);
        } else if (v.getId() == R.id.tv_broadcast4) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent1 = new Intent("gz.example.common.stickybroad1");
                    intent1.setComponent(new ComponentName("gz.example.common.receiver", "gz.example.common.receiver.StickyBroadcastReceiver"));
                    Person person = new Person("A", 1);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("person", person);
                    intent1.putExtras(bundle);
                    context.sendStickyBroadcast(intent1);
                }
            }, 1000);
        } else if (v.getId() == R.id.tv_A) {
            intent.setClass(context, ShowActivityA.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("joke", new Joke("asdasdasdasdasd"));
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (v.getId() == R.id.tv_B) {
            intent.setClass(context, ShowActivityB.class);
            startActivity(intent);
        } else if (v.getId() == R.id.tv_C) {
            intent.setClass(context, ShowActivityC.class);
            startActivity(intent);
        } else if (v.getId() == R.id.tv_E) {
            intent.setClass(context, ShowActivityE.class);
            startActivity(intent);
        } else if (v.getId() == R.id.tv_F) {
            intent.setClass(context, ShowActivityF.class);
            startActivity(intent);
        } else if (v.getId() == R.id.tv_remote) {
            intent.setClass(context, RemoteActivity.class);
            startActivity(intent);


        } else if (v.getId() == R.id.tv_send) {



        } else if (v.getId() == R.id.tv_fragment) {
//            JokeFragment jokeFragment= (JokeFragment) getFragmentManager().getFragments().get(0);
////            jokeFragment.qwe("调用Joke");
//            setTargetFragment(jokeFragment,REQUEST_CODE);
            intent.putExtra("key", 100);
            getTargetFragment().onActivityResult(REQUEST_CODE, Activity.RESULT_OK, intent);

        } else if (v.getId() == R.id.tv_anr) {

        }
    }


}
