package com.example.fronttttttttttttttttttt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.firestore.FirebaseFirestore;
import java.sql.Timestamp;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Button signUp,login, amb,adm;
    FirebaseFirestore db;
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);
        login =(Button) findViewById(R.id.login);
        signUp=(Button) findViewById(R.id.signup);
        amb=(Button) findViewById(R.id.ambulancier);
        adm=(Button) findViewById(R.id.admin);
        db = FirebaseFirestore.getInstance();


        date=new Date();
        Date yesterday = new Date(date.getTime() - (1000 * 60 * 60 * 24));
        yesterday.setSeconds(0);
        yesterday.setMinutes(0);
        yesterday.setHours(0);
        Timestamp timestamp = new Timestamp(yesterday.getTime());


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });
        amb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Ambulence.class));
            }
        });
        adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Admin.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }
}