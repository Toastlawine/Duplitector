package detector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Scanner {

    private List<String> files;

    public void scan() {
        files = new ArrayList<>();
        getFiles();
    }
    private void getFiles() {
        File here = new File("");
        System.out.println(here.list());
        System.out.println(here.getAbsolutePath());
    }

}
