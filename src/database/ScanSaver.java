package database;

import detector.ScannedImage;
import main.Finals;
import util.Vector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Saves scanned images on the file system
 */
public class ScanSaver {

    private File saveFile;

    // id;folder;mean;()()...()()

    //TODO UUID format
    public List<ScannedImage> load() {
        List<ScannedImage> list = new ArrayList<>();

        try {
            Scanner reader = new Scanner(saveFile);

            int i1, i2, i3;
            UUID id;
            String parentFolder;
            Vector mean;
            Vector[][] matrix;
            while (reader.hasNextLine()) {
                String s = reader.nextLine();
                i1 = s.indexOf(';');
                i2 = s.substring(i1 + 1).indexOf(';');
                i3 = s.substring(i2 + 1).indexOf(';');
                id = UUID.fromString(s.substring(0, i1));
                parentFolder = s.substring(i1 + 1, i2);
                mean = Vector.fromString(s.substring(i2 + 1, i3));
                if (mean == null) continue;
                matrix = matrixFromString(s.substring(i3 + 1));
                if (matrix == null) continue;
                list.add(new ScannedImage(id, parentFolder, mean, matrix));
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }

        return list;
    }

    private Vector[][] matrixFromString(String s) {
        Vector[][] re = new Vector[Finals.MATRIX_SIZE][Finals.MATRIX_SIZE];

        int i;
        for (int y = 0; y < re.length; y++) {
            for (int x = 0; x < re.length; x++) {
                i = s.indexOf(')');
                if (i < 0) return null;
                re[x][y] = Vector.fromString(s.substring(0, i + 1));
                if (re[x][y] == null) return null;
                s = s.substring(i + 1);
            }
        }

        return re;
    }

    public void save(List<ScannedImage> images) {
        try {
            for (ScannedImage i : images) {
                FileWriter writer = new FileWriter(saveFile);
                String s = i.getId().toString() + ";" + i.getParentFolder() + ";" + i.getMean().toString() + ";";

                int size = Finals.MATRIX_SIZE;
                for (int y = 0; y < size; y++) {
                    for (int x = 0; x < size; x++) {
                        s = s + i.getMatrixVector(x, y);
                    }
                }

                writer.write(s + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ScanSaver() {
        saveFile = new File("scanned_images.txt");
        try {
            if (!saveFile.exists()) {
                saveFile.mkdir();
                saveFile.createNewFile();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
