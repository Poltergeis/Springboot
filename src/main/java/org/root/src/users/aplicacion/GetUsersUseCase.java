package org.root.src.users.aplicacion;

import org.root.src.users.dominio.User;
import org.root.src.users.dominio.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetUsersUseCase {
    private final static Logger logger = LoggerFactory.getLogger(CreateUserUseCase.class);
    private final UserRepository userRepository;

    public GetUsersUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public CompletableFuture<User[]> run(){
        return CompletableFuture.supplyAsync(() -> {
            return this.userRepository.getUsers();
        }).exceptionally((e) -> {
            logger.error("error al obtener todos los usuarios. ", e);
            return null;
        });
    }
}
