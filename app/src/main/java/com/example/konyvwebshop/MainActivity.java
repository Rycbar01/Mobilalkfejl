package com.example.konyvwebshop;

import static com.example.konyvwebshop.R.id.ratingbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StorageReference;


public class MainActivity extends AppCompatActivity {



    Button button;
    RatingBar ratingbar;
    private static final String TAG = MainActivity.class.getName();
    private static final String PREF_KEY = MainActivity.class.getPackage().toString();
    private static final int SECRET_KEY = 98;

    EditText userNET;
    EditText passWET;

    private SharedPreferences preferences;
    private FirebaseAuth mAuth;
    //private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        userNET = findViewById(R.id.edittextuserN);
        passWET = findViewById(R.id.edittextpassword);

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();

        Log.i(TAG, "onCreate");



    }



    public void bejelentkezes(View view) {

    String userN = userNET.getText().toString();
    String passW = passWET.getText().toString();

   // Log.i(TAG, "Bejelentkezett: " + userN + ", jelszó: " + passW);
        mAuth.signInWithEmailAndPassword(userN, passW).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "Sikeres felhasználói bejelentkezés");
                    startList();
                } else {
                    Log.d(TAG, "Felhasználói bejelentkezés sikertelen");
                    Toast.makeText(MainActivity.this, "Felhasználói bejelentkezés sikertelen: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void startshop(){
        Intent intent = new Intent(this, ShoplistActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    private void startList(){
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    public void regiszt(View view) {
        Intent intent = new Intent(this, RegisztActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userN", userNET.getText().toString());
        editor.putString("passW", passWET.getText().toString());
        editor.apply();

        Log.i(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    public void loginGuest(View view) {
        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "Sikeres anonim felhasználói bejelentkezés");
                    startList();
                } else {
                    Log.d(TAG, "Anonim felhasználói bejelentkezés sikertelen");
                    Toast.makeText(MainActivity.this, "Anonim felhasználói bejelentkezés sikertelen: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}