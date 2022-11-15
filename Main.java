import javax.swing.*;
import java.awt.*;

public class Main {
    private static Board board;

    public static void main(String[] args) {
        Start start = new Start();
        
        board = new Board();
        board.setPreferredSize(new Dimension(900,600));

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);

        JPanel jpanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jpanel,BoxLayout.X_AXIS);
        jpanel.setLayout(boxLayout);
        jpanel.add(board);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = dimension.width;
        int y = dimension.height;

        JFrame jframe = new JFrame("Richy Mony");
        jframe.setSize(900,600);
        jframe.setLocation( x / 2 - jframe.getWidth()/2, y / 2 - jframe.getHeight()/2);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        jframe.add(jpanel);
        jframe.pack();
        jframe.setVisible(true);

    }
}
