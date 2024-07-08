package org.root.src.users.infraestructura.Controladores;

import org.root.src.users.aplicacion.LoginUseCase;
import org.root.src.users.dominio.User;
import org.root.src.users.infraestructura.models.ResponseError;
import org.root.src.users.infraestructura.models.ResponseSuccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LoginController {
    private final LoginUseCase loginUseCase;

    public LoginController(LoginUseCase loginUseCase){
        this.loginUseCase = loginUseCase;
    }

    public ResponseEntity<?> run(String password, String nombre){
        try{
            final User storedUser = this.loginUseCase.run(
                    password, nombre).get();
            if(storedUser == null){
                return new ResponseEntity<ResponseError>(
                        new ResponseError("no se ha encontrado al usuario"),
                        HttpStatus.NOT_FOUND
                );
            }
            return new ResponseEntity<ResponseSuccess<User>>(
                    new ResponseSuccess<User>(storedUser),
                    HttpStatus.OK
            );
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<ResponseError>(
                    new ResponseError("error al realizar el login." + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
