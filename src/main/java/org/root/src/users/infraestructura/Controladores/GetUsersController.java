package org.root.src.users.infraestructura.Controladores;

import org.root.src.users.aplicacion.GetUsersUseCase;
import org.root.src.users.dominio.User;
import org.root.src.users.infraestructura.models.ResponseError;
import org.root.src.users.infraestructura.models.ResponseSuccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GetUsersController {
    private final GetUsersUseCase getUsersUseCase;

    public GetUsersController(final GetUsersUseCase getUsersUseCase){
        this.getUsersUseCase = getUsersUseCase;
    }

    public ResponseEntity<?> run(){
        try{
            final User[] users = this.getUsersUseCase.run().get();
            if(users == null){
                return new ResponseEntity<ResponseError>(
                        new ResponseError("no se ha localizado la coleccion de usuarios"),
                        HttpStatus.NOT_FOUND
                );
            }
            return new ResponseEntity<ResponseSuccess<User[]>>(
                    new ResponseSuccess<User[]>(users),
                    HttpStatus.OK
            );
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<ResponseError>(
                    new ResponseError("error al obtener los usuarios."
                            + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
