package org.root.src.users.dominio;

public class User {

    private final int id;
    private final String nombre;
    private final String password;

    public User(int id, String nombre, String password){
        this.password = password;
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }
}
