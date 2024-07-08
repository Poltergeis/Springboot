package org.root.src.users.aplicacion;
import org.root.src.users.dominio.EncrypterRepository;
import org.root.src.users.dominio.User;
import org.root.src.users.dominio.UserRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;

public class CreateUserUseCase {
    private final static Logger logger = LoggerFactory.getLogger(CreateUserUseCase.class);

    private final UserRepository userRepository;
    private final EncrypterRepository encrypter;
    public CreateUserUseCase(UserRepository userRepository, EncrypterRepository encrypter){
        this.userRepository = userRepository;
        this.encrypter = encrypter;
    }

    public CompletableFuture<User> run(int id, String nombre, String password){
        return CompletableFuture.supplyAsync(() -> {
            final String hashedPassword = this.encrypter.encrypt(password);
            if(hashedPassword.equals(password)){
                logger.error("error al encriptar la contraseña.");
                return null;
            }
            return this.userRepository.createUser(id, nombre, hashedPassword);
        }).exceptionally((e) -> {
            logger.error("error al crear un nuevo usuario.",e);
            return null;
        });
    }
}
