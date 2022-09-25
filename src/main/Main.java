package main;

import detector.Detector;
import gui.Controller;
import util.Vector;

public class Main {

    private static Main program;

    private Detector detector;
    private Controller gui;

    private Main() {
    }

    private void init() {
        //gui = new Controller();
        detector = new Detector();
        detector.detect();
        System.exit(0);
    }

    public void message(String m) {
        System.out.println(m);
    }

    public static void main(String[] args) {
        program = new Main();
        program.init();
    }

    public static Main getProgram() {
        return program;
    }
}
