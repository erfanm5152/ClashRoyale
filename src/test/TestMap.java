package Test;

import Model.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class TestMap {
    static String[][] map  = new String[35][20];
    @BeforeAll
    static void readMap(){
        map = FileUtils.readMap();
    }

    @Test
    static void print(){
        for (int i = 0; i < 35; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(map[i][j]);
            }
        }
    }
    @Test
    void print1(){
        for (int i = 0; i < 35; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(map[i][j]);
            }
        }
    }
}
