package Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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

        JLabel welcome = new JLabel("Welcom to my game!");
        welcome.setBounds(550, 45, 500, 100);
        welcome.setFont(new Font("Comic Sans", Font.ITALIC, 50));

        JButton b1 = new JButton("Start");
        b1.setBounds(700, 400, 200, 75);
        b1.setFont(new Font("Comic Sans",Font.BOLD,25));
        b1.setIconTextGap(-15);

        JButton b2 = new JButton("Music");
        b2.setBounds(700, 500, 200, 75);
        b2.setFont(new Font("Comic Sans",Font.BOLD,25));
        b2.setIconTextGap(-15);
        
        JButton b3 = new JButton("Exit");
        b3.setBounds(1300, 700, 200, 75);
        b3.setFont(new Font("Comic Sans",Font.BOLD,25));
        b3.setIconTextGap(-15);

        add(b1);
        add(b2);
        add(b3);
        add(welcome);
        add(jLabel);
        setVisible(true);
    }
}