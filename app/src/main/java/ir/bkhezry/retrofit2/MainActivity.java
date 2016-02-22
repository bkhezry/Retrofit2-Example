package ir.bkhezry.retrofit2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.hiveActivityBTN)
    Button hiveActivityBTN;
    @Bind(R.id.stackoverflowActivityBTN)
    Button stackoverflowActivityBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.hiveActivityBTN) void showHiveActivity() {
        startActivity(new Intent(this, HiveActivity.class));

    }
    @OnClick(R.id.stackoverflowActivityBTN) void showStackoverflowActivity() {
        startActivity(new Intent(this, StackoverflowActivity.class));
    }
}
