package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IncludedFolders {

    private File file;
    private List<String> included;

    private void load() {
        included = new ArrayList<>();
        loadFile();

        try {
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.charAt(0) != '+') continue;
                included.add(line.substring(1));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadFile() {
        file = new File("Duplitector/included_folders.txt");
        try {
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<String> getIncluded() {
        return included;
    }

    public IncludedFolders() {
        load();
    }

}
