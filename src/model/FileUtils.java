package Model;

import java.io.*;
import java.util.HashSet;

public class FileUtils {

    public static void writeUsers(UserLibrary users){
        if (users.getUsers().size()==0){
            return;
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./Resources/users.bin"))){
            outputStream.writeObject(users);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static UserLibrary readUsers(){
        UserLibrary userLibrary =new UserLibrary();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./Resources/users.bin"))){
            userLibrary = (UserLibrary) inputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            return userLibrary;
        }
    }
}
