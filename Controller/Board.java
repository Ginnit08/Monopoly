package Controller;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.*; 

public class Board extends JFrame {
    public Board() {
        setTitle("Monopoly");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File("img/Board.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image image = bufferedImage.getScaledInstance(1536, 864, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(icon);
        add(jLabel);
        this.setVisible(true);
    }

}
