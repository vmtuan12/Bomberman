package main;

import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class MyFrame extends JFrame {

    public MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setResizable(false);
        ImageIcon img = new ImageIcon("bakugo.png");
        this.setIconImage(img.getImage());
        // not specify the location of window -> displayed at center of the screen
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
