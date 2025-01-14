package com.example.fronttttttttttttttttttt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    private Button login1;
    private ImageButton retourL;
    private EditText mail , mdp ;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login1 =(Button) findViewById(R.id.login1);
        retourL =(ImageButton) findViewById(R.id.retourL);
        mail=(EditText)findViewById(R.id.emailL);
        mdp=(EditText)findViewById(R.id.PasswL);
        mAuth = FirebaseAuth.getInstance();
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= mail.getText().toString().trim();
                String mdp1= mdp.getText().toString().trim();

                if(email.isEmpty()){
                    mail.setError("Champ obligatoire");
                    mail.requestFocus();
                    return;
                }
                else{
                    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        mail.setError("Adresse mail invalide");
                        mail.requestFocus();
                        return;
                    }
                }
                if(mdp1.isEmpty() ){
                    mdp.setError("Champ obligatoire");
                    mdp.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email,mdp1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            DocumentReference reference;
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
                            String currentId;
                            currentId=user.getUid();
                            reference=db.collection("user").document(currentId);
                            reference.get()
                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if(task.getResult().exists()){
                                                Toast.makeText(Login.this , "Vous etes connecté(e)",Toast.LENGTH_SHORT).show();
                                                Intent i =new Intent(Login.this,Menu.class);
                                                startActivity(i);
                                            }
                                            else {
                                                Toast.makeText(Login.this , "Email ou mot de passe incorrecte(s). Veuillez reessayer",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        }
                        else {
                            Toast.makeText(Login.this , "Email ou mot de passe incorrecte(s). Veuillez reessayer",Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });
        retourL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFirstP();
            }
        });

    }




    public void openFirstP(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
