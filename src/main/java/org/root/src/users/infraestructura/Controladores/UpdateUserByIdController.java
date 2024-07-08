package org.root.src.users.infraestructura.Controladores;

import org.root.src.users.aplicacion.UpdateUserByIdUseCase;
import org.root.src.users.dominio.User;
import org.root.src.users.infraestructura.models.NewUser;
import org.root.src.users.infraestructura.models.ResponseError;
import org.root.src.users.infraestructura.models.ResponseSuccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

public class UpdateUserByIdController {
    private final UpdateUserByIdUseCase updateUserByIdUseCase;

    public UpdateUserByIdController(UpdateUserByIdUseCase updateUserByIdUseCase){
        this.updateUserByIdUseCase = updateUserByIdUseCase;
    }

    public ResponseEntity<?> run(int checkId, User newUser){
        try{
            final boolean success = this.updateUserByIdUseCase.run(checkId,newUser).get();
            if(success){
                return new ResponseEntity<ResponseSuccess<String>>(
                        new ResponseSuccess<String>("usuario actualizado con exito"),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity<ResponseError>(
                        new ResponseError("no fue posible actualizar el usuario."),
                        HttpStatus.NOT_FOUND
                );
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<ResponseError>(
                    new ResponseError("error al actualizar el usuario." + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
