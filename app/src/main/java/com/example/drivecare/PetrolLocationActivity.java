package com.example.drivecare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PetrolLocationActivity extends AppCompatActivity {
    Button b1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_petrol_location);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        b1=(Button)findViewById(R.id.back);
        String location = "petrol pump near me"; // The location to search for

        Uri mapUri = Uri.parse("https://www.google.com/maps/search/?api=1&query="  + Uri.encode(location));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent); }
        else {
            Toast.makeText(this, "Google Maps is not installed on your device.", Toast.LENGTH_SHORT).show();
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PetrolLocationActivity.this,DashboardActivity.class);
                startActivity(intent);
                finish();
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
        int itemId = item.getItemId();
        if (itemId == R.id.signout) {
            startActivity(new Intent(PetrolLocationActivity.this, HomeActivity.class));
            finish(); // Optional: finish the current activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}