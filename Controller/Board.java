package Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.*; 

public class Board extends JFrame {
    private int screenWidth = 1536;
    private int screenHeight = 864;
    // private int screenWidth = getWidth();
    // private int screenHeight = getHeight();

    private int edgeCell = screenHeight/12;

    private Controller control = new Controller();

    public Board() {
        setTitle("Monopoly");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

        control.creatMatrix();
        
        JPanel boardGame = new JPanel();
        boardGame.setBounds(0, 0, screenHeight, screenHeight);
        boardGame.setBackground(Color.CYAN);

        // Game running
        JPanel game = new JPanel();
        game.setBounds(screenHeight, 0, (screenWidth - screenHeight) / 2, screenHeight);
        game.setBackground(Color.GRAY);

        // game info 
        JPanel info = new JPanel();
        info.setBounds(screenHeight + (screenWidth - screenHeight) / 2, 0, (screenWidth - screenHeight) / 2, screenHeight);
        info.setBackground(Color.PINK);

        add(boardGame);
        add(game);
        add(info);
        
        this.setVisible(true);
    }

}

