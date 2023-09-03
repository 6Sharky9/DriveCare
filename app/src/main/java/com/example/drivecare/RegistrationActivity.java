package com.example.drivecare;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText phoneNumberEditText;
    private Button saveButton;
    private Button goBack;
    private DBHelper dbHelper;

    public static final int REGISTRATION_REQUEST_CODE = 1;

    private Spinner locationSpinner;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        dbHelper = new DBHelper(this);
        locationSpinner = findViewById(R.id.locationSpinner);
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.location_options));
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        usernameEditText = findViewById(R.id.ed6);
        passwordEditText = findViewById(R.id.ed7);
        phoneNumberEditText = findViewById(R.id.ed9);
        saveButton = findViewById(R.id.regg);
        goBack = findViewById(R.id.goBackButton);

        phoneNumberEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10), getNumericFilter()});

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();
                String location = locationSpinner.getSelectedItem().toString();

                if (username.isEmpty() || password.isEmpty() || phoneNumber.isEmpty() || location.equals("Select Location")) {
                    Toast.makeText(RegistrationActivity.this, "Please fill all the details and select a location", Toast.LENGTH_SHORT).show();
                } else if (phoneNumber.length() != 10) {
                    Toast.makeText(RegistrationActivity.this, "Phone number should be 10 digits", Toast.LENGTH_SHORT).show();
                } else if (!isValidPassword(password)) {
                    Toast.makeText(RegistrationActivity.this, "Invalid password. It should contain at least 7 characters, including a capital letter, a number, and a special character.", Toast.LENGTH_SHORT).show();
                }  else if (location.equals("Select Location")) {
                    Toast.makeText(RegistrationActivity.this, "Please select a location", Toast.LENGTH_SHORT).show();
                }else {
                    boolean isUserInserted = dbHelper.insertUser(username, password);

                    if (isUserInserted) {
                        Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationActivity.this, HomeActivity.class);
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Account already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            private boolean isValidPassword(String password) {
                // Password validation regex pattern
                String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,}$";
                return password.matches(passwordPattern);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private InputFilter getNumericFilter() {
        return new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isDigit(source.charAt(i))) {
                        Toast.makeText(RegistrationActivity.this, "Phone number should contain only digits", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }
        };
    }
}
