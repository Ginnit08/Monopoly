package Controller;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Board extends JFrame{


    private Image imgB;
    public Board() throws IOException {
        setTitle("Monopoly");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        setLayout(null);

        try {
            imgB = ImageIO.read(new File("img/Board.jpg"));
            imgB.getScaledInstance(854,854,Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void paint(Graphics g) {
        int w = getWidth();
        int h = getHeight();

        Graphics2D graphics2D = (Graphics2D) g;
        g.drawImage(imgB,0,0,2*w/3,h,this);
    }


}