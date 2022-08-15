package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private JFrame frame;
    private JButton scan, scanAll;
    private ActionListener actionListener;

    public GUI() {
        initActionListener();
        
        frame = new JFrame("Duplitector");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocation(400, 300);
        frame.setBackground(Color.DARK_GRAY);
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        scan = new JButton("Scan new");
        scan.setSize(120, 30);
        scan.setLocation(10, 12);
        scan.addActionListener(actionListener);
        frame.add(scan);

        scanAll = new JButton("Scan all");
        scanAll.setSize(120, 30);
        scanAll.setLocation(10, 52);
        scanAll.addActionListener(actionListener);
        frame.add(scanAll);

        frame.setVisible(true);
    }

    private void initActionListener() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                if (source == scan) {
                    
                } else if (source == scanAll) {
                    
                }
            }
        };
    }
    
}
