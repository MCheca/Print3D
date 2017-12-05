package com.example.usuario.prueba1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jumanji.prueba1.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;


public class Proyecto extends AppCompatActivity {

    private String usuario, nombre, descripcion, presupuesto;
    private int posi;
    private TextView usu, nom, des, presu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proyecto);
        usu=findViewById(R.id.usu);
        nom=findViewById(R.id.nom);
        des=findViewById(R.id.des);
        presu=findViewById(R.id.pres);
        ImageView imageView = (ImageView) findViewById(R.id.trabajo1);

        Picasso.with(this)
                .load("https://target.scene7.com/is/image/Target/18779833?wid=520&hei=520&fmt=pjpeg")
                .into(imageView);

        posi=Anuncios.getPosicion();
        AdministradorOpenHelper admin = new AdministradorOpenHelper(this, "pedidos", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery(
                "select usuario, nombre,descripcion,presupuesto from pedidos", null);
        if(fila.moveToFirst()) {
            int contador = 0;
            //Recorremos todos los registros hasta que no hayan mas
            while (contador < (posi-1)) {
                fila.moveToNext();
                contador++;
            }

            usuario=fila.getString(0);
            nombre=fila.getString(1);
            descripcion=fila.getString(2);
            presupuesto=fila.getString(3);

            nom.setText(nombre);
            des.setText(descripcion);
            presu.setText("Presupuesto máximo: \n"+presupuesto+" €");
        }
        else{
            Toast.makeText(this, "No hay disponible",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void mail(View v){
        String[] TO = {usuario}; //Direcciones email  a enviar.
        String[] CC = {usuario}; //Direcciones email con copia.

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Asunto de prueba");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Quiero imprimir esto, puedes hacerlo?"); // * configurar email aquí!s

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email."));
            Log.i("EMAIL", "Enviando email...");
        }
        catch (android.content.ActivityNotFoundException e) {
            Toast.makeText(this, "NO existe ningún cliente de email instalado!.", Toast.LENGTH_SHORT).show();
        }
    }
    public void menu(View v){
        Intent registro =new Intent(Proyecto.this, MenuInicio.class);
        startActivity(registro);
    }
}