package org.root.src.users.infraestructura.Controladores;

import org.root.src.users.aplicacion.DeleteUserByIdUseCase;
import org.root.src.users.dominio.User;
import org.root.src.users.infraestructura.models.DestructuredUser;
import org.root.src.users.infraestructura.models.ResponseError;
import org.root.src.users.infraestructura.models.ResponseSuccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public class DeleteUserByIdController {
    private final DeleteUserByIdUseCase deleteUserByIdUseCase;

    public DeleteUserByIdController(DeleteUserByIdUseCase deleteUserByIdUseCase){
        this.deleteUserByIdUseCase = deleteUserByIdUseCase;
    }

    public ResponseEntity<?> run(User deletableUser){
        try{
            final boolean result = this.deleteUserByIdUseCase
                    .run(deletableUser.getId()).get();
            if(result){
                return new ResponseEntity<ResponseSuccess<String>>(
                        new ResponseSuccess<String>("usuario borrado exitosamente"),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseError>(
                        new ResponseError("no fue posible borrar al usuario"),
                        HttpStatus.NOT_FOUND
                );
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<ResponseError>(
                    new ResponseError("error al borrar un usuario." + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
