package main;

import gui.Controller;

public class Main {

    private static Main program;

    private Controller gui;

    private Main() {
        gui = new Controller();
    }

    public static void main(String[] args) {
        program = new Main();
    }

    public static Main getProgram() {
        return program;
    }
}
