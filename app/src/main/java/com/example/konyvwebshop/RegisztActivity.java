package com.example.konyvwebshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class RegisztActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String LOG_TAG = RegisztActivity.class.getName();
    private static final String PREF_KEY = RegisztActivity.class.getPackage().toString();
    private static final int SECRET_KEY = 99;

    EditText usernamedittext;
    EditText useremailedittext;
    EditText passwedittext;
    EditText passwagain;
    EditText phoneedittext;
    Spinner spinner;
    EditText adressedittex;
    RadioGroup accounttypegroup;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiszt);

        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);

        if(secret_key!=99){
            finish();
        }


        usernamedittext = findViewById(R.id.usernameedittext);
        useremailedittext = findViewById(R.id.useremailedittext);
        passwedittext = findViewById(R.id.passwedittext);
        passwagain = findViewById(R.id.passwagain);
        phoneedittext = findViewById(R.id.phoneedittext);
        spinner = findViewById(R.id.phonespinner);
        adressedittex = findViewById(R.id.adressedittext);
        accounttypegroup = findViewById(R.id.accounttypegroup);
        accounttypegroup.check(R.id.vasarloradiobutton);

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        String userN = preferences.getString("userN", "");
        String passW = preferences.getString("passW", "");

        usernamedittext.setText(userN);
        passwedittext.setText(passW);
        passwagain.setText(passW);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.phonemode, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Log.i(LOG_TAG, "onCreate");

    }

    public void regiszt(View view) {
        String userN = usernamedittext.getText().toString();
        String email = useremailedittext.getText().toString();
        String passW = passwedittext.getText().toString();
        String passWagain = passwagain.getText().toString();


        if(!passW.equals(passWagain)){
            Log.e(LOG_TAG, "A megadott jelszavak nem egyeznek.");
            return;
        }

        String phonenumber = phoneedittext.getText().toString();
        String phonetype = spinner.getSelectedItem().toString();
        String address = adressedittex.getText().toString();

        int checkedId = accounttypegroup.getCheckedRadioButtonId();
        RadioButton radioButton = accounttypegroup.findViewById(checkedId);
        String accounttypegroup = radioButton.getText().toString();



        Log.i(LOG_TAG, "Regisztrált: " + userN + ", e-mail: " + email);

        startshop();
    }

    public void megse(View view) {
        finish();
    }

    private void startshop(/* registered user data */){
        Intent intent = new Intent(this, ShoplistActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "onRestart");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selecteditem = parent.getItemAtPosition(position).toString();
        Log.i(LOG_TAG, selecteditem);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}