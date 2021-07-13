package Model;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

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

    public static String[][] readMap(){
        String[][] temp = new String[35][20];
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("./Resources/map.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 35; i++) {
            for (int j = 0; j < 20; j++) {
                if (!scanner.hasNext()){break;}
                temp[i][j]=scanner.next();
            }
        }

        return temp;
    }
}
