package net.iessochoa.danielruizhernandez.practicafinal_v_1.Model;

public class PersonajeModel {

    String clase;
    String raza ;
    String conjuros;
    String inventario;
    String id ;

    public PersonajeModel() {
    }

    public PersonajeModel(String clase, String raza, String conjuros, String inventario, String id) {
        this.clase = clase;
        this.raza = raza;
        this.conjuros = conjuros;
        this.inventario = inventario;
        this.id = id;
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

    public String getConjuros() {
        return conjuros;
    }

    public void setConjuros(String conjuros) {
        this.conjuros = conjuros;
    }

    public String getInventario() {
        return inventario;
    }

    public void setInventario(String inventario) {
        this.inventario = inventario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
