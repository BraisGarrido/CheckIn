package com.proyecto.checkin.entidades;

import android.graphics.drawable.Drawable;

public class Vivienda {
    private String nombre;
    private String localidad;
    private String url_imagen;
    private Drawable imagen;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public Vivienda(){

    }

    public Vivienda(String nombre, String localidad, String url_imagen){
        this.nombre=nombre;
        this.localidad=localidad;
        this.url_imagen=url_imagen;
    }
}
