package Controller;

import java.awt.Color;

import javax.swing.*;

public class Main {
    MainFrame frame;
	static int nowPlaying = 0;
	static JTextArea infoConsole;
    Player player1 = new Player(1, Color.RED);
    Player player2 = new Player(2, Color.BLUE);

    
    public Main() {
        frame = new MainFrame();
    }

    public static void main(String[] args) {
        new Main();
    }
}   