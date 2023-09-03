package com.example.drivecare;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PetrolPumpActivity extends AppCompatActivity {

    private ImageButton btnOutOfFuel;
    private ImageButton btnNearbyPump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petrol_pump);

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

        btnOutOfFuel = findViewById(R.id.btnout);
        btnNearbyPump = findViewById(R.id.btnpump);

        btnNearbyPump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PetrolPumpActivity.this, PetrolFormActivity.class));
            }
        });

        btnOutOfFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PetrolPumpActivity.this, PetrolLocationActivity.class));
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
            startActivity(new Intent(PetrolPumpActivity.this, HomeActivity.class));
            finish(); // Optional: finish the current activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
