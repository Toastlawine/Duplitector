package main;

import gui.GUI;

public class Main {

    private static Main program;

    private GUI gui;

    private Main() {
        gui = new GUI();
    }

    public static void main(String[] args) {
        program = new Main();
    }

    public static Main getProgram() {
        return program;
    }
}
