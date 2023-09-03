package com.example.drivecare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class TowingServiceActivity extends AppCompatActivity {
    private EditText etName, etNumberPlate, etMobileNumber;
    private CheckBox cbCity, cbHighway, cb2wh, cb3wh, cb4wh, cbCash, cbGpay, cbRural;
    private Button btnSubmit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_towing_service);
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
        etName = findViewById(R.id.editTextName);
        etNumberPlate = findViewById(R.id.editTextNumberPlate);
        etMobileNumber = findViewById(R.id.editTextMobile);
        cbCity = findViewById(R.id.cbCity);
        cbHighway = findViewById(R.id.cbHighway);
        cbRural = findViewById(R.id.cbRural);
        cb2wh = findViewById(R.id.checkBox2wheel);
        cb3wh = findViewById(R.id.checkBox3wheel);
        cb4wh = findViewById(R.id.checkBox4wheel);
        cbCash = findViewById(R.id.checkBoxCash);
        cbGpay = findViewById(R.id.checkBoxGPay);
        btnSubmit = findViewById(R.id.butnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndSubmitForm();
            }
        });
    }

    private void validateAndSubmitForm() {
        String name = etName.getText().toString().trim();
        String numberPlate = etNumberPlate.getText().toString().trim();
        String mobileNumber = etMobileNumber.getText().toString().trim();
        boolean isCitySelected = cbCity.isChecked();
        boolean isHighwaySelected = cbHighway.isChecked();
        boolean isRuralSelected = cbRural.isChecked();
        boolean is2wh = cb2wh.isChecked();
        boolean is3wh = cb3wh.isChecked();
        boolean is4wh = cb4wh.isChecked();
        boolean isCash = cbCash.isChecked();
        boolean isGpay = cbGpay.isChecked();

        if (TextUtils.isEmpty(name)) {
            etName.setError("Please enter your name");
            etName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(numberPlate)) {
            etNumberPlate.setError("Please enter your number plate");
            etNumberPlate.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mobileNumber)) {
            etMobileNumber.setError("Please enter your mobile number");
            etMobileNumber.requestFocus();
            return;
        }

        if (!TextUtils.isDigitsOnly(mobileNumber)) {
            etMobileNumber.setError("Mobile number should contain only digits");
            etMobileNumber.requestFocus();
            return;
        }

        if (!is2wh && !is3wh && !is4wh) {
            showAlert("Error", "Please select Vehicle Type");
            return;
        }

        if (!isCash && !isGpay) {
            showAlert("Error", "Please select mode of Payment");
            return;
        }

        int selectedSectionCount = 0;
        if (isCitySelected) {
            selectedSectionCount++;
        }
        if (isHighwaySelected) {
            selectedSectionCount++;
        }
        if (isRuralSelected) {
            selectedSectionCount++;
        }

        if (selectedSectionCount > 1) {
            showAlert("Error", "Please select only one option for City, Highway, or Rural");
            return;
        }

        if (selectedSectionCount == 0) {
            showAlert("Error", "Please select City, Highway, or Rural");
            return;
        }

        showSuccessDialog();
    }

    private void showSuccessDialog() {
        String otp = generateOTP();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Success")
                .setMessage("Your request for Towing was Successful.\nPlease share this OTP with the tow assistant: " + otp + ".\nPlease do not switch off your phone.\nThank You")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle dialog button click if needed
                        navigateToDashboard();
                    }
                })
                .show();
    }

    private String generateOTP() {
        Random random = new Random();
        int otpValue = random.nextInt(9000) + 1000; // Generate a random 4-digit number between 1000 and 9999
        return String.valueOf(otpValue);
    }

    private void showAlert(String title, String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void navigateToDashboard() {
        Intent intent = new Intent(TowingServiceActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
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
            Intent intent = new Intent(TowingServiceActivity.this, HomeActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
