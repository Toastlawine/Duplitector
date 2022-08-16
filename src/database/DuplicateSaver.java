package database;

import detector.Duplicate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class DuplicateSaver {

    private File file;

    public void save(HashSet<Duplicate> duplicates) {
        try {
            FileWriter writer = new FileWriter(file);
            for (Duplicate d : duplicates) {
                writer.write(d.getImage1().getId().toString() + ";" + d.getImage2().getId().toString()+"\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            return;
        }
    }

    /*
    public HashSet<Duplicate> load() {
        return null;
    }
    */

    public DuplicateSaver() {
        loadFile();
    }

    private void loadFile() {
        file = new File("Duplitector/duplicates.txt");
        try {
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
