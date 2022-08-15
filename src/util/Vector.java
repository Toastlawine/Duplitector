package util;

public class Vector {

    private double x, y, z;

    public double distance(Vector v) {
        return Math.sqrt(distanceSquared(v));
    }

    private double distanceSquared(Vector v) {
        return sq(v.getX() - x) + sq(v.getY() - y) + sq(v.getZ() - z);
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
