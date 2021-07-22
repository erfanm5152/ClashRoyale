package Model;

import java.io.*;
import java.util.Scanner;

/**
 * The type File utils.
 * for read & write from files
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class FileUtils {

    /**
     * Write users from userLibrary to file.
     *
     * @param users the users
     */
    public static void writeUsers(UserLibrary users) {
        if (users.getUsers().size() == 0) {
            return;
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./Resources/users.bin"))) {
            outputStream.writeObject(users);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read users from file.
     *
     * @return the user library
     */
    public static UserLibrary readUsers() {
        UserLibrary userLibrary = new UserLibrary();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./Resources/users.bin"))) {
            userLibrary = (UserLibrary) inputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return userLibrary;
        }
    }

    /**
     * Read map from text file.
     *
     * @return the string [ ] [ ]
     */
    public static String[][] readMap() {
        String[][] temp = new String[34][20];
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("./Resources/map.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 35; i++) {
            for (int j = 0; j < 20; j++) {
                if (!scanner.hasNext()) {
                    break;
                }
                temp[i][j] = scanner.next();
            }
        }

        return temp;
    }
}
