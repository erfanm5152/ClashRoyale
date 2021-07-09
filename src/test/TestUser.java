package Test;

import Model.FileUtils;
import Model.User;
import Model.UserLibrary;
import org.junit.jupiter.api.*;

public class TestUser {
    private static UserLibrary userLibrary =new UserLibrary();

    @BeforeAll
    @Disabled
    static void add(){
        userLibrary.add(new User("a","b"));
        userLibrary.add(new User("a","b"));
        userLibrary.add(new User("b","b"));
    }

    @BeforeAll
    static void load(){
        userLibrary=FileUtils.readUsers();
    }

    @Test
    @Disabled
    void save(){
        FileUtils.writeUsers(userLibrary);
    }

    @Test
    void checkUsers(){
        for (User user:userLibrary.getUsers()) {
            System.out.println(user);
        }
    }
}
