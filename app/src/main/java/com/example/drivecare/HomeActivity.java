package com.example.drivecare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button createAccountButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new DBHelper(this);

        usernameEditText = findViewById(R.id.ed3);
        passwordEditText = findViewById(R.id.ed4);
        loginButton = findViewById(R.id.login);
        createAccountButton = findViewById(R.id.reg);

        // Set a click listener on the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                // Check if the entered username and password match the registered details
                if (dbHelper.validateLogin(enteredUsername, enteredPassword)) {
                    Toast.makeText(HomeActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                    // Start the dashboard activity
                    Intent dashboardIntent = new Intent(HomeActivity.this, DashboardActivity.class);
                    startActivity(dashboardIntent);
                    finish();
                } else {
                    Toast.makeText(HomeActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set a click listener on the create account button
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the registration activity
                Intent registrationIntent = new Intent(HomeActivity.this, RegistrationActivity.class);
                startActivityForResult(registrationIntent, RegistrationActivity.REGISTRATION_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RegistrationActivity.REGISTRATION_REQUEST_CODE && resultCode == RESULT_OK) {
            String registeredUsername = data.getStringExtra("username");
            String registeredPassword = data.getStringExtra("password");
            dbHelper.insertUser(registeredUsername, registeredPassword);
        }
    }
}
