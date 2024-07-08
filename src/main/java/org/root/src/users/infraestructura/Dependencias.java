package org.root.src.users.infraestructura;

import org.root.src.users.aplicacion.*;
import org.root.src.users.dominio.EncrypterRepository;
import org.root.src.users.dominio.UserRepository;
import org.root.src.users.infraestructura.Controladores.*;

public class Dependencias {

    private final CreateUserController createUserController;
    private final GetUsersController getUsersController;
    private final LoginController loginController;
    private final UpdateUserByIdController updateUserByIdController;
    private final DeleteUserByIdController deleteUserByIdController;

    public Dependencias(){
        UserRepository userRepository = new MongoUserRepository();
        EncrypterRepository encrypterRepository = new BcryptRepository();

        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository,encrypterRepository);
        DeleteUserByIdUseCase deleteUserByIdUseCase
                = new DeleteUserByIdUseCase(userRepository);
        GetUsersUseCase getUsersUseCase = new GetUsersUseCase(userRepository);
        LoginUseCase loginUseCase = new LoginUseCase(userRepository,encrypterRepository);
        UpdateUserByIdUseCase updateUserByIdUseCase
                = new UpdateUserByIdUseCase(userRepository,encrypterRepository);

        this.createUserController = new CreateUserController(createUserUseCase);
        this.getUsersController = new GetUsersController(getUsersUseCase);
        this.loginController = new LoginController(loginUseCase);
        this.updateUserByIdController = new UpdateUserByIdController(updateUserByIdUseCase);
        this.deleteUserByIdController = new DeleteUserByIdController(deleteUserByIdUseCase);
    }

    public CreateUserController getCreateUserController() {
        return createUserController;
    }

    public GetUsersController getGetUsersController() {
        return getUsersController;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public UpdateUserByIdController getUpdateUserByIdController() {
        return updateUserByIdController;
    }

    public DeleteUserByIdController getDeleteUserByIdController() {
        return deleteUserByIdController;
    }
}
