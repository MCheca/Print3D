package com.example.usuario.prueba1;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Judith on 22/11/2017.
 */

public class AdministradorOpenHelper extends SQLiteOpenHelper{


    public AdministradorOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(usuario text not null, email text primary key, contraseña text not null, cuenta text not null)");
        db.execSQL("create table pedidos(codigo integer primary key autoincrement, usuario text, nombre text, descripcion text, presupuesto text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        //Borra la tabla
        db.execSQL("drop table if exists usuarios");
        db.execSQL("create table usuarios(usuario text not null, email text primary key, contraseña text, cuenta text)");
        db.execSQL("drop table if exists pedidos");
        db.execSQL("create table pedidos(codigo integer primary key autoincrement, usuario text, nombre text, descripcion text, presupuesto text)");
    }
}
