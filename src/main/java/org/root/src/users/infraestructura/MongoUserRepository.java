package org.root.src.users.infraestructura;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.root.database.DatabaseConnection;
import org.root.src.users.dominio.EncrypterRepository;
import org.root.src.users.dominio.User;
import org.root.src.users.dominio.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class MongoUserRepository implements UserRepository {
    private final DatabaseConnection database = new DatabaseConnection("users");
    private final MongoCollection<Document> collection = database.getCollection();
    private EncrypterRepository encrypter = new BcryptRepository();
    public MongoUserRepository(){}

    @Override
    public User createUser(int id, String nombre, String password) {
        final Document newUser = new Document("id",id)
                .append("nombre",nombre)
                .append("password",password);
        if(newUser == null) return null;
        collection.insertOne(newUser);
        return new User(
                ((int) newUser.get("id")),
                ((String) newUser.get("nombre")),
                ((String) newUser.get("password"))
        );
    }

    @Override
    public User getUserById(int id) {
        final Document storedUser = collection.find(
                new Document("id",id)
        ).first();
        if(storedUser == null) return null;
        return new User(
                ((int) storedUser.get("id")),
                ((String) storedUser.get("nombre")),
                ((String) storedUser.get("password"))
        );
    }

    @Override
    public User getUserByName(String nombre) {
        final Document storedUser = collection.find(new Document("nombre", nombre)).first();
        if(storedUser == null) return null;
        return new User(
                ((int) storedUser.get("id")),
                ((String) storedUser.get("nombre")),
                ((String) storedUser.get("password"))
        );
    }

    @Override
    public User[] getUsers() {
        List<Document> storedUsers = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();
        while(cursor.hasNext()){
            storedUsers.add(cursor.next());
        }
        User[] users = new User[storedUsers.size()];
        for(int i = 0; i < users.length; i++){
            users[i] = new User(
                    ((int) storedUsers.get(i).get("id")),
                    ((String) storedUsers.get(i).get("nombre")),
                    ((String) storedUsers.get(i).get("password"))
            );
        }
        return users;
    }

    @Override
    public boolean deleteUserById(int id) {
        collection.deleteOne(Filters.eq("id",id));
        if(getUserById(id) == null){
            return true;
        } return false;
    }

    @Override
    public boolean updateUserById(int id, User newUser) {
        final User originalUser = getUserById(id);
        int modificableID = originalUser.getId();
        String modificableName = originalUser.getNombre();
        String modificablePassword = originalUser.getPassword();
        if(newUser.getId() != 0){
            modificableID = newUser.getId();
        }
        if(newUser.getNombre() != null){
            modificableName = newUser.getNombre();
        }
        if(newUser.getPassword() != null){
            modificablePassword = newUser.getPassword();
        }
        final User modifiedUser = new User(
                modificableID,
                modificableName,
                modificablePassword
        );
        if(modifiedUser.equals(originalUser)){
            return false;
        }else{
            final Document newDocument = new Document("id",modifiedUser.getId())
                    .append("nombre",modifiedUser.getNombre())
                    .append("password",modifiedUser.getPassword());
            final Document filter = new Document("id",id);
            collection.findOneAndReplace(filter,newDocument);
            return true;
        }
    }
}
