package Test;

import Model.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * The type Test map.
 *
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class TestMap {
    /**
     * The string of Map.
     */
    static String[][] map = new String[35][20];

    /**
     * Read map.
     */
    @BeforeAll
    static void readMap() {
        map = FileUtils.readMap();
    }

    /**
     * Print.
     */
    @Test
    static void print() {
        for (int i = 0; i < 35; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(map[i][j]);
            }
        }
    }

    /**
     * Print 1.
     */
    @Test
    void print1() {
        for (int i = 0; i < 35; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(map[i][j]);
            }
        }
    }
}
