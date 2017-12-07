package com.example.usuario.prueba1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mpedidos extends AppCompatActivity {

    /*ArrayList<String> listviewTitle = new ArrayList<String>();
    ArrayList<String> listviweShortDescription = new ArrayList<String>();
    ArrayList<String> listviewPresupuesto = new ArrayList<String>();*/
    String[] imagen = new String[]{Integer.toString(R.drawable.yodaicono),Integer.toString(R.drawable.kirby),Integer.toString(R.drawable.mario),Integer.toString(R.drawable.yoshi)};
    private static int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpedidos);

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        AdministradorOpenHelper admin = new AdministradorOpenHelper(this, "pedidos", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String correo=LogIn.getUser().getCorreo();

        Cursor fila = bd.rawQuery(
                "select usuario, nombre,descripcion,presupuesto from pedidos where usuario='"+correo+"'", null);
        if(fila.moveToFirst()) {
            int contador=0;
            //Recorremos todos los registros hasta que no hayan mas
            do{
                HashMap<String, String> hm = new HashMap<String, String>();
                hm.put("listview_title", fila.getString(1));
                hm.put("listview_discription",fila.getString(2));
                hm.put("listview_image", Integer.toString(R.drawable.pieza));
                hm.put("listview_presupuesto", "Presupuesto máximo:\n"+fila.getString(3)+"€");
                aList.add(hm);
                contador++;
            }
            while(fila.moveToNext());

            String[] from = {"listview_image", "listview_title", "listview_discription", "listview_presupuesto"};
            int[] to = {R.id.listview_image, R.id.titulo, R.id.descrip, R.id.presu};

            SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_mpedidos, from, to);
            ListView androidListView = (ListView) findViewById(R.id.list_view);
            androidListView.setAdapter(simpleAdapter);

            // Que pone el header al list view tambien
            LayoutInflater inflater = getLayoutInflater();
            ViewGroup header = (ViewGroup) inflater.inflate(R.layout.listview_headermpedidos, androidListView, false);
            androidListView.addHeaderView(header);

            androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //  Position contiene la posicion del item seleccionado
                    posicion=position;
                    Intent myIntent = new Intent(Mpedidos.this, MProyecto.class);
                    startActivityForResult(myIntent,0);
                }
            });

        }
        else{
            setContentView(R.layout.vacio);
            Toast.makeText(this, "No has hecho ningún pedido",
                    Toast.LENGTH_SHORT).show();
        }
    }


    public void menu(View v){
        Intent registro =new Intent(Mpedidos.this, MenuInicio.class);
        startActivity(registro);
    }

    public static int getPosicion(){
        return posicion;
    }
}
