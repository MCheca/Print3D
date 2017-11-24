package com.example.usuario.prueba1;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jumanji.prueba1.R;


public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



    }

    public void cambiologin(View v){
        Button iniciar=(Button) findViewById(R.id.inicia);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iniciar =new Intent(Home.this, LogIn.class);
                startActivity(iniciar);
            }
        });
    }

    public void cambioregistro(View v){
        //Crea Botones
        Button registro=(Button) findViewById(R.id.registra);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro =new Intent(Home.this, MainActivity.class);
                startActivity(registro);
            }
        });
    }

}


