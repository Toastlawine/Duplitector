package gui;

import javax.swing.*;

public class Controller {

    private JFrame view;

    public Controller() {
        this.view = new View(
                event -> scanNew(),
                event -> scanAll()
        );
    }

    private void scanNew() {

    }

    private void scanAll() {

    }
}
