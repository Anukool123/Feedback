package myapplication.examples.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sdk.feedback.Setup;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Main2Activity", "onResume()");
        Setup.enable(this, "xyz");
    }

    @Override
    protected void onPause() {
        Log.i("Main2Activity","onPause()");
        super.onPause();
        Setup.disable();
    }
}
