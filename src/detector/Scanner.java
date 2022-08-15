package detector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Scanner {

    private List<String> files;
    private List<ScannedImage> scannedImages;

    public void scan() {
        files = new ArrayList<>();
        loadFiles();
    }
    private void loadFiles() {
        File here = new File("");
        here = here.getAbsoluteFile();

        for (String s : here.list()) {
            if (s.length() < 4) continue;
            String suffix = s.substring(s.length()-4);
            if (!s.endsWith(".png") && !s.endsWith(".jpg")) continue;
            files.add(s);
        }
    }

}
