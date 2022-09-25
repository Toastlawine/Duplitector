package detector;

import database.IncludedFolders;
import database.ScanSaver;
import main.Finals;
import main.Main;
import util.Vector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Scanner {

    private List<String> files;
    private HashMap<UUID, ScannedImage> scannedImages;
    private ScanSaver saver;
    private IncludedFolders includedFolders;

    public void scan() {
        files = new ArrayList<>();
        saver = new ScanSaver();
        includedFolders = new IncludedFolders();
        scannedImages = saver.load();
        loadFiles();
        scanImages();
        saver.save(scannedImages.values());
        Main.getProgram().message("Scan complete!");
    }

    public void scanImages() {
        UUID id = null;
        File file;
        int count = files.size();
        Main.getProgram().message(files.size() + " images found");
        int cur = 0;
        for (String i : files) {
            cur++;
            if (cur % 10 == 0) Main.getProgram().message(cur + " / " + count);
            file = new File(i);
            if (file.isDirectory()) return;
            String name = file.getName();
            id = null;
            try {
                id = UUID.fromString(name.substring(0, name.length() - 4));
            } catch (IllegalArgumentException ex) {
            }
            if (scannedImages.containsKey(id)) {
                if (id.toString().length() + 4 == name.length()) continue;
            }
            ScannedImage si = scanImage(file, id);
            if (si != null) scannedImages.put(si.getId(), si);
        }
    }

    private ScannedImage scanImage(File i, UUID uuid) {
        Vector[][] matrix = new Vector[Finals.MATRIX_SIZE][Finals.MATRIX_SIZE];

        BufferedImage image;
        try {
            image = ImageIO.read(i);
        } catch (IOException e) {
            return null;
        }

        int width = image.getWidth() / Finals.MATRIX_SIZE;
        int height = image.getHeight() / Finals.MATRIX_SIZE;

        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix.length; x++) {
                matrix[x][y] = getAreaVector(x, y, width, height, image);
            }
        }

        ScannedImage re = new ScannedImage(uuid, matrix, i.getAbsolutePath());
        String path = i.getAbsolutePath();
        String newName = re.getId().toString() + path.substring(path.length() - 4);
        File rename = new File(path.substring(0, path.length() - i.getName().length()) + "/" + newName);
        i.renameTo(rename);
        re.setPath(rename.getAbsolutePath());
        return re;
    }

    private Vector getAreaVector(int x, int y, int w, int h, BufferedImage i) {
        long rSum = 0, gSum = 0, bSum = 0;
        int rgb;
        for (int iy = 0; iy < h; iy++) {
            for (int ix = 0; ix < w; ix++) {
                rgb = i.getRGB(x * w + ix, y * h + iy);
                rSum += getR(rgb);
                gSum += getG(rgb);
                bSum += getB(rgb);
            }
        }
        double red = (double) rSum / (w * h);
        double green = (double) gSum / (w * h);
        double blue = (double) bSum / (w * h);
        return new Vector(red, green, blue);
    }

    private void loadFiles() {
        if (includedFolders.getIncluded().size() == 0) {
            System.out.println("No included folders");
            return;
        }
        for (String i : includedFolders.getIncluded()) {
            loadFolder(i);
        }
    }

    private void loadFolder(String folderPath) {
        File folder = new File(folderPath);
        folder = folder.getAbsoluteFile();

        if (folder.list() == null) return;
        for (String s : folder.list()) {
            if (s.indexOf(';') >= 0) continue;
            if (s.length() < 4) continue;
            String suffix = s.substring(s.length() - 4);
            if (!s.endsWith(".png") && !s.endsWith(".jpg")) continue;
            files.add(folderPath + (folderPath.length() == 0 ? "" : "/") + s);
        }
    }

    public HashMap<UUID, ScannedImage> getScannedImages() {
        return scannedImages;
    }

    private int getB(int rgb) {
        return rgb & 0b11111111;
    }

    private int getG(int rgb) {
        return rgb >> 8 & 0b11111111;
    }

    private int getR(int rgb) {
        return rgb >> 16 & 0b11111111;
    }

}
