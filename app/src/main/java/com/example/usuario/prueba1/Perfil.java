package com.example.usuario.prueba1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Perfil extends AppCompatActivity {

    private TextView usuario,correo,cuent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Usuario user= LogIn.getUser();
        if(user.getCuenta().equals("Interesado")) {
            setContentView(R.layout.activity_perfil);
            String corr=LogIn.getUser().getCorreo();
            String nombre=LogIn.getUser().getNombre();
            String cuenta=LogIn.getUser().getCuenta();
            usuario=findViewById(R.id.usuario5);
            correo=findViewById(R.id.contacto);
            cuent=findViewById(R.id.cuenta);
            usuario.setText(nombre);
            correo.setText(corr);
            cuent.setText(cuenta);
        }
        else{
            setContentView(R.layout.activity_perfil);
            String corr=LogIn.getUser().getCorreo();
            String nombre=LogIn.getUser().getNombre();
            String cuenta=LogIn.getUser().getCuenta();
            usuario=findViewById(R.id.usuario5);
            correo=findViewById(R.id.contacto);
            cuent=findViewById(R.id.cuenta);
            usuario.setText(nombre);
            correo.setText(corr);
            cuent.setText(cuenta);
        }
    }

    public void mios (View v){
        Intent registro2 = new Intent(Perfil.this, Mpedidos.class);
        startActivity(registro2);
    }


    public void menu(View v){
        Intent registro =new Intent(Perfil.this, MenuInicio.class);
        startActivity(registro);
    }


}
