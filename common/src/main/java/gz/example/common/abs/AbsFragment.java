package gz.example.common.abs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import gz.example.common.BuildConfig;

public class AbsFragment extends Fragment {
    public static String TAG = BuildConfig.fragmentTAG;

    @Override
    public void onAttach(@NonNull Context context) {
        Log.i(TAG, getClass().getSimpleName() + "    onAttach: ");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + "    onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + "    onCreateView: ");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + "    onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + "    onStart: ");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + "    onResume: ");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, getClass().getSimpleName() + "    onPause: ");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, getClass().getSimpleName() + "    onStop: ");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, getClass().getSimpleName() + "    onDestroyView: ");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + "    onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, getClass().getSimpleName() + "    onDetach: ");
        super.onDetach();
    }

}
