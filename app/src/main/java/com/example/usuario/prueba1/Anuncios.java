// Android Custom ListView with Image and Text Tutorial with Example and Source Code
package com.example.usuario.prueba1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.jumanji.prueba1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Anuncios extends AppCompatActivity {



    // Array of strings for ListView Title
    String[] listviewTitle = new String[]{
            "Mano para Sara", "Yoda", "ListView Title 3", "ListView Title 4",
            "ListView Title 5", "ListView Title 6", "ListView Title 7", "ListView Title 8",
    };


    int[] listviewImage = new int[]{
            R.drawable.mano, R.drawable.yoda, R.drawable.img1, R.drawable.img1,
            R.drawable.img1, R.drawable.img1, R.drawable.img1, R.drawable.img1,
    };

    String[] listviewShortDescription = new String[]{
            "Sara necesita una mano nueva", "Tengo tiempo libre y quiero un Yoda en 3D", "Android ListView Short Description", "Android ListView Short Description",
            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
    };

    String[] listviewPresupuesto = new String[]{
            "5€", "5€", "Android ListView Short Description", "Android ListView Short Description",
            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios);

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 8; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            hm.put("listview_presupuesto", "Presupuesto máximo:\n"+listviewPresupuesto[i]);
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription", "listview_presupuesto"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description, R.id.listview_presupuesto};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_activity, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);
        // Add a header to the ListView
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.listview_header,androidListView,false);
        androidListView.addHeaderView(header);
    }
}