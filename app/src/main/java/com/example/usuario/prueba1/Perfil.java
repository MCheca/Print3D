package com.example.usuario.prueba1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jumanji.prueba1.R;

public class Perfil extends AppCompatActivity {

    private TextView usuario,correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Usuario user= LogIn.getUser();
        if(user.getCuenta().equals("Interesado")) {
            setContentView(R.layout.activity_perfil);
            String corr=LogIn.getUser().getCorreo();
            String nombre=LogIn.getUser().getNombre();
            usuario=findViewById(R.id.usuario5);
            correo=findViewById(R.id.contacto);
            usuario.setText(nombre);
            correo.setText(corr);
        }
        else{
            setContentView(R.layout.activity_perfil);
            String corr=LogIn.getUser().getCorreo();
            String nombre=LogIn.getUser().getNombre();
            usuario=findViewById(R.id.usuario5);
            correo=findViewById(R.id.contacto);
            usuario.setText(nombre);
            correo.setText(corr);
        }
    }


    public void anuncios(View v){
        Intent registro2 = new Intent(Perfil.this, Epedido.class);
        startActivity(registro2);
    }

    public void mios (View v){
        Intent registro2 = new Intent(Perfil.this, Mpedidos.class);
        startActivity(registro2);
    }


    //Metodo que abre Pop Up
    public void PopUp(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("pepeviyuela@gmail.com")
                .setTitle("Su email")
                .setCancelable(false)
                .setNeutralButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void menu(View v){
        Intent registro =new Intent(Perfil.this, MenuInicio.class);
        startActivity(registro);
    }


}
