package com.example.fronttttttttttttttttttt;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.RequiresApi;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class SignUp extends Activity {
    private Button suivant ;
    private ImageButton retourS;
    protected EditText nomprenom ,mail , tel , mdp , mdpc , dnn;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup1);
        mail=(EditText)findViewById(R.id.mails);
        tel=(EditText)findViewById(R.id.tels);
        nomprenom=(EditText)findViewById(R.id.nomprenoms);
        mdp=(EditText)findViewById(R.id.mdps);
        mdpc=(EditText)findViewById(R.id.mdpcs);
        suivant =(Button) findViewById(R.id.suivant);
        retourS =(ImageButton) findViewById(R.id.retourS);
        dnn=(EditText)findViewById(R.id.dn);

        suivant.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String email= mail.getText().toString().trim();
                String tell= tel.getText().toString().trim();
                String mdp1= mdp.getText().toString().trim();
                String mdp2= mdpc.getText().toString().trim();
                String nompre= nomprenom.getText().toString().trim();
                String daten= dnn.getText().toString().trim();




                if(nompre.isEmpty() ){
                    nomprenom.setError("Champ obligatoire");
                    nomprenom.requestFocus();
                    return;
                }
                if(daten.isEmpty()  ){
                    dnn.setError("Champ obligatoire");
                    dnn.requestFocus();
                    return;
                }else{
                            try {
                                LocalDate.parse(daten);
                            } catch (DateTimeParseException dtpe) {
                              dnn.setError("Date invalide");
                              dnn.requestFocus();
                              return;
                            }

                    }
                if(email.isEmpty()){
                    mail.setError("Champ obligatoire");
                    mail.requestFocus();
                    return;
                }else{
                      if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                          mail.setError("Adresse mail invalide");
                          mail.requestFocus();
                          return;
                      }
                }
                if(tell.isEmpty()  ){
                    tel.setError("Champ obligatoire");
                    tel.requestFocus();
                    return;
                } else{
                    if(!Patterns.PHONE.matcher(tell).matches()){
                        tel.setError("Numero de telephone invalide");
                        tel.requestFocus();
                        return;
                    }
                }
                if(mdp1.isEmpty()  ){
                    mdp.setError("Champ obligatoire");
                    mdp.requestFocus();
                    return;
                }
                if(mdp1.length()<6 ){
                    mdp.setError("Mot de passe trop court au moins 6 caracteres");
                    mdp.requestFocus();
                    return;
                }
                if(!mdp2.equals(mdp1) ){
                    mdpc.setError("Les mots de passe ne sont pas identiques");
                    mdpc.requestFocus();
                    return;
                }
                Intent i =new Intent(SignUp.this,SignUp2.class);
                i.putExtra("nom_prenom" ,nompre);
                i.putExtra("email" ,email);
                i.putExtra("telephone" ,tell);
                i.putExtra("mdp" ,mdp1);
                i.putExtra("dn" ,daten);
                startActivity(i);
            }
        });
        retourS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, MainActivity.class));
            }
        });
    }




}


