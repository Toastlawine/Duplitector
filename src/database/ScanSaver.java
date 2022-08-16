package database;

import detector.ScannedImage;
import main.Finals;
import util.Vector;

import java.io.*;
import java.util.*;

/**
 * Saves scanned images on the file system
 */
public class ScanSaver {

    private File saveFile;

    // id;folder;mean;()()...()()

    //TODO UUID format check
    public HashMap<UUID, ScannedImage> load() {
        HashMap<UUID, ScannedImage> list = new HashMap<>();

        try {
            Scanner reader = new Scanner(saveFile);

            int i1, i2, i3;
            UUID id;
            String path;
            Vector mean;
            Vector[][] matrix;
            while (reader.hasNextLine()) {
                String s = reader.nextLine();
                i1 = s.indexOf(';');
                i2 = s.substring(i1 + 1).indexOf(';') + i1 + 1;
                i3 = s.substring(i2 + 1).indexOf(';') + i2 + 1;
                id = UUID.fromString(s.substring(0, i1));
                path = s.substring(i1 + 1, i2);
                mean = Vector.fromString(s.substring(i2 + 1, i3));
                if (mean == null) continue;
                matrix = matrixFromString(s.substring(i3 + 1));
                if (matrix == null) continue;
                File file = new File(path);
                if (file.exists()) list.put(id, new ScannedImage(id, path, mean, matrix));
            }
            reader.close();

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

    public void save(Collection<ScannedImage> images) {
        try {
            FileWriter writer = new FileWriter(saveFile);
            int size = Finals.MATRIX_SIZE;
            for (ScannedImage i : images) {
                String s = i.getId().toString() + ";" + i.getPath() + ";" + i.getMean().toString() + ";";

                for (int y = 0; y < size; y++) {
                    for (int x = 0; x < size; x++) {
                        s = s + i.getMatrixVector(x, y);
                    }
                }

                writer.write(s + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ScanSaver() {
        saveFile = new File("Duplitector/scanned_images.txt");
        try {
            if (!saveFile.exists()) {
                saveFile.getParentFile().mkdir();
                saveFile.createNewFile();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
