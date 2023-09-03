package com.example.drivecare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MechanicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic);
        ImageButton req_mech = findViewById(R.id.mech_btn);
        ImageButton gar_loct = findViewById(R.id.gar_btn);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.arrow_img_foreground);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        req_mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MechanicFormActivity
                Intent intent = new Intent(MechanicActivity.this, MechanicFormActivity.class);
                startActivity(intent);
            }
        });

        gar_loct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start GarageFinderActivity
                Intent intent = new Intent(MechanicActivity.this, GarageFinderActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.signout) {
            // Handle signout option here
            // For example, you can navigate back to the HomeActivity
            Intent intent = new Intent(MechanicActivity.this, HomeActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    }