package com.example.usuario.prueba1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


public class MenuInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);
    }

    public void proyecto(View v){
        Intent registro2 = new Intent(MenuInicio.this, Anuncios.class);
        startActivity(registro2);
    }

    public void add(View v){
        Intent registro2 = new Intent(MenuInicio.this, Rpedidos.class);
        startActivity(registro2);
    }

    public void perfil(View v){
        Intent registro2 = new Intent(MenuInicio.this, Perfil.class);
        startActivity(registro2);
    }

    public void usuario(View v){
        Intent registro2 = new Intent(MenuInicio.this, Usuarios.class);
        startActivity(registro2);
    }
}
