package gz.example.common.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gz.example.common.R;
import gz.example.common.abs.AbsActivity;
import gz.example.common.fragment.JokeFragment;
import gz.example.common.fragment.NewsFragment;
import gz.example.common.fragment.ShowFragmentA;
import gz.example.common.receiver.FirstReceiver;
import gz.example.common.receiver.LocalReceiver;
import gz.example.common.receiver.SecondReceiver;


public class HomeActivity extends AbsActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> nameList = Arrays.asList("笑话", "新闻","A");
    private int defaultSelect = 0;
    private FirstReceiver firstReceiver;
    private SecondReceiver secondReceiver;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        Intent intent = getIntent();
        Log.d("HomeActivity", "onCreate: " + intent.getIntExtra("A", 0));
        Log.d("HomeActivity", "onCreate: " + intent.getStringExtra("name"));
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        initView();

    }

    private void initView() {
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.pager);
        String s = new String("asd");
        JokeFragment jokeFragment = new JokeFragment();
        Bundle jokeBundle = new Bundle();
        jokeBundle.putString("parent", getClass().getSimpleName() + "Joke");
        jokeFragment.setArguments(jokeBundle);


        NewsFragment newsFragment = new NewsFragment();
        Bundle newsBundle = new Bundle();
        newsBundle.putString("parent", getClass().getSimpleName() + "news");
        newsFragment.setArguments(newsBundle);
        newsFragment.setTargetFragment(jokeFragment, NewsFragment.REQUEST_CODE);


//        ShowFragmentA showFragmentA=new ShowFragmentA();


        fragmentList.add(jokeFragment);
        fragmentList.add(newsFragment);
//        fragmentList.add(showFragmentA);

//        FragmentManager fragmentManager=getSupportFragmentManager();
//        FragmentTransaction ft=fragmentManager.beginTransaction();
//        ft.add(jokeFragment,"joke");
//        ft.add(newsFragment,"news");
//        ft.commit();
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return nameList.get(position);
            }
        });
        viewPager.setOffscreenPageLimit(1);





        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(defaultSelect).select();





        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
            View imag = view.findViewById(R.id.v_tab);
            TextView textView = view.findViewById(R.id.tv_tab);
            textView.setText(nameList.get(i));
            if (i == defaultSelect) {
                imag.setBackgroundColor(Color.parseColor("#41b24e"));
                textView.setTextColor(Color.parseColor("#41b24e"));
            } else {
                imag.setBackgroundColor(Color.parseColor("#dc143c"));
                textView.setTextColor(Color.parseColor("#dc143c"));
            }
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            tabLayout.getTabAt(i).setCustomView(view);

        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                View imag = view.findViewById(R.id.v_tab);
                TextView textView = view.findViewById(R.id.tv_tab);
                textView.setTextColor(Color.parseColor("#41b24e"));
                imag.setBackgroundColor(Color.parseColor("#41b24e"));
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                View imag = view.findViewById(R.id.v_tab);
                TextView textView = view.findViewById(R.id.tv_tab);
                imag.setBackgroundColor(Color.parseColor("#dc143c"));
                textView.setTextColor(Color.parseColor("#dc143c"));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


//        IntentFilter intentFilter1 = new IntentFilter();
//        intentFilter1.addAction("gz.example.common.broad1");
//        intentFilter1.setPriority(200);
//        firstReceiver = new FirstReceiver();
//        registerReceiver(firstReceiver, intentFilter1);
//
//        IntentFilter intentFilter2 = new IntentFilter();
//        intentFilter2.addAction("gz.example.common.broad1");
//        intentFilter2.setPriority(100);
//        secondReceiver = new SecondReceiver();
//        registerReceiver(secondReceiver, intentFilter2);
//
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("gz.example.common.broad1");
//        intentFilter3.setPriority(100);
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter3);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(firstReceiver);
        unregisterReceiver(secondReceiver);
        localBroadcastManager.unregisterReceiver(localReceiver);
        localBroadcastManager=null;
    }


}
