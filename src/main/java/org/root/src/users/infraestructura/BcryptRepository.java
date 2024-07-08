package org.root.src.users.infraestructura;

import org.mindrot.jbcrypt.BCrypt;
import org.root.src.users.dominio.EncrypterRepository;

public class BcryptRepository implements EncrypterRepository {

    public BcryptRepository(){}

    @Override
    public String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean check(String userPassword, String hashedPassword) {
        return BCrypt.checkpw(userPassword,hashedPassword);
    }
}
