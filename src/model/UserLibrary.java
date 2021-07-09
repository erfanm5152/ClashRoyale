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

    public boolean isExistUser(User user){
        return users.contains(user);
    }
    public boolean isExistUser(String userName){
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if (user.getName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    public User getUserByName(String userName){
        for (User temp:users) {
            if (temp.getName().equals(userName)){
                return temp;
            }
        }
        return null;
    }

    public boolean checkPassword(String name,String password){
        return getUserByName(name).getPassword().equals(password);
    }
}
