package com.example.beatriz.academiainteligente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {

    private TextView textEmail, textID;
    private Button btnLogOut;

    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        inicializarComponentes();

        eventoClick();
    }

    private void eventoClick() {
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Conexão.logOut();
                finish();
            }
        });
    }

    private void inicializarComponentes() {
        textEmail = (TextView) findViewById(R.id.viewEmail);
        textID = (TextView) findViewById(R.id.viewID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexão.getFirebaseAuth();
        user = Conexão.getFirebaseUser();
        verificaUser();
    }

    private void verificaUser() {
        if(user == null){
            finish();
        }
        else{
            textEmail.setText("E-mail:"+user.getPhoneNumber());
            textID.setText("ID:"+user.getUid());
        }

    }
}
