package myapplication.examples.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sdk.feedback.Setup;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Setup.enable(this, "xyz");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Setup.disable();
    }


}
