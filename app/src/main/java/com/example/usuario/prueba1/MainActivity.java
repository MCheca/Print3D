package com.example.usuario.prueba1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jumanji.prueba1.R;

public class MainActivity extends AppCompatActivity {

    private EditText user,email,email2,pass,pass2,tipo;
    private CheckBox acepto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user=(EditText)findViewById(R.id.user);
        email=(EditText)findViewById(R.id.email);
        email2=(EditText)findViewById(R.id.email2);
        pass=(EditText)findViewById(R.id.pass);
        pass2=(EditText)findViewById(R.id.pass2);
        tipo=(EditText)findViewById(R.id.tipo);
        acepto=findViewById(R.id.checkBox);

    }


    //Metodo que da de alta a un usuario en el registro

    public void registro(View v) {

        //Trae la tabla

        AdministradorOpenHelper admin = new AdministradorOpenHelper(this,
                "usuarios", null, 2);

        //Permisos de lectura y escritura de la tabla

        SQLiteDatabase bd = admin.getWritableDatabase();

        String nombre = user.getText().toString();
        String correo = email.getText().toString();
        String correo2 = email2.getText().toString();
        String cont = pass.getText().toString();
        String cont2 = pass2.getText().toString();
        String cuenta = tipo.getText().toString();

        if(!nombre.equals("")) {
            if(!correo.equals("")) {
                if (!cont.equals("")) {
                    if (correo.equalsIgnoreCase(correo2)) {
                        if (cont.equals(cont2)) {
                            if (cuenta.equalsIgnoreCase("Impresor") || cuenta.equalsIgnoreCase("Interesado")) {
                               if(acepto.isChecked()) {
                                   if (consultasiexiste(correo)) {
                                       ContentValues registrar = new ContentValues();
                                       registrar.put("usuario", nombre);
                                       registrar.put("email", correo);
                                       registrar.put("contraseña", cont);
                                       registrar.put("cuenta", cuenta);

                                       bd.insert("usuarios", null, registrar);

                                       bd.close();
                                       user.setText("");
                                       email.setText("");
                                       email2.setText("");
                                       pass.setText("");
                                       pass2.setText("");
                                       tipo.setText("");


                                       Toast.makeText(this, "Registro completado",
                                               Toast.LENGTH_SHORT).show();


                                       Intent registro2 = new Intent(MainActivity.this, LogIn.class);
                                       startActivity(registro2);


                                   } else {
                                       Toast.makeText(this, "Este email ya está registrado",
                                               Toast.LENGTH_SHORT).show();
                                   }
                               }
                               else{
                                   Toast.makeText(this, "No has aceptado los terminos",
                                           Toast.LENGTH_SHORT).show();
                               }
                            } else {
                                Toast.makeText(this, "Debes ser: Impresor o Interesado",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(this, "Los contraseñas no coinciden",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Los correos no coinciden",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "No has introducido la contraseña",
                            Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "No has introducido el correo",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "No has introducido nombre de usuario",
                    Toast.LENGTH_SHORT).show();
        }


    }

    /*
    public void iniciosesion(View v) {
        AdministradorOpenHelper admin = new AdministradorOpenHelper(this,
                "usuarios", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String correo = nombre.getText().toString();
        Cursor fila = bd.rawQuery(
                "select usuario,contraseña,cuenta from usuarios where email=" + correo, null);
        if (fila.moveToFirst()) {
            String confirmar=contra.getText().toString();
            String confi=fila.getString(1);
            if(confirmar.equals(confi)){

                //AQUI SE CAMBIA DE XML

            }
        } else
            Toast.makeText(this, "El email no es correcto",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }


*/

    //El metodo Alta, llama a este para saber si existe ya un elemento con la misma clave primaria

    public boolean consultasiexiste(String correo) {
        boolean puedes=false;
        AdministradorOpenHelper admin = new AdministradorOpenHelper(this,
                "usuarios", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery(
                "select usuario,contraseña,cuenta from usuarios where email='" + correo+"'", null);
        if (fila.moveToFirst()) {
            puedes=false;
        } else {puedes=true;}

        bd.close();
        return puedes;
    }


                /*


    public void bajaporcodigo(View v) {
        AdministradorOpenHelper admin = new AdministradorOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod= et1.getText().toString();
        int cant = bd.delete("articulos", "codigo=" + cod, null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borró el artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe un artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
    }



    public void modificacion(View v) {
        AdministradorOpenHelper admin = new AdministradorOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        String descri = et2.getText().toString();
        String pre = et3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("descripcion", descri);
        registro.put("precio", pre);
        int cant = bd.update("articulos", registro, "codigo=" + cod, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe un artículo con el código ingresado",
                    Toast.LENGTH_SHORT).show();
    }

    */

}
