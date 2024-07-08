package org.root.src.users.aplicacion;

import org.root.src.users.dominio.EncrypterRepository;
import org.root.src.users.dominio.User;
import org.root.src.users.dominio.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class LoginUseCase {
    private final static Logger logger = LoggerFactory.getLogger(LoginUseCase.class);
    private final UserRepository userRepository;
    private final EncrypterRepository encrypter;

    public LoginUseCase(UserRepository userRepository, EncrypterRepository encrypter){
        this.encrypter = encrypter;
        this.userRepository = userRepository;
    }

    public CompletableFuture<User> run(String password, String nombre){
        return CompletableFuture.supplyAsync(() -> {
            final User possibleUser = this.userRepository.getUserByName(nombre);
            if(possibleUser == null){
                logger.error("error, no se ha encontrado al usuario.");
                return null;
            }
            if(this.encrypter.check(password,possibleUser.getPassword())){
                return possibleUser;
            }else return null;
        }).exceptionally((e) -> {
            logger.error("error al realizar el login.", e);
            return null;
        });
    }
}
