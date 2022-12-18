package Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MainFrame extends JFrame{

    public MainFrame() {

        setTitle("Monopoly");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File("img/Background_Start.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image image = bufferedImage.getScaledInstance(1536, 864, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(image);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(icon);

        JLabel welcome = new JLabel("Welcome to my game!");
        welcome.setBounds(550, 45, 700, 100);
        welcome.setFont(new Font("Comic Sans", Font.BOLD, 50));


        JButton startBtn = new JButton("Start");
        startBtn.setBounds(700, 400, 200, 75);
        startBtn.setFont(new Font("Comic Sans",Font.BOLD,25));
        startBtn.setIconTextGap(-15);
        startBtn.setIcon(new ImageIcon("/img/btn.jpg"));
        startBtn.setFocusable(false);
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.dispose();
                try {
                    Board board = new Board();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton musicBtn = new JButton("Music");
        musicBtn.setIcon(new ImageIcon("/img/btn.jpg"));
        musicBtn.setBounds(700, 500, 200, 75);
        musicBtn.setFont(new Font("Comic Sans",Font.BOLD,25));
        musicBtn.setIconTextGap(-15);
        musicBtn.setFocusable(false);

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(1300, 700, 200, 75);
        exitBtn.setFont(new Font("Comic Sans",Font.BOLD,25));
        exitBtn.setIconTextGap(-15);
        exitBtn.setFocusable(false);

        add(startBtn);
        add(musicBtn);
        add(exitBtn);
        add(welcome);
        add(jLabel);
        setVisible(true);
        setResizable(false);
    }
}