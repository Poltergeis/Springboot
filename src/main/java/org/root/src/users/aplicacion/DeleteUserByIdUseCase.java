package org.root.src.users.aplicacion;

import org.root.src.users.dominio.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class DeleteUserByIdUseCase {
    private final static Logger logger = LoggerFactory.getLogger(CreateUserUseCase.class);
    private final UserRepository userRepository;

    public DeleteUserByIdUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public CompletableFuture<Boolean> run(int id){
        return CompletableFuture.supplyAsync(() -> {
            return this.userRepository.deleteUserById(id);
        }).exceptionally((e) -> {
            logger.error("error al borrar el usuario. ", e);
            return false;
        });
    }
}
