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

import com.example.jumanji.prueba1.R;

public class Epedido extends AppCompatActivity {

    private EditText nombre,descripcion,presupuesto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epedido);
    }

    public void consultanombre(View v) {
        nombre=findViewById(R.id.nombre2);
        descripcion=findViewById(R.id.desc2);
        presupuesto=findViewById(R.id.presupuesto2);
        String direccion=LogIn.getUser().getCorreo();
        AdministradorOpenHelper admin = new AdministradorOpenHelper(this, "pedidos", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod= nombre.getText().toString();

        if(!cod.equals("")) {

            Cursor fila = bd.rawQuery(
                    "select descripcion,presupuesto from pedidos where (usuario='" + direccion + "' and nombre='" + cod + "')", null);
            if (fila.moveToFirst()) {
                descripcion.setText(fila.getString(0));
                presupuesto.setText(fila.getString(1));
            } else {
                Toast.makeText(this, "No has realizado pedidos con ese nombre",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "No has introducido el nombre",
                    Toast.LENGTH_SHORT).show();
        }

        bd.close();

    }


    public void borro(View v) {

        nombre=findViewById(R.id.nombre2);
        descripcion=findViewById(R.id.desc2);
        presupuesto=findViewById(R.id.presupuesto2);
        String direccion=LogIn.getUser().getCorreo();

        AdministradorOpenHelper admin = new AdministradorOpenHelper(this,
                "pedidos", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String cod= nombre.getText().toString();
        String cod2= descripcion.getText().toString();
        String cod3= presupuesto.getText().toString();

        if(!cod.equals("")&&!cod2.equals("")&&!cod3.equals("")) {
            int cant = bd.delete("pedidos", "(usuario='" + direccion + "' and nombre='" + cod + "' and descripcion='"+cod2+"' and presupuesto='"+cod3+"')", null);
            bd.close();
            nombre.setText("");
            descripcion.setText("");
            presupuesto.setText("");
            if (cant == 1)
                Toast.makeText(this, "Se borró el pedido con éxito",
                        Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "No has realizado nunca ese pedido",
                        Toast.LENGTH_SHORT).show();

        }
        else if(!cod.equals("")||!cod2.equals("")||!cod3.equals("")){
            Toast.makeText(this, "Hay algún campo vacío",
                    Toast.LENGTH_SHORT).show();
        }
    }



    public void menu(View v){
        Intent registro =new Intent(Epedido.this, MenuInicio.class);
        startActivity(registro);
    }
}
