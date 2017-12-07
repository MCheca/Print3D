package com.example.usuario.prueba1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Rpedidos extends AppCompatActivity {

    private EditText nombre, descrip, precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpedidos);
    }

    public void registro(View v){
        nombre=findViewById(R.id.nombre2);
        descrip=findViewById(R.id.desc2);
        precio=findViewById(R.id.presupuesto2);

        AdministradorOpenHelper admin = new AdministradorOpenHelper(this,
                "pedidos", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nom = nombre.getText().toString();
        String des = descrip.getText().toString();
        String  prec= precio.getText().toString();

        if(!nom.equals("")){
            if(!des.equals("")){
                if(!prec.equals("")){
                    if(!existe(nom)) {
                        String user = LogIn.getUser().getCorreo();
                        ContentValues registrar = new ContentValues();
                        registrar.put("nombre", nom);
                        registrar.put("usuario", user);
                        registrar.put("descripcion", des);
                        registrar.put("presupuesto", prec);

                        bd.insert("pedidos", null, registrar);

                        bd.close();
                        nombre.setText("");
                        descrip.setText("");
                        precio.setText("");


                        Toast.makeText(this, "Registro del pedido completado",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this, "Ya has realizado un pedido con ese nombre",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, "Introduce un presupuesto",
                            Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Introduce una descripcion",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Introduce un nombre",
                    Toast.LENGTH_SHORT).show();
        }
    }


    public void volver(View v){
        Intent registro2 = new Intent(Rpedidos.this, MenuInicio.class);
        startActivity(registro2);
    }

    public boolean existe(String s){
        boolean existe=false;
        String direccion=LogIn.getUser().getCorreo();
        AdministradorOpenHelper admin = new AdministradorOpenHelper(this,
                "pedidos", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery(
                "select descripcion,presupuesto from pedidos where (usuario='" + direccion + "' and nombre='" + s + "')", null);
        if (fila.moveToFirst()) {
            existe=true;
        }

        bd.close();
        return existe;
    }

    public void menu(View v){
        Intent registro =new Intent(Rpedidos.this, MenuInicio.class);
        startActivity(registro);
    }
}
