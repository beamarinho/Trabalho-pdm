package com.example.beatriz.academiainteligente;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetSenha extends AppCompatActivity {

    private EditText resetEmail;
    private Button resetSend;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_senha);

        inicializaComponentes();
        eventoClick();
    }

    private void eventoClick() {
        resetSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = resetEmail.getText().toString().trim();
                reiniciarSenha(email);
            }
        });
    }

    private void reiniciarSenha(String email) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(ResetSenha.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    alert("Um email foi enviado para alterar sua senha");
                    finish();
                }
                else{
                    alert("Email não encontrado");
                }
            }
        });
    }

    private void alert(String s) {
        Toast.makeText(ResetSenha.this,s,Toast.LENGTH_SHORT).show();
    }


    private void inicializaComponentes() {
        resetEmail  = (EditText) findViewById(R.id.resetEmail);
        resetSend = (Button) findViewById(R.id.resetSend);

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexão.getFirebaseAuth();
    }
}
