package org.root.src.users.dominio;

public interface UserRepository {
    User createUser(int id, String nombre, String password);
    User[] getUsers();
    User getUserByName(String nombre);
    User getUserById(int id);
    boolean deleteUserById(int id);
    boolean updateUserById(int id, User newUser);
}
