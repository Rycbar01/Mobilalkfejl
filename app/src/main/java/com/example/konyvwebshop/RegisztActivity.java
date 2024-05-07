package com.example.konyvwebshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class RegisztActivity extends AppCompatActivity {
    private static final String LOG_TAG = RegisztActivity.class.getName();
    private static final String PREF_KEY = MainActivity.class.getPackage().toString();

    EditText usernamedittext;
    EditText useremailedittext;
    EditText passwedittext;
    EditText passwagain;

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

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        String userN = preferences.getString("userN", "");
        String passW = preferences.getString("passW", "");

        usernamedittext.setText(userN);
        passwedittext.setText(passW);
        passwagain.setText(passW);

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

        Log.i(LOG_TAG, "Regisztrált: " + userN + ", e-mail: " + email);
    }

    public void megse(View view) {
        finish();
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
}