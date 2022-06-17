package net.iessochoa.danielruizhernandez.practicafinal_v_1.Model;

public class PersonajeModel {

    String nombre;
    String clase;
    String raza ;
    String nivel;
    String inventario;


    public PersonajeModel() {
    }


    public PersonajeModel(String nombre, String clase, String raza, String nivel, String inventario) {
        this.nombre = nombre;
        this.clase = clase;
        this.raza = raza;
        this.nivel = nivel;
        this.inventario = inventario;
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
}