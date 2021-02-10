package gz.example.common.abs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import gz.example.common.BuildConfig;

public class AbsActivity extends AppCompatActivity {
    public static String TAG = BuildConfig.activityTAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, getClass().getSimpleName() + "    onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, getClass().getSimpleName() + "    onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, getClass().getSimpleName() + "    onStart: ");
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, getClass().getSimpleName() + "    onRestoreInstanceState: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, getClass().getSimpleName() + "    onResume: ");
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, getClass().getSimpleName() + "    onSaveInstanceState: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, getClass().getSimpleName() + "    onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, getClass().getSimpleName() + "    onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, getClass().getSimpleName() + "    onDestroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, getClass().getSimpleName() + "    onNewIntent: ");
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        Log.i(TAG, getClass().getSimpleName() + "    onBackPressed: ");
        super.onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
