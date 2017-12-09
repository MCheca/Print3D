package com.example.usuario.prueba1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class BuscaUsu extends AppCompatActivity {

    public static String nombre;
    public static String tipo; //True Impresor False Interesado
    private EditText usu;
    private RadioButton imp;
    private RadioButton inte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_usu);
    }
    public void busca(View v){
        usu=findViewById(R.id.busu);
        nombre=usu.getText().toString();
        imp=findViewById(R.id.impresor);
        inte=findViewById(R.id.interesado);
        if(imp.isChecked()){
            tipo="Impresor";
        }
        else{
            tipo="Interesado";
        }
        Intent intento = new Intent(this, MenuInicio.class);
        startActivity(intento);
    }

    public void buscalo(View v){
        usu=findViewById(R.id.busu);
        nombre=usu.getText().toString();
        imp=findViewById(R.id.impresor);
        inte=findViewById(R.id.interesado);
        if(imp.isChecked()){
            tipo="Impresor";
        }
        else{
            tipo="Interesado";
        }
        Intent buscar =new Intent(this, Buscados.class);
        startActivity(buscar);
    }

    public static String getNom() {
        return nombre;
    }

    public static String getTipo() {
        return tipo;
    }
}
