package com.example.usuario.prueba1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Marcos on 02/12/2017.
 */

public class Proyect {
    private String nombre, d, pres;

    public Proyect(String n, String desc, String pre){
            nombre=n;
            d=desc;
            pres=pre;
    }

    public String getNom(){
        return nombre;
    }


    public String getDesc(){
        return d;
    }

    public String getUser(){
        return pres;
    }
}
