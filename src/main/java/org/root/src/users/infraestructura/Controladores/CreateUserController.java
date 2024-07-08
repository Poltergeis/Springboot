package org.root.src.users.infraestructura.Controladores;

import org.root.src.users.aplicacion.CreateUserUseCase;
import org.root.src.users.dominio.User;
import org.root.src.users.infraestructura.models.ResponseError;
import org.root.src.users.infraestructura.models.ResponseSuccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CreateUserController {
    private final CreateUserUseCase createUserUseCase;

    public CreateUserController(CreateUserUseCase createUserUseCase){
        this.createUserUseCase = createUserUseCase;
    }

    public ResponseEntity<?> run(User user){
        try{
            final User resultUser = this.createUserUseCase.run(
                    user.getId(), user.getNombre(), user.getPassword()
            ).get();
            if(resultUser == null){
                return new ResponseEntity<ResponseError>(
                        new ResponseError("no se ha podido crear al nuevo usuario"),
                        HttpStatus.FAILED_DEPENDENCY
                );
            }
            return new ResponseEntity<ResponseSuccess<User>>(
                    new ResponseSuccess<User>(resultUser),
                    HttpStatus.CREATED
            );
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<ResponseError>(
                    new ResponseError("error al crear un nuevo usuario." + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
