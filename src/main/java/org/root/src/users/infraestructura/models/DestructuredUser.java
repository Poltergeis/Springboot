package org.root.src.users.infraestructura.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.root.src.users.dominio.User;

public class DestructuredUser {

    @JsonProperty("id") private int id;
    @JsonProperty("nombre") private String nombre;
    @JsonProperty("password") private String password;

    @JsonCreator public DestructuredUser(
            @JsonProperty("id") final int id,
            @JsonProperty("nombre") final String nombre,
            @JsonProperty("password") final String password
    ){
        this.id = id;
        this.nombre = nombre;
        this.password = password;
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

    public User toUser(){
        return new User(id,nombre,password);
    }
}
