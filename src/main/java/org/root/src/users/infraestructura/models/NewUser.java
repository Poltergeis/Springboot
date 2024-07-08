package org.root.src.users.infraestructura.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewUser {
    @JsonProperty("checkId") private final int checkId;
    @JsonProperty("newUser") private final DestructuredUser newUser;

    @JsonCreator
    public NewUser(
            @JsonProperty("checkId") final int checkId,
            @JsonProperty("newUser") final DestructuredUser newUser
    ){
        this.checkId = checkId;
        this.newUser = newUser;
    }

    public DestructuredUser getNewUser() {
        return newUser;
    }

    public int getCheckId() {
        return checkId;
    }
}
