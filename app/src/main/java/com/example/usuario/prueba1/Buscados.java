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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Buscados extends AppCompatActivity {

    private EditText buscar;
    public static int posicion;
    private static ArrayList<String> nombre,correo,cuenta;
    private static String n,co,cu;
    private ImageView imagen;
    private String busca;
    private String tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscados);
        setContentView(R.layout.activity_usuarios);
        nombre = new ArrayList<String>();
        correo = new ArrayList<String>();
        cuenta = new ArrayList<String>();
        busca=BuscaUsu.getNom();
        Toast.makeText(this, "Estas buscando '"+busca+"'",
                Toast.LENGTH_SHORT).show();
        tipo=BuscaUsu.getTipo();
        imagen = findViewById(R.id.listview_image);
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        AdministradorOpenHelper admin = new AdministradorOpenHelper(this, "usuarios", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila  = bd.rawQuery(
                "select usuario, email,cuenta from usuarios where usuario like'%" + busca + "%' and cuenta='"+tipo+"'", null);
        if (fila.moveToFirst()) {
            //Recorremos todos los registros hasta que no hayan mas
            do {
                HashMap<String, String> hm = new HashMap<String, String>();
                hm.put("listview_image", Integer.toString(R.drawable.usuario));
                hm.put("listview_title", "\n" + fila.getString(0));
                hm.put("listview_discription", fila.getString(1));
                hm.put("listview_presupuesto", fila.getString(2));
                nombre.add(fila.getString(0));
                correo.add(fila.getString(1));
                cuenta.add(fila.getString(2));
                aList.add(hm);
            }
            while (fila.moveToNext());

            String[] from = {"listview_image", "listview_title", "listview_discription", "listview_presupuesto"};
            int[] to = {R.id.listview_image, R.id.nombre, R.id.correo, R.id.cuenta};

            SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_usuarios, from, to);
            ListView androidListView = (ListView) findViewById(R.id.list_view);
            androidListView.setAdapter(simpleAdapter);

            // Que pone el header al list view tambien
            LayoutInflater inflater = getLayoutInflater();
            ViewGroup header = (ViewGroup) inflater.inflate(R.layout.listview_headerusuarios, androidListView, false);
            androidListView.addHeaderView(header);

            androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //  Position contiene la posicion del item seleccionado
                    posicion = position;
                    n = nombre.get(position - 1);
                    co = correo.get(position - 1);
                    cu = cuenta.get(position - 1);

                    Intent myIntent = new Intent(Buscados.this, PerfilUsu.class);
                    startActivityForResult(myIntent, 0);
                }
            });

        } else {
            setContentView(R.layout.listview_headerusuarios);
            Toast.makeText(this, "No hay usuarios registrados",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void menu(View v){
        Intent registro =new Intent(this, MenuInicio.class);
        startActivity(registro);
    }
    public void buscar(View v){
        Intent busc =new Intent(this, BuscaUsu.class);
        startActivity(busc);
    }
    }

