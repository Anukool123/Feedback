package myapplication.examples.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.sdk.feedback.Setup;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        Log.i("MainActivity", "onResume()");
        super.onResume();
        Setup.enable(this, "xyz");
    }

    @Override
    protected void onPause() {
        Log.i("MainActivity","onPause()");
        super.onPause();
        Setup.disable();
    }

    public void launchAnotherActivity(View view){
        Intent intent =new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}
