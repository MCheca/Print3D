package com.example.usuario.prueba1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MProyecto extends AppCompatActivity {

    private String usuario, nombre, descripcion, presupuesto;
    private int posi;
    private TextView nom, des, presu;
    private Button usu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mproyecto);
        nom=findViewById(R.id.nom);
        des=findViewById(R.id.des);
        presu=findViewById(R.id.pres);

        posi=Anuncios.getPosicion();
        AdministradorOpenHelper admin = new AdministradorOpenHelper(this, "pedidos", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery(
                "select usuario, nombre,descripcion,presupuesto from pedidos where usuario='"+LogIn.getUser().getCorreo()+"'", null);
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
            presu.setText(presupuesto+"€");
        }
        else{
            Toast.makeText(this, "No hay disponible",
                    Toast.LENGTH_SHORT).show();
        }
    }


    public void eliminar(View v) {
        String direccion = LogIn.getUser().getCorreo();
        AdministradorOpenHelper admin = new AdministradorOpenHelper(this,
                "pedidos", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();

        int cant = bd.delete("pedidos", "(usuario='" + direccion + "' and nombre='" + nombre + "' and descripcion='" + descripcion + "' and presupuesto='" + presupuesto + "')", null);
        bd.close();
        if (cant == 1){
            Toast.makeText(this, "Se borró el pedido con éxito",
                    Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(MProyecto.this, Mpedidos.class);
        startActivityForResult(myIntent, 0);
        }
        else{
        Toast.makeText(this, "No has podido borrar el proyecto",
                Toast.LENGTH_SHORT).show();
        }

    }

    public void menu(View v){
        Intent registro =new Intent(MProyecto.this, MenuInicio.class);
        startActivity(registro);
    }

}
