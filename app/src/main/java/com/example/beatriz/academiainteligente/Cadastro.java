package com.example.beatriz.academiainteligente;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cadastro extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button btnRegistrar, btnVoltar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializarComponete();
        eventoClick();
    }

    private void eventoClick() {
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();
                criarUser(email, senha);
            }
        });
    }

    private void criarUser(String email, String senha){
        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    alert("Usuario Cadastrado com Sucesso");
                    Intent i = new Intent(Cadastro.this, Exercicios.class);
                    startActivity(i);
                    finish();
                }
                else{
                    alert("Erro de Cadastro");
                }
            }
        });
    }

    private void alert(String msg){
        Toast.makeText(Cadastro.this,msg,Toast.LENGTH_SHORT).show();
    }

    private void inicializarComponete(){
        editEmail = (EditText) findViewById(R.id.cadEmail);
        editSenha = (EditText) findViewById(R.id.cadSenha);
        btnRegistrar = (Button) findViewById(R.id.btnCadastrar);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexão.getFirebaseAuth();
    }
}
