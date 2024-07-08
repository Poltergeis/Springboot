package org.root.src.users.infraestructura;

import org.root.src.users.dominio.User;
import org.root.src.users.infraestructura.models.DestructuredUser;
import org.root.src.users.infraestructura.models.NewUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRouter {
    final Dependencias dependencias = new Dependencias();

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody DestructuredUser user){
        return this.dependencias
                .getCreateUserController().run(user.toUser());
    }

    @GetMapping
    public ResponseEntity<?> getUsers(){
        return this.dependencias.getGetUsersController().run();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody DestructuredUser destructuredUser){
        final User user = destructuredUser.toUser();
        return this.dependencias.getLoginController().run(
                user.getPassword(), user.getNombre()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateById(@RequestBody NewUser newUser){
        return this.dependencias.getUpdateUserByIdController()
                .run(newUser.getCheckId(),newUser.getNewUser().toUser());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestBody DestructuredUser destructuredUser){
        return this.dependencias.getDeleteUserByIdController()
                .run(destructuredUser.toUser());
    }
}
