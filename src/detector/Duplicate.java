package detector;

public class Duplicate {

    private ScannedImage image1, image2;

    public Duplicate(ScannedImage image1, ScannedImage image2) {
        this.image1 = image1;
        this.image2 = image2;
    }

    public ScannedImage getImage1() {
        return image1;
    }

    public ScannedImage getImage2() {
        return image2;
    }
}
