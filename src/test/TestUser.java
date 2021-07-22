package Test;

import Model.FileUtils;
import Model.Game;
import Model.User;
import Model.UserLibrary;
import org.junit.jupiter.api.*;

public class TestUser {
    private static UserLibrary userLibrary =new UserLibrary();

    @BeforeAll
    static void add(){
        userLibrary.add(new User("a","b"));
        userLibrary.add(new User("a","b"));
        userLibrary.add(new User("b","b"));

    }

//    @Test
//    void checkUsers(){
//        if (userLibrary.getUsers().size()==0){
//            System.out.println("size = 0");
//        }
//        for (User user:userLibrary.getUsers()) {
//            System.out.println(user);
//        }
//    }

//    @BeforeAll
//    static void load(){
//        userLibrary=FileUtils.readUsers();
//    }

    @Test
    void save(){
        FileUtils.writeUsers(userLibrary);
    }
//    @Test
//    void printGame(){
//        for (User user:userLibrary.getUsers()) {
//            for (Game game: user.getGameHistory()) {
//                System.out.println(game.getWinner());
//                System.out.println(game.getLoser());
//                System.out.println("////////////////////////");
//            }
//        }
//    }
}
