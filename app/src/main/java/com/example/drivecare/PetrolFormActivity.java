package com.example.drivecare;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class PetrolFormActivity extends AppCompatActivity {

    private EditText etName, etNumberPlate, etMobileNumber;
    private CheckBox cbCity, cbHighway, cb1ltr, cb3ltr, cb5ltr, cbPetrol, cbDiesel, cbCash, cbGpay, cbRural, cbCNG;
    private Button btnSubmit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petrol_form);

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
        cb1ltr = findViewById(R.id.checkBox1Ltr);
        cb3ltr = findViewById(R.id.checkBox3Ltr);
        cb5ltr = findViewById(R.id.checkBox5Ltr);
        cbPetrol = findViewById(R.id.checkBoxPetrol);
        cbDiesel = findViewById(R.id.checkBoxDiesel);
        cbCNG=findViewById(R.id.checkBoxCNG);
        cbRural=findViewById(R.id.cbRural);
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
        boolean isRuralSelected=cbRural.isChecked();
        boolean isPetrolSelected = cbPetrol.isChecked();
        boolean isDieselSelected = cbDiesel.isChecked();
        boolean isCNGSelected=cbCNG.isChecked();
        boolean is1ltr = cb1ltr.isChecked();
        boolean is3ltr = cb3ltr.isChecked();
        boolean is5ltr = cb5ltr.isChecked();
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

        int fuelTypeCount = 0;
        if (isPetrolSelected) {
            fuelTypeCount++;
        }
        if (isDieselSelected) {
            fuelTypeCount++;
        }
        if (isCNGSelected) {
            fuelTypeCount++;
        }
        if (fuelTypeCount > 1) {
            Toast.makeText(this, "Please select only one fuel type (Petrol, Diesel, or CNG)", Toast.LENGTH_SHORT).show();
            return;
        }

        int localeCount = 0;
        if (isCitySelected) {
            localeCount++;
        }
        if (isHighwaySelected) {
            localeCount++;
        }
        if (isRuralSelected) {
            localeCount++;
        }
        if (localeCount > 1) {
            Toast.makeText(this, "Please select only one current locale (City, Highway, or Rural)", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if petrol/diesel litre is selected when needed
        if ((isPetrolSelected || isDieselSelected) && !(is1ltr || is3ltr || is5ltr)) {
            Toast.makeText(this, "Please select Petrol or Diesel Litre", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if payment mode is selected
        if (!isCash && !isGpay) {
            Toast.makeText(this, "Please select mode of Payment", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if any multiple checkboxes are selected
        if (isCitySelected && (isHighwaySelected || isRuralSelected)
                || isHighwaySelected && (isCitySelected || isRuralSelected)
                || isRuralSelected && (isCitySelected || isHighwaySelected)
                || isGpay && isCash) {
            Toast.makeText(this, "Please remove multiple selections", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isCitySelected) {
            showCityDialog();
        } else if (isHighwaySelected) {
            showHighwayDialog();
        }
        else if(isRuralSelected){
            showRuralDialog();
        }
    }
    private void showCityDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_traffix, null);
        builder.setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        navigateToDashboard();
                    }
                })
                .show();
    }
    private void showHighwayDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_highway, null);
        builder.setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        navigateToDashboard();
                    }
                })
                .show();
    }
    private void showRuralDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_traffix, null);
        builder.setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        navigateToDashboard();
                    }
                })
                .show();
    }
    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        navigateToDashboard();
                    }
                })
                .show();
    }

    private void navigateToDashboard() {
        Intent intent = new Intent(PetrolFormActivity.this, DashboardActivity.class);
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
            Intent intent = new Intent(PetrolFormActivity.this, HomeActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
