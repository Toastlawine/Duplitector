package detector;

import java.util.ArrayList;
import java.util.List;

public class Chunk {

    private List<ScannedImage> images;

    Chunk() {
        images = new ArrayList<>();
    }

    public List<ScannedImage> getImages() {
        return images;
    }

    public void addImage(ScannedImage i) {
        images.add(i);
    }
}
