package net.iessochoa.danielruizhernandez.practicafinal_v_1.Model;

import java.io.Serializable;

public class Personaje implements Serializable {


    String nombre;
    String clase;
    String raza;
    String nivel;
    String inventario;
    String usuario;
    String id;


    public Personaje() {
    }


    public Personaje(String nombre, String clase, String raza, String nivel, String inventario, String usuario, String idPersonaje) {
        this.nombre = nombre;
        this.clase = clase;
        this.raza = raza;
        this.nivel = nivel;
        this.inventario = inventario;
        this.usuario = usuario;
        this.id = idPersonaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getInventario() {
        return inventario;
    }

    public void setInventario(String inventario) {
        this.inventario = inventario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "nombre='" + nombre + '\'' +
                ", clase='" + clase + '\'' +
                ", raza='" + raza + '\'' +
                ", nivel='" + nivel + '\'' +
                ", inventario='" + inventario + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
