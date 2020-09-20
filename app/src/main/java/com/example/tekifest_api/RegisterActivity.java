package com.example.tekifest_api;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    // Variables de tremplate registers
    private EditText edtname_user;
    private EditText edtemail;
    private EditText edtpassword;
    private Button btnregister;

    //firebase
    FirebaseAuth mAuth;
    DatabaseReference mDatabase = FirebaseDatabase;

    //Variables a utilizar en validaciones
    private String email = "";
    private String password = "";
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Inicializacion de varibles
        edtemail = findViewById(R.id.email_user);
        edtpassword = findViewById(R.id.password_user);
        edtname_user = findViewById(R.id.name);
        btnregister = findViewById(R.id.register);
        //Firebase
        mAuth = FirebaseAuth.getInstance();

        //Accion del boton register
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edtname_user.getText().toString();
                email = edtemail.getText().toString();
                password = edtpassword.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    if(password.length() >= 6) {
                        registerUser();
                    }else {
                        Toast.makeText(RegisterActivity.this, "¡Lo siento!, No se completo el regisro", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "¡Completa todos los campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("email", email);
                    map.put("password", password);

                    String id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                                finish();
                            }else {
                                Toast.makeText(RegisterActivity.this, "Error al crear los datos del usuario", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else {
                    Toast.makeText(RegisterActivity.this, "No se completo el registro de este usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}