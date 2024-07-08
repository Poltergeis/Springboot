package org.root.src.users.dominio;

public interface EncrypterRepository {
    String encrypt(String password);
    boolean check(String userPassword, String hashedPassword);
}
