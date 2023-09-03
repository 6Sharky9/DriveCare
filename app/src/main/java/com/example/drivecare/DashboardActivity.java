package com.example.drivecare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drivecare.R;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton emergencyButton;
    private ImageButton mechanicButton;
    private ImageButton petrolPumpButton;
    private ImageButton TowingButton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        emergencyButton = findViewById(R.id.imageButton7);
        mechanicButton = findViewById(R.id.imageButton3);
        petrolPumpButton = findViewById(R.id.imageButton5);
        TowingButton = findViewById(R.id.imageButton6);

        emergencyButton.setOnClickListener(this);
        mechanicButton.setOnClickListener(this);
        petrolPumpButton.setOnClickListener(this);
        TowingButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.signout) {
            // Handle signout option here
            // For example, you can navigate back to the HomeActivity
            Intent intent = new Intent(DashboardActivity.this, HomeActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == R.id.imageButton7) {
            Intent emergencyIntent = new Intent(DashboardActivity.this, EmergencyActivity.class);
            startActivity(emergencyIntent);
        }
        else if (viewId == R.id.imageButton3) {
            Intent mechanicIntent = new Intent(DashboardActivity.this, MechanicActivity.class);
            startActivity(mechanicIntent);
        }
        else if (viewId == R.id.imageButton5) {
            Intent petrolPumpIntent = new Intent(DashboardActivity.this, PetrolPumpActivity.class);
            startActivity(petrolPumpIntent);
        }
        else if (viewId == R.id.imageButton6) {
            Intent emergencyContactIntent = new Intent(DashboardActivity.this, TowingServiceActivity.class);
            startActivity(emergencyContactIntent);
        }

    }
}
