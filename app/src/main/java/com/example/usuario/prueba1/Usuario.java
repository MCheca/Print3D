package com.example.usuario.prueba1;

/**
 * Created by Judith on 28/11/2017.
 */

public class Usuario {

    private String nombre, correo, cuenta;

    public Usuario(String n, String co, String cu){
        if(n!=null&&co!=null&&cu!=null){
            nombre=n;
            correo=co;
            cuenta=cu;
        }
    }

    public String getNombre(){
        return nombre;
    }


    public String getCorreo(){
        return correo;
    }

    public String getCuenta(){
        return cuenta;
    }


}
