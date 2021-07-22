package Model;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

/**
 * The type User library.
 * To store users of the game
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class UserLibrary implements Serializable {
    // set of users
    private HashSet<User> users;

    /**
     * Instantiates a new User library.
     */
    public UserLibrary() {
        this.users = new HashSet<>();
    }

    /**
     * Instantiates a new User library.
     *
     * @param users the users
     */
    public UserLibrary(HashSet<User> users) {
        this.users = users;
    }

    /**
     * Add user to user library.
     *
     * @param user the user
     */
    public void add(User user) {
        remove(user.getName());
        users.add(user);
    }

    /**
     * Remove user from user library.
     *
     * @param name the name
     */
    public void remove(String name) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getName().equals(name)) {
                iterator.remove();
            }
        }
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public HashSet<User> getUsers() {
        return users;
    }

    /**
     * Is exist user in user library.
     *
     * @param user the user
     * @return true or false
     */
    public boolean isExistUser(User user) {
        return users.contains(user);
    }

    /**
     * Is exist user in user library.
     *
     * @param userName the name of user
     * @return true or false
     */
    public boolean isExistUser(String userName) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get user by name of user.
     *
     * @param userName the user name
     * @return the user
     */
    public User getUserByName(String userName) {
        for (User temp : users) {
            if (temp.getName().equals(userName)) {
                return temp;
            }
        }
        return null;
    }

    /**
     * Check password .
     *
     * @param name     the name
     * @param password the password
     * @return the boolean
     */
    public boolean checkPassword(String name, String password) {
        return getUserByName(name).getPassword().equals(password);
    }
}
