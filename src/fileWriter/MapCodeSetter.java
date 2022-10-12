package fileWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MapCodeSetter extends FileWriter {

    public MapCodeSetter() {
        textPath = "tile/savedMapCode.txt";
        try {
            scanner = new Scanner(new File(textPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMapCode(int code) {
        try {
            writer = new BufferedWriter(new java.io.FileWriter(textPath));
            writer.write(Integer.toString(code));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
