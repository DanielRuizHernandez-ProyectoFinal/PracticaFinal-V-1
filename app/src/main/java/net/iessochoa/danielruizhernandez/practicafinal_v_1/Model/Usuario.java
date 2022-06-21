package net.iessochoa.danielruizhernandez.practicafinal_v_1.Model;

public class Usuario {

    String Nombre;
    String Correo;
    String Contraseña;
    String Teléfono;

    public Usuario() {
    }

    public Usuario(String nombre, String correo, String contraseña, String teléfono) {
        Nombre = nombre;
        Correo = correo;
        Contraseña = contraseña;
        Teléfono = teléfono;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getTeléfono() {
        return Teléfono;
    }

    public void setTeléfono(String teléfono) {
        Teléfono = teléfono;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Nombre='" + Nombre + '\'' +
                ", Correo='" + Correo + '\'' +
                ", Contraseña='" + Contraseña + '\'' +
                ", Teléfono='" + Teléfono + '\'' +
                '}';
    }
}
