package com.example.usuario.prueba1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LogIn extends AppCompatActivity {

    private EditText nombre,contra;
    public static Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //nombre=(EditText)findViewById(R.id.email);
        //contra=(EditText)findViewById(R.id.password);
    }


    public void iniciosesion(View v) {
        nombre=findViewById(R.id.email);
        contra=findViewById(R.id.password);
        AdministradorOpenHelper admin = new AdministradorOpenHelper(this,
                "usuarios", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String correo = nombre.getText().toString();
        if(!correo.equals("")) {
            Cursor fila = bd.rawQuery(
                    "select usuario,contraseña, cuenta from usuarios where email='" + correo + "'", null);

            if (fila.moveToFirst()) {
                String confirmar = contra.getText().toString();
                if(!confirmar.equals("")) {
                    String confi = fila.getString(1);
                    if (confirmar.equals(confi)) {
                        Toast.makeText(this, "Has iniciado sesion",
                                Toast.LENGTH_SHORT).show();
                        //AQUI SE CAMBIA DE XML


                        user = new Usuario(fila.getString(0),correo,fila.getString(2));
                        Intent registro2 = new Intent(LogIn.this, MenuInicio.class);
                        startActivity(registro2);

                    } else {
                        Toast.makeText(this, "La contraseña no es correcta",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, "No has introducido contraseña",
                            Toast.LENGTH_SHORT).show();
                }
            } else
                Toast.makeText(this, "El email no es correcto",
                        Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "No has introducido el correo",
                    Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void cambioregistro(View v){
        //Crea Botones
        Button registro=(Button) findViewById(R.id.registrate);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro =new Intent(LogIn.this, MainActivity.class);
                startActivity(registro);
            }
        });
    }

    public static Usuario getUser(){
        return user;
    }

}
