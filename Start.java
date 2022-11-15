

import javax.swing.*;
import java.awt.*;

public class Start {
    public Start() {
        JFrame jframe = new JFrame("Hello");
        jframe.setSize(1000, 700);
        JPanel start_jpanel = new JPanel();
        JLabel start_label = new JLabel("Welcome to my game!");
        JButton start_button = new JButton("Start");
        ImageIcon start_img = new ImageIcon("img/Background_Start.png");
        start_label.setIcon(start_img);
        start_jpanel.add(start_label);
        start_jpanel.add(start_button);
        
        jframe.add(start_jpanel);
        jframe.setVisible(true);
    }
}
