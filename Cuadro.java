package com.example.handlerconhilo;

public class Cuadro {
    private String nombre;
    private String ruta_imagen;

    public Cuadro(String nombre, String ruta_imagen) {
        this.nombre = nombre;
        this.ruta_imagen = ruta_imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }

    @Override
    public String toString() {
        return "Cuadro{" +
                "nombre='" + nombre + '\'' +
                ", ruta_imagen='" + ruta_imagen + '\'' +
                '}';
    }
}
