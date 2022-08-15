package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    public View(ActionListener scanNew, ActionListener scanAll) {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setResizable(false);

        this.setLocation(400, 300);

        JButton scanNewButton = new JButton("Scan new");
        scanNewButton.addActionListener(scanNew);
        this.add(scanNewButton);

        JButton scanAllButton = new JButton("Scan all");
        scanAllButton.addActionListener(scanAll);
        this.add(scanAllButton);

        this.pack();
        this.setVisible(true);
    }
}
