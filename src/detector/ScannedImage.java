package detector;

import main.Finals;
import util.Vector;

import java.util.UUID;

public class ScannedImage {

    private Vector[][] matrix;
    private Vector mean;
    private UUID id;
    private String path;

    public ScannedImage(Vector[][] matrix, String path) {
        this.matrix = matrix;
        id = UUID.randomUUID();
        this.path = path;
        calculateMean();
    }

    public ScannedImage(UUID id, String path, Vector mean, Vector[][] matrix) {
        this.matrix = matrix;
        this.mean = mean;
        this.id = id;
        this.path = path;
    }

    private void calculateMean() {
        Vector m = Vector.nullVector();
        for (Vector[] w : matrix) {
            for (Vector v : w) {
                m.add(v);
            }
        }
        m.divide(Finals.MATRIX_SIZE * Finals.MATRIX_SIZE);
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
