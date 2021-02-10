package gz.example.common.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import gz.example.common.R;
import gz.example.common.abs.AbsFragment;
import gz.example.common.view.GZFrameLayout;

public class ShowFragmentA extends AbsFragment {
    private GZFrameLayout gzFrameLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        gzFrameLayout=null;
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + "    onCreateView: ");
        gzFrameLayout= (GZFrameLayout) LayoutInflater.from(getContext()).inflate(R.layout.a_fragment,null,false);
        return gzFrameLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
