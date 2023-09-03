package com.example.drivecare;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

import com.example.drivecare.HomeActivity;
import com.example.drivecare.R;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_DELAY=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_DELAY);
    }
}