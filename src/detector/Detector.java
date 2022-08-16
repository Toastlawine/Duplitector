package detector;

import database.DuplicateSaver;
import main.Finals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Detector {

    private Scanner scanner;
    private Chunk[][][] chunks;
    private HashSet<Duplicate> duplicates;
    private DuplicateSaver duplicateSaver;

    public Detector() {
        scanner = new Scanner();
        scanner.scan();
        initChunks();
    }

    public void detect() {
        duplicates = new HashSet<>();
        duplicateSaver = new DuplicateSaver();
        for (int z = 0; z < chunks.length; z++) {
            for (int y = 0; y < chunks.length; y++) {
                for (int x = 0; x < chunks.length; x++) {
                    if (chunks[x][y][z] == null) continue;
                    findDuplicatesInSameChunk(chunks[x][y][z]);
                    if (x < Finals.CHUNK_COUNT - 1) {
                        findDuplicatesInNeighbouringChunk(chunks[x][y][z], chunks[x + 1][y][z]);
                        findDuplicatesInNeighbouringChunk(chunks[x][y][z], chunks[x + 1][y + 1][z]);
                        findDuplicatesInNeighbouringChunk(chunks[x][y][z], chunks[x + 1][y][z + 1]);
                        findDuplicatesInNeighbouringChunk(chunks[x][y][z], chunks[x + 1][y + 1][z + 1]);
                    }
                    if (y < Finals.CHUNK_COUNT - 1) {
                        findDuplicatesInNeighbouringChunk(chunks[x][y][z], chunks[x][y + 1][z]);
                        findDuplicatesInNeighbouringChunk(chunks[x][y][z], chunks[x][y + 1][z + 1]);
                    }
                    if (z < Finals.CHUNK_COUNT - 1) {
                        findDuplicatesInNeighbouringChunk(chunks[x][y][z], chunks[x][y][z + 1]);
                    }
                }
            }
        }
        duplicateSaver.save(duplicates);
    }

    private void findDuplicatesInSameChunk(Chunk c) {
        if (c.getImages().size() <= 1) return;
        List<ScannedImage> images = c.getImages();
        for (int i = 0; i < images.size(); i++) {
            for (int j = i + 1; j < images.size(); j++) {
                checkDuplicate(images.get(i), images.get(j));
            }
        }
    }

    private void findDuplicatesInNeighbouringChunk(Chunk c1, Chunk c2) {
        if (c2 == null) return;
        for (ScannedImage i1 : c1.getImages()) {
            for (ScannedImage i2 : c2.getImages()) {
                checkDuplicate(i1, i2);
            }
        }
    }

    private void checkDuplicate(ScannedImage i1, ScannedImage i2) {
        double p = 1;
        for (int y = 0; y < Finals.MATRIX_SIZE; y++) {
            for (int x = 0; x < Finals.MATRIX_SIZE; x++) {
                p *= i1.getMatrixVector(x, y).cosineLengthSimilarity(i2.getMatrixVector(x, y));
            }
        }
        //p = Math.pow(p,1);
        if (p < Finals.TOLERATED_DEVIATION) return;
        duplicates.add(new Duplicate(i1, i2));
        System.out.println("Duplicate found (" + p + "): " + i1.getId().toString() + " and " + i2.getId().toString());
    }

    private void initChunks() {
        chunks = new Chunk[Finals.CHUNK_COUNT][Finals.CHUNK_COUNT][Finals.CHUNK_COUNT];
        for (ScannedImage i : scanner.getScannedImages().values()) {
            insertImage(i);
        }
    }

    private void insertImage(ScannedImage i) {
        double chunkSize = 256d / Finals.CHUNK_COUNT;
        int x = (int) (i.getMean().getX() / chunkSize);
        int y = (int) (i.getMean().getY() / chunkSize);
        int z = (int) (i.getMean().getZ() / chunkSize);
        if (chunks[x][y][z] == null) chunks[x][y][z] = new Chunk();
        chunks[x][y][z].addImage(i);
    }

}
