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
        
        // JPanel panel = new JPanel();
        // GridBagLayout layout = new GridBagLayout();
        // panel.setLayout(layout);

        // GridBagConstraints gbc = new GridBagConstraints();
        // int i = 0;
        // for(Cell cell: control.getListCell()) {
            
        //     gbc.gridx = cell.getY();
        //     gbc.gridy = cell.getX();
        //     panel.add(new JButton("Cell " + cell.getIndex()), gbc);
        // }

        // add(panel);
        
        JPanel boardGame = new JPanel();
        boardGame.setBounds(0, 0, screenHeight, screenHeight);
        boardGame.setBackground(Color.CYAN);

        GridBagLayout layout = new GridBagLayout();
        boardGame.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        for(Cell cell: control.getListCell()) {

            gbc.gridx = cell.getX();
            gbc.gridy = cell.getY();

            JPanel cellPanel = new JPanel();
            JLabel label = new JLabel("Cell " + cell.getIndex());
            cellPanel.add(label);

            // if(cell.getIndex() % 10 == 0) {
            //     cellPanel.setSize(edgeCell * 3 / 2, edgeCell * 3 / 2);
            // }
            // else
            // cellPanel.setSize(edgeCell, edgeCell);

            boardGame.add(cellPanel, gbc);
        }

        // for(Cell c: control.getListCell()) {
        //     JPanel cellPanel = new JPanel();
        //     cellPanel.add(new JLabel("Cell" + c.getIndex()));
        //     if(c.getIndex() % 10 == 0) {
        //         cellPanel.setBounds(screenHeight - c.getX() * edgeCell, screenHeight - c.getY() * edgeCell , edgeCell * 3 / 2, edgeCell * 3 / 2);
        //     }
        //     else
        //     cellPanel.setBounds(screenHeight - c.getX() * edgeCell, screenHeight - c.getY() * edgeCell , edgeCell, edgeCell);
            
        //     boardGame.add(cellPanel);
        // }

        // Game running
        JPanel game = new JPanel();
        game.setBounds(screenHeight, 0, (screenWidth - screenHeight) / 2, screenHeight);
        game.setBackground(Color.GRAY);
        game.add(new JLabel("Game running"));

        // game info 
        JPanel info = new JPanel();
        info.add(new JLabel("info of 4 player"));
        info.setBounds(screenHeight + (screenWidth - screenHeight) / 2, 0, (screenWidth - screenHeight) / 2, screenHeight);
        info.setBackground(Color.PINK);

        add(boardGame);
        add(game);
        add(info);
        

        this.setVisible(true);
    }

}

