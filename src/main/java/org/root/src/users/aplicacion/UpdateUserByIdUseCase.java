package org.root.src.users.aplicacion;

import org.root.src.users.dominio.EncrypterRepository;
import org.root.src.users.dominio.User;
import org.root.src.users.dominio.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class UpdateUserByIdUseCase {
    private final static Logger logger = LoggerFactory.getLogger(CreateUserUseCase.class);
    private final UserRepository userRepository;
    private final EncrypterRepository encrypter;

    public UpdateUserByIdUseCase(UserRepository userRepository, EncrypterRepository encrypter){
        this.userRepository = userRepository;
        this.encrypter = encrypter;
    }

    public CompletableFuture<Boolean> run(int id, User newUser){
        return CompletableFuture.supplyAsync(() -> {
            final User possibleUser = this.userRepository.getUserById(id);
            if(possibleUser == null){
                logger.error("no se ha encontrado el usuario actualizable.");
                return false;
            }
            if(newUser.getPassword() != null){
                System.out.println(newUser.getPassword());
                final String hashedPassword = this.encrypter.encrypt(newUser.getPassword());
                System.out.println(hashedPassword);
                if(hashedPassword == null || hashedPassword.equals(newUser.getPassword())){
                    logger.error("error al encriptar la nueva contraseña");
                    return false;
                }
                final User newUserWithHashedPassword = new User(
                        newUser.getId(), newUser.getNombre(), hashedPassword
                );
                return this.userRepository.updateUserById(id, newUserWithHashedPassword);
            }else{
                return this.userRepository.updateUserById(id,newUser);
            }
        }).exceptionally((e) -> {
            logger.error("error al actualizar el usuario.",e);
            return false;
        });
    }
}
