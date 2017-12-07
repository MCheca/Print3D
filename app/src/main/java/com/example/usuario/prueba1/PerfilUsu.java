package com.example.usuario.prueba1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilUsu extends AppCompatActivity {

    private String usu, cor, cue;
    private TextView usuario,correo,cuenta;
    private int posi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usu);
        usuario=findViewById(R.id.nombre);
        correo=findViewById(R.id.correo);
        cuenta=findViewById(R.id.cuenta);

        posi=Usuarios.getPosicion();
        AdministradorOpenHelper admin = new AdministradorOpenHelper(this, "usuarios", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery(
                "select usuario,email, cuenta from usuarios", null);
        if(fila.moveToFirst()) {
            int contador = 0;
            //Recorremos todos los registros hasta que no hayan mas
            while (contador < (posi-1)) {
                fila.moveToNext();
                contador++;
            }

            usu=fila.getString(0);
            cor=fila.getString(1);
            cue=fila.getString(2);

            usuario.setText(usu);
            correo.setText(cor);
            cuenta.setText(cue);
        }
        else{
            Toast.makeText(this, "No hay disponible",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void mail(View v){
        if(Usuarios.getCo().equalsIgnoreCase(LogIn.getUser().getCorreo())){
            Toast.makeText(this, "No puedes hacer esto. Eres tu.",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            String[] TO = {usu}; //Direcciones email  a enviar.
            String[] CC = {usu}; //Direcciones email con copia.

            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Asunto de prueba");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "¿Quiero imprimir esto, puedes hacerlo?"); // * configurar email aquí!s

            try {
                startActivity(Intent.createChooser(emailIntent, "Enviar email."));
                Log.i("EMAIL", "Enviando email...");
            } catch (android.content.ActivityNotFoundException e) {
                Toast.makeText(this, "NO existe ningún cliente de email instalado!.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void menu(View v){
        Intent registro =new Intent(PerfilUsu.this, MenuInicio.class);
        startActivity(registro);
    }


}
