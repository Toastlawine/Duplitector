package util;

public class Vector {

    private double x, y, z;

    public static Vector nullVector() {
        return new Vector(0, 0, 0);
    }

    public static Vector fromString(String s) {
        try {
            double x, y, z;
            if (!s.endsWith(")")) return null;
            if (s.charAt(0) != '(') return null;
            int i = s.indexOf(',');
            if (i < 0) return null;
            x = Double.parseDouble(s.substring(1, i));
            s = s.substring(i + 1);
            i = s.indexOf(',');
            if (i < 0) return null;
            y = Double.parseDouble(s.substring(0, i));
            z = Double.parseDouble(s.substring(i + 1, s.length() - 1));

            return new Vector(x, y, z);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public Vector setLength(double a) {
        return multiply(a / getLength());
    }

    public double dotProduct(Vector v) {
        return v.getX() * x + v.getY() * y + v.getZ() * z;
    }

    public double cosineSimilarity(Vector v) {
        return dotProduct(v) / (getLength() * v.getLength());
    }

    public double cosineLengthSimilarity(Vector v) {
        double a = getLength() / v.getLength();
        if (a > 1) a = 1 / a;
        return cosineSimilarity(v) * a;
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distance(Vector v) {
        return Math.sqrt(distanceSquared(v));
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }

    private double distanceSquared(Vector v) {
        return sq(v.getX() - x) + sq(v.getY() - y) + sq(v.getZ() - z);
    }

    public Vector divide(double a) {
        x = x / a;
        y = y / a;
        z = z / a;
        return this;
    }

    public Vector multiply(double a) {
        x = x*a;
        y = y*a;
        z = z*a;
        return this;
    }

    public Vector add(Vector v) {
        x = x+v.getX();
        y = y+v.getY();
        z = z+v.getZ();
        return this;
    }

    private double sq(double a) {
        return a*a;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
