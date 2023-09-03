package com.example.drivecare;

import android.Manifest;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.Random;

public class MechanicFormActivity extends AppCompatActivity {

    private EditText etName, etNumberPlate, etMobileNumber, etExtraInfo;
    private CheckBox cb4wh, cb3wh, cb2wh, cbTyre, cbBattery, cbGear, cbOther, cbCash, cbGPay, cbCity, cbRural, cbHighway;
    private Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_form);
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
        etExtraInfo = findViewById(R.id.editTextExtraInfo);
        cb4wh = findViewById(R.id.checkBox4wh);
        cb3wh = findViewById(R.id.checkBox3wh);
        cb2wh = findViewById(R.id.checkBox2wh);
        cbTyre = findViewById(R.id.checkBoxtyre);
        cbBattery = findViewById(R.id.checkBoxbattery);
        cbGear = findViewById(R.id.checkBoxgear);
        cbOther = findViewById(R.id.checkBoxother);
        cbCash = findViewById(R.id.checkBoxCash);
        cbGPay = findViewById(R.id.checkBoxGPay);
        cbCity = findViewById(R.id.cbCity);
        cbRural = findViewById(R.id.cbrural);
        cbHighway = findViewById(R.id.cbHighway);
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
        String extraInfo = etExtraInfo.getText().toString().trim();
        boolean is4wh = cb4wh.isChecked();
        boolean is3wh = cb3wh.isChecked();
        boolean is2wh = cb2wh.isChecked();
        boolean isTyre = cbTyre.isChecked();
        boolean isBattery = cbBattery.isChecked();
        boolean isGear = cbGear.isChecked();
        boolean isOther = cbOther.isChecked();
        boolean isCash = cbCash.isChecked();
        boolean isGpay = cbGPay.isChecked();
        boolean isCitySelected = cbCity.isChecked();
        boolean isRuralSelected = cbRural.isChecked();
        boolean isHighwaySelected = cbHighway.isChecked();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(numberPlate) || TextUtils.isEmpty(mobileNumber)) {
            Toast.makeText(this, "Please fill in all the required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidMobileNumber(mobileNumber)) {
            Toast.makeText(this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedVehicleCount = 0;
        if (is4wh) selectedVehicleCount++;
        if (is3wh) selectedVehicleCount++;
        if (is2wh) selectedVehicleCount++;

        int selectedIssueCount = 0;
        if (isTyre) selectedIssueCount++;
        if (isBattery) selectedIssueCount++;
        if (isGear) selectedIssueCount++;
        if (isOther) selectedIssueCount++;

        int selectedPaymentCount = 0;
        if (isCash) selectedPaymentCount++;
        if (isGpay) selectedPaymentCount++;

        int selectedLocationCount = 0;
        if (isCitySelected) selectedLocationCount++;
        if (isRuralSelected) selectedLocationCount++;
        if (isHighwaySelected) selectedLocationCount++;

        if (selectedVehicleCount != 1 || selectedIssueCount > 1 || selectedPaymentCount != 1 || selectedLocationCount != 1) {
            Toast.makeText(this, "Please select the correct options for each section", Toast.LENGTH_SHORT).show();
            return;
        }

        String otp = generateOTP();
        String message = "Your request for Mechanic was successful.\nPlease be patient while a mechanic will try to contact you for the provided " + mobileNumber
                + ".\nShare the following OTP with the mechanic: " + otp                + ".\nPlease do not switch off your phone during this process.\nThank You";

        showSuccessDialog(message);

        clearFormFields();


    }

    private boolean isValidMobileNumber(String mobileNumber) {
        return mobileNumber.length() == 10;
    }

    private void showSuccessDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Success")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle dialog button click if needed
                        navigateToDashboard();
                    }

                    private void navigateToDashboard() {
                        Intent dashboardIntent = new Intent(MechanicFormActivity.this, DashboardActivity.class);
                        startActivity(dashboardIntent);
                        finish();
                    }

                })
                .show();

    }

    private String generateOTP() {
        Random random = new Random();
        int otp = random.nextInt(9000) + 1000;
        return String.valueOf(otp);
    }

    private void clearFormFields() {
        etName.getText().clear();
        etNumberPlate.getText().clear();
        etMobileNumber.getText().clear();
        etExtraInfo.getText().clear();
        cb4wh.setChecked(false);
        cb3wh.setChecked(false);
        cb2wh.setChecked(false);
        cbTyre.setChecked(false);
        cbBattery.setChecked(false);
        cbGear.setChecked(false);
        cbOther.setChecked(false);
        cbCash.setChecked(false);
        cbGPay.setChecked(false);
        cbCity.setChecked(false);
        cbRural.setChecked(false);
        cbHighway.setChecked(false);
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
            Intent intent = new Intent(MechanicFormActivity.this, HomeActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
