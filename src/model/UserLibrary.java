package Model;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class UserLibrary implements Serializable {
    private HashSet<User> users;

    public UserLibrary() {
        this.users = new HashSet<>();
    }
    public UserLibrary(HashSet<User> users){
        this.users = users;
    }

    public void add(User user){
        remove(user.getName());
        users.add(user);
    }

    public void remove(String name){
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if (user.getName().equals(name)){
                iterator.remove();
            }
        }
    }

    public HashSet<User> getUsers() {
        return users;
    }
}
