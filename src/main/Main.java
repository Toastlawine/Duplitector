package main;

import detector.Detector;
import gui.GUI;

public class Main {

    private static Main program;

    private GUI gui;
    private Detector detector;

    private Main() {
        gui = new GUI();
        detector = new Detector();
    }

    public static void main(String[] args) {
        program = new Main();
    }

    public static Main getProgram() {
        return program;
    }
}
