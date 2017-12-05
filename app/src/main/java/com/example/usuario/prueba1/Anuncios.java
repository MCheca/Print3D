package com.example.usuario.prueba1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.jumanji.prueba1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Anuncios extends AppCompatActivity {

    /*ArrayList<String> listviewTitle = new ArrayList<String>();
    ArrayList<String> listviweShortDescription = new ArrayList<String>();
    ArrayList<String> listviewPresupuesto = new ArrayList<String>();*/
    String[] imagen = new String[]{Integer.toString(R.drawable.yodaicono),Integer.toString(R.drawable.kirby),Integer.toString(R.drawable.mario),Integer.toString(R.drawable.yoshi)};
    public static int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios);

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        AdministradorOpenHelper admin = new AdministradorOpenHelper(this, "pedidos", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery(
                "select usuario, nombre,descripcion,presupuesto from pedidos", null);
        if(fila.moveToFirst()) {
            int contador=0;
            //Recorremos todos los registros hasta que no hayan mas
            do{
                HashMap<String, String> hm = new HashMap<String, String>();
                hm.put("listview_title", fila.getString(1));
                hm.put("listview_discription",fila.getString(2));
                hm.put("listview_image", imagen[contador]);
                hm.put("listview_presupuesto", "Presupuesto máximo:\n"+fila.getString(3)+"€");
                aList.add(hm);
                contador++;
            }
            while(fila.moveToNext());


            String[] from = {"listview_image", "listview_title", "listview_discription", "listview_presupuesto"};
            int[] to = {R.id.listview_image, R.id.titulo, R.id.descrip, R.id.presu};




            SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_activity, from, to);
            ListView androidListView = (ListView) findViewById(R.id.list_view);
            androidListView.setAdapter(simpleAdapter);

            /*ArrayAdapter<HashMap<String,String>> adaptador =
                    new ArrayAdapter<HashMap<String, String>>(this,android.R.layout.simple_list_item_1,aList);
            lista.setAdapter(adaptador);*/


            // Add a header to the ListView
            LayoutInflater inflater = getLayoutInflater();
            //ListView androidListView = (ListView) findViewById(R.id.list_view);
            ViewGroup header = (ViewGroup) inflater.inflate(R.layout.listview_header, androidListView, false);
            androidListView.addHeaderView(header);

            androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //  Position contiene la posicion del item seleccionado
                    posicion=position;
                    Intent myIntent = new Intent(Anuncios.this, Proyecto.class);
                    startActivityForResult(myIntent,0);
                }
            });


        }
    }

    public void menu(View v){
        Intent registro =new Intent(Anuncios.this, MenuInicio.class);
        startActivity(registro);
    }

    public static int getPosicion(){
        return posicion;
    }
}