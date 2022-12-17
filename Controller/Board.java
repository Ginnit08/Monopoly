package Controller;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Board extends JFrame {
    private Image imgB;
    public Board() {
        try {
            imgB = ImageIO.read(Objects.requireNonNull(getClass().getResource("img/Board.jpg")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Drawing the board
    }

    @Override
    public void paint(Graphics g) {
        int w = getWidth();
        int h = getHeight();

        Graphics2D graphics2D = (Graphics2D) g;
        g.drawImage(imgB,0,0,2*w/3,h,this);
    }
}