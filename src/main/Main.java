package main;

import detector.Detector;
import gui.Controller;
import util.Vector;

public class Main {

    private static Main program;

    private Detector detector;
    private Controller gui;

    private Main() {
        gui = new Controller();
        detector = new Detector();
        detector.detect();
    }

    public static void main(String[] args) {
        program = new Main();
    }

    public static Main getProgram() {
        return program;
    }
}
