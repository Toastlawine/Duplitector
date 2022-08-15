package detector;

import main.Finals;
import util.Vector;

import java.util.UUID;

public class ScannedImage {

    private Vector[][] matrix;
    private Vector mean;
    private UUID id;
    private String parentFolder;

    public ScannedImage(Vector[][] matrix, String parentFolder) {
        this.matrix = matrix;
        id = UUID.randomUUID();
        this.parentFolder = parentFolder;
        calculateMean();
    }

    public ScannedImage(UUID id, String parentFolder, Vector mean, Vector[][] matrix) {
        this.matrix = matrix;
        this.mean = mean;
        this.id = id;
        this.parentFolder = parentFolder;
    }

    private void calculateMean() {
        Vector m = Vector.nullVector();
        for (Vector[] w : matrix) {
            for (Vector v : w) {
                m.add(v);
            }
        }
        m.divide(Finals.MATRIX_SIZE^2);
        mean = m;
    }

    public boolean isValid() {
        if (matrix.length != Finals.MATRIX_SIZE) return false;
        return (matrix[0].length == Finals.MATRIX_SIZE);
    }

    public Vector getMean() {
        return mean;
    }
    public Vector getMatrixVector(int x, int y) {
        return matrix[x][y];
    }

    public UUID getId() {
        return id;
    }

    public String getParentFolder() {
        return parentFolder;
    }
}
