package myapplication.examples.com.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sdk.feedback.Setup;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment();


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

    private void addFragment()
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flFirstFragment, new InstructionFragment());
        ft.commit();
    }
}
