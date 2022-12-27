package Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;
import java.util.ArrayList;

public class Board extends JFrame {
    private ArrayList<Square> allSquares = new ArrayList<Square>();
    private ArrayList<Square> unbuyableSquares = new ArrayList<Square>(); // squares like "Go", "Chances" etc...
    private int screenWidth = 1536;
    private int screenHeight = 864;

	static int turnCounter = 0;
    static int nowPlaying = 0;
	static JTextArea infoConsole;

	ArrayList<Player> players = new ArrayList<Player>();
    JButton btnNextTurn;
	JButton btnRollDice;
	JButton btnPayRent;
	JButton btnBuy;
    JTextArea panelPlayer1TextArea;
	JTextArea panelPlayer2TextArea;
    Player player1;
	Player player2;
	Boolean doubleDiceForPlayer1 = false;
	Boolean doubleDiceForPlayer2 = false;


    public Board() {
        setTitle("Monopoly");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        player1 = new Player(1, Color.RED);
		players.add(player1);
        add(player1);
        player2 = new Player(2, Color.blue);
		players.add(player2);
        add(player2);

        //Board panels
        JPanel boardGame = new JPanel();
        boardGame.setBounds(0, 0, screenHeight, screenHeight);
        boardGame.setBackground(new Color(236, 185, 57));
        initializeCell();

        // Game running
        JPanel game = new JPanel();
        game.setBounds(screenHeight, 0, (screenWidth - screenHeight) / 2, screenHeight);
        game.setLayout(null);
        infoConsole = new JTextArea();
		infoConsole.setColumns(20);
		infoConsole.setRows(5);
		infoConsole.setBounds(game.getX() + 10, game.getY() + 50, game.getWidth() - 20, 80);
        game.add(infoConsole);
        infoConsole.setLineWrap(true);
		infoConsole.setText("PLayer 1 starts the game by clicking Roll Dice!");

        Dice dice1 = new Dice(game.getX() + 120, infoConsole.getHeight() + 120, 40, 40);
        game.add(dice1);

        Dice dice2 = new Dice(game.getX() + 170, infoConsole.getHeight() + 120, 40, 40);
        game.add(dice2);

        btnRollDice = new JButton("Roll Dice");
		btnRollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(nowPlaying == 0) {
					// player1's turn
					int dice1OldValue = dice1.getFaceValue();
					int dice2OldValue = dice2.getFaceValue();
					dice1.rollDice();
					dice2.rollDice();
					int dicesTotal = dice1.getFaceValue() + dice2.getFaceValue();
					if(dice1.getFaceValue() == dice2.getFaceValue()) {
						doubleDiceForPlayer1 = true;
					} else {
						doubleDiceForPlayer1 = false;
					}
					player1.move(dicesTotal);
					if(Player.ledger.containsKey(player1.getCurrentSquareNumber()) // if bought by someone
							&& Player.ledger.get(player1.getCurrentSquareNumber()) != player1.getPlayerNumber() // not by itself
							) {
						btnBuy.setEnabled(false);
						btnRollDice.setEnabled(false);
						btnNextTurn.setEnabled(false);
						btnPayRent.setEnabled(true);
					} 
					if (Player.ledger.containsKey(player1.getCurrentSquareNumber()) // if bought by someone 
							&& Player.ledger.get(player1.getCurrentSquareNumber()) == player1.getPlayerNumber()) { // and by itself
						btnBuy.setEnabled(false);
						btnPayRent.setEnabled(false);
						btnNextTurn.setEnabled(true);
					}
					if(getUnbuyableSquares().contains(getAllSquares().get(player1.getCurrentSquareNumber()))) {
						btnBuy.setEnabled(false);
						btnNextTurn.setEnabled(true);
					} else if (!Player.ledger.containsKey(player1.getCurrentSquareNumber())) { // if not bought by someone
						btnBuy.setEnabled(true);
						btnNextTurn.setEnabled(true);
						btnPayRent.setEnabled(false);
					} 
					
	
				} else {
					// player2's turn
					int dice1OldValue = dice1.getFaceValue();
					int dice2OldValue = dice2.getFaceValue();
					dice1.rollDice();
					dice2.rollDice();
					int dicesTotal = dice1.getFaceValue() + dice2.getFaceValue();
					if(dice1.getFaceValue() == dice2.getFaceValue()) {
						doubleDiceForPlayer2 = true;
					} else {
						doubleDiceForPlayer2 = false;
					}
					player2.move(dicesTotal);
					if(Player.ledger.containsKey(player2.getCurrentSquareNumber()) // if bought by someone
							&& Player.ledger.get(player2.getCurrentSquareNumber()) != player2.getPlayerNumber() // not by itself
							) {
						btnBuy.setEnabled(false);
						btnRollDice.setEnabled(false);
						btnNextTurn.setEnabled(false);
						btnPayRent.setEnabled(true);
					}
					if(Player.ledger.containsKey(player2.getCurrentSquareNumber()) // if bought by someone 
							&& Player.ledger.get(player2.getCurrentSquareNumber()) == player2.getPlayerNumber()) { // and by itself
						btnBuy.setEnabled(false);
						btnPayRent.setEnabled(false);

					}
					if(getUnbuyableSquares().contains(getAllSquares().get(player2.getCurrentSquareNumber()))) {
						btnBuy.setEnabled(false);
						btnNextTurn.setEnabled(true);
					} else if (!Player.ledger.containsKey(player2.getCurrentSquareNumber())) { // if not bought by someone
						btnBuy.setEnabled(true);
						btnNextTurn.setEnabled(true);
						btnPayRent.setEnabled(false);
					}

				}

				btnRollDice.setEnabled(false);
				if(doubleDiceForPlayer1 || doubleDiceForPlayer2) {
					infoConsole.setText("Click Next Turn to allow player "+ (nowPlaying==0 ? 1 : 2) +" to Roll Dice!");
				} else {
					infoConsole.setText("Click Next Turn to allow player "+ (nowPlaying==0 ? 2 : 1) +" to Roll Dice!");
				}
				

				// we have to add below 2 lines to avoid some GUI breakdowns.
				// layeredPane.remove(gameBoard);
				// layeredPane.add(gameBoard, new Integer(0));
				
				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();

			}
		});
		btnRollDice.setBounds(infoConsole.getX() + 50, 300, game.getWidth() - 100, 53);
		game.add(btnRollDice);

        btnNextTurn = new JButton("Next Turn");
		btnNextTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnRollDice.setEnabled(true);
				btnBuy.setEnabled(false);
				btnPayRent.setEnabled(false);
				btnNextTurn.setEnabled(false);
				
				if(nowPlaying == 0 && doubleDiceForPlayer1) {
					nowPlaying = 0;
					doubleDiceForPlayer1 = false;
				} else if(nowPlaying == 1 && doubleDiceForPlayer2) {
					nowPlaying = 1;
					doubleDiceForPlayer2 = false;
				} else if(!doubleDiceForPlayer1 && !doubleDiceForPlayer2) {
					nowPlaying = (nowPlaying + 1) % 2;
				}
				
				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();
				infoConsole.setText("It's now player "+(nowPlaying==0 ? 1 : 2)+"'s turn!");
			}

		

		});
		btnNextTurn.setBounds(btnRollDice.getX(), btnRollDice.getY() + btnRollDice.getHeight() + 20, btnRollDice.getWidth(), 53);
		game.add(btnNextTurn);
		btnNextTurn.setEnabled(false);

        btnBuy = new JButton("Buy");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//turnCounter--; // decrease because we increased at the end of the rolldice
				Player currentPlayer = players.get(nowPlaying);
				infoConsole.setText("You bought "+getAllSquares().get(currentPlayer.getCurrentSquareNumber()).getName());
				currentPlayer.buyTitleDeed(currentPlayer.getCurrentSquareNumber());
				int withdrawAmount = getAllSquares().get(currentPlayer.getCurrentSquareNumber()).getPrice();
				currentPlayer.withdrawFromWallet(withdrawAmount);
				btnBuy.setEnabled(false);
				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();
				//turnCounter++;
			}
		});
		btnBuy.setBounds(btnRollDice.getX(), btnNextTurn.getY() + btnNextTurn.getHeight() + 20, btnRollDice.getWidth() / 2 - 10, 29);
		btnBuy.setEnabled(false);
		game.add(btnBuy);

        btnPayRent = new JButton("Pay Rent");
		btnPayRent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// turnCounter--;
				Player currentPlayer = players.get(nowPlaying);
				Player ownerOfTheSquare = players.get((Player.ledger.get(currentPlayer.getCurrentSquareNumber()))==1?0:1);
				infoConsole.setText("You paid to the player "+ownerOfTheSquare.getPlayerNumber());

				int withdrawAmount = getAllSquares().get(currentPlayer.getCurrentSquareNumber()).getRentPrice();
				System.out.println(withdrawAmount);
				currentPlayer.withdrawFromWallet(withdrawAmount);
				ownerOfTheSquare.depositToWallet(withdrawAmount);
				
				btnNextTurn.setEnabled(true);
				btnPayRent.setEnabled(false);
				//currentPlayer.withdrawFromWallet(withdrawAmount);
				updatePanelPlayer1TextArea();
				updatePanelPlayer2TextArea();
				//turnCounter++;
				//gameBoard.getAllSquares().get(player1.getCurrentSquareNumber()).setRentPaid(true);
			}

		});
		btnPayRent.setBounds(btnBuy.getX() + btnBuy.getWidth() + 20, btnBuy.getY(), btnBuy.getWidth(), 29);
		game.add(btnPayRent);
		btnPayRent.setEnabled(false);


        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(btnRollDice.getX(), screenHeight - 160, btnRollDice.getWidth(), 40);
        exitBtn.setFont(new Font("Comic Sans",Font.BOLD,25));
        exitBtn.setIconTextGap(-15);
        exitBtn.setFocusable(false);
        exitBtn.addActionListener(e -> {
            if (e.getSource() == exitBtn) {
                dispose();
            }
        });
        game.add(exitBtn);

        // game info 
        JPanel info = new JPanel();
        info.setBounds(screenHeight + (screenWidth - screenHeight) / 2, 0, (screenWidth - screenHeight) / 2, screenHeight);

        JPanel infoPlayer1 = new JPanel();
		infoPlayer1.setBorder(new LineBorder(new Color(0, 0, 0)));
        infoPlayer1.setBounds(screenHeight + (screenWidth - screenHeight) / 2, 0, (screenWidth - screenHeight) / 2, screenHeight/2 - 30);
        infoPlayer1.setBackground(Color.RED);
        infoPlayer1.setLayout(null);
        
        
        JLabel panelPlayer1Title = new JLabel("Player 1 All Wealth");
		panelPlayer1Title.setForeground(Color.WHITE);
		panelPlayer1Title.setHorizontalAlignment(SwingConstants.CENTER);
        panelPlayer1Title.setBounds(0, 0, infoPlayer1.getWidth(), 40);
        
        panelPlayer1TextArea = new JTextArea();
        panelPlayer1TextArea.setBounds(10, panelPlayer1Title.getHeight(), infoPlayer1.getWidth() - 20, infoPlayer1.getHeight() - panelPlayer1Title.getHeight() - 10);
        infoPlayer1.add(panelPlayer1Title);
        infoPlayer1.add(panelPlayer1TextArea);




        JPanel infoPlayer2 = new JPanel();
		infoPlayer2.setBorder(new LineBorder(new Color(0, 0, 0)));
        infoPlayer2.setBounds(screenHeight + (screenWidth - screenHeight) / 2, infoPlayer1.getHeight() , (screenWidth - screenHeight) / 2, screenHeight/2);
        infoPlayer2.setBackground(Color.BLUE);
        infoPlayer2.setLayout(null);

        JLabel panelPlayer2Title = new JLabel("Player 2 All Wealth");
		panelPlayer2Title.setForeground(Color.WHITE);
		panelPlayer2Title.setHorizontalAlignment(SwingConstants.CENTER);
        panelPlayer2Title.setBounds(0, 0, infoPlayer2.getWidth(), 40);

        
        panelPlayer2TextArea = new JTextArea();
        panelPlayer2TextArea.setBounds(10, panelPlayer2Title.getHeight(), infoPlayer2.getWidth() - 20, infoPlayer2.getHeight() - panelPlayer2Title.getHeight() - 50);
        infoPlayer2.add(panelPlayer2Title);
        infoPlayer2.add(panelPlayer2TextArea);

        updatePanelPlayer1TextArea();
		updatePanelPlayer2TextArea();

        add(infoPlayer1);
        add(infoPlayer2);

        add(boardGame);
        add(game);

        this.setVisible(true);
    }

    public void initializeCell() {
        String[] squareNames = {
                "Go",
                "Ha Noi",
                "Community Chest",
                "Hai Phong",
                "Phu Yen",
                "Cao Bang",
                "Lang Son",
                "Thai Nguyen",
                "Roll once",
                "Son La",
                "Squeeze Play",
                "Ha Tien",
                "Da Nang",
                "Community Chest",
                "Binh Thuan",
                "Ninh Thuan",
                "Free Parking",
                "Khanh Hoa",
                "Pay Taxes",
                "Binh Duong",
                "Ho Chi Minh",
                "Dong Nai",
                "Squeeze Play",
                "Tay Ninh",
                "Jail",
                "Vung Tau",
                "Long An",
                "Ben Tre",
                "Chance",
                "Vinh Long",
                "Phan Thiet",
                "Quang Ngai"
        };


        // squares on the top
        int x_top = 6;
        int y_top = 6;

        Square square00 = new Square(16, y_top, 110, 100, squareNames[0], -45);
        this.add(square00);
        allSquares.add(square00);
        unbuyableSquares.add(square00);

        Square square01 = new Square(126, y_top, 80, 100, squareNames[1], 0);
        this.add(square01);
        allSquares.add(square01);

        Square square02 = new Square(206, y_top, 100, 100, squareNames[2], 0);
        this.add(square02);
        allSquares.add(square02);
        unbuyableSquares.add(square02);

        Square square03 = new Square(306, y_top, 80, 100, squareNames[3], 0);
        this.add(square03);
        allSquares.add(square03);

        Square square04 = new Square(386, y_top, 80, 100, squareNames[4], 0);
        this.add(square04);
        allSquares.add(square04);

        Square square05 = new Square(466, y_top, 80, 100, squareNames[5], 0);
        this.add(square05);
        allSquares.add(square05);

        Square square06 = new Square(546, y_top, 80, 100, squareNames[6], 0);
        this.add(square06);
        allSquares.add(square06);

        Square square07 = new Square(626, y_top, 80, 100, squareNames[7], 0);
        this.add(square07);
        allSquares.add(square07);

        Square square08 = new Square(706, y_top, 110, 100, squareNames[8], 45);
        this.add(square08);
        allSquares.add(square08);
        unbuyableSquares.add(square08);

        // squares on the right

        int x_right =  706;
        int y_right = 106;

        Square square09 = new Square(x_right, 106, 110, 80, squareNames[9], 0);
        this.add(square09);
        allSquares.add(square09);

        Square square10 = new Square(x_right, 186, 110, 80, squareNames[10], 0);
        this.add(square10);
        allSquares.add(square10);
        unbuyableSquares.add(square10);

        Square square11 = new Square(x_right, 266, 110, 80, squareNames[11], 0);
        this.add(square11);
        allSquares.add(square11);

        Square square12 = new Square(x_right, 346, 110, 80, squareNames[12], 0);
        this.add(square12);
        allSquares.add(square12);

        Square square13 = new Square(x_right, 426, 110, 80, squareNames[13], 0);
        this.add(square13);
        allSquares.add(square13);
        unbuyableSquares.add(square13);


        Square square14 = new Square(x_right, 506, 110, 80, squareNames[14], 0);
        this.add(square14);
        allSquares.add(square14);

        Square square15 = new Square(x_right, 586, 110, 80, squareNames[15], 0);
        this.add(square15);
        allSquares.add(square15);


        Square square16 = new Square(x_right, 666, 110, 100, squareNames[16], -45);
        this.add(square16);
        allSquares.add(square16);
        unbuyableSquares.add(square16);

        // squares on the bottom
        int x_bot = 606;
        int y_bot = 666;

        Square square17 = new Square(626, y_bot, 80, 100, squareNames[17], 0);
        this.add(square17);
        allSquares.add(square17);

        Square square18 = new Square(546, y_bot, 80, 100, squareNames[18], 0);
        this.add(square18);
        allSquares.add(square18);
        unbuyableSquares.add(square18);

        Square square19 = new Square(466, y_bot, 80, 100, squareNames[19], 0);
        this.add(square19);
        allSquares.add(square19);

        Square square20 = new Square(386, y_bot, 80, 100, squareNames[20], 0);
        this.add(square20);
        allSquares.add(square20);

        Square square21 = new Square(306, y_bot, 80, 100, squareNames[21], 0);
        this.add(square21);
        allSquares.add(square21);

        Square square22 = new Square(206, y_bot, 100, 100, squareNames[22], 0);
        this.add(square22);
        allSquares.add(square22);
        unbuyableSquares.add(square22);

        Square square23 = new Square(126, y_bot, 80, 100, squareNames[23], 0);
        this.add(square23);
        allSquares.add(square23);

        Square square24 = new Square(16, y_bot, 110, 100, squareNames[24], 45);
        this.add(square24);
        allSquares.add(square24);
        unbuyableSquares.add(square24);

        // squares on the left
        int x_left = 16;
        int y_left = 586;

        Square square25 = new Square(x_left, 586, 110, 80, squareNames[25], 0);
        this.add(square25);
        allSquares.add(square25);

        Square square26 = new Square(x_left, 506, 110, 80, squareNames[26], 0);
        this.add(square26);
        allSquares.add(square26);


        Square square27 = new Square(x_left, 426, 110, 80, squareNames[27], 0);
        this.add(square27);
        allSquares.add(square27);

        Square square28 = new Square(x_left, 346, 110, 100, squareNames[28], 0);
        this.add(square28);
        allSquares.add(square28);
        unbuyableSquares.add(square28);

        Square square29 = new Square(x_left, 266, 110, 80, squareNames[29], 0);
        this.add(square29);
        allSquares.add(square29);


        Square square30 = new Square(x_left, 186, 110, 80, squareNames[30], 0);
        this.add(square30);
        allSquares.add(square30);

        Square square31 = new Square(x_left, 106, 110, 80, squareNames[31], 0);
        this.add(square31);
        allSquares.add(square31);


        // setting prices
        square01.setPrice(100);
        square03.setPrice(100);
        square04.setPrice(120);

        square05.setPrice(160);
        square06.setPrice(140);
        square07.setPrice(180);

        square14.setPrice(200);

        square16.setPrice(300);
        square17.setPrice(300);
        square19.setPrice(320);

        // setting rent prices
        square01.setRentPrice(6);
        square03.setRentPrice(6);
        square04.setRentPrice(8);

        square06.setRentPrice(10);
        square08.setRentPrice(10);
        square09.setRentPrice(12);

        square11.setRentPrice(14);
        square14.setRentPrice(16);

        square16.setRentPrice(26);
        square17.setRentPrice(26);
        square19.setRentPrice(28);


        JLabel lblMonopoly = new JLabel("MONOPOLY") {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                AffineTransform aT = g2.getTransform();
                Shape oldshape = g2.getClip();
                double x = getWidth() / 2.0;
                double y = getHeight() / 2.0;
                aT.rotate(Math.toRadians(-35), x, y);
                g2.setTransform(aT);
                g2.setClip(oldshape);
                super.paintComponent(g);
            }
        };
        lblMonopoly.setForeground(new Color(107, 227, 29));
        lblMonopoly.setBackground(Color.WHITE);
        lblMonopoly.setOpaque(true);
        lblMonopoly.setHorizontalAlignment(SwingConstants.CENTER);
        lblMonopoly.setFont(new Font("Lucida Grande", Font.BOLD, 60));
        lblMonopoly.setBounds(176, 316, 500, 120);
        this.add(lblMonopoly);

    }

    
    public ArrayList<Square> getUnbuyableSquares() {
        return unbuyableSquares;
    }

    public ArrayList<Square> getAllSquares() {
        return allSquares;
    }

    public Square getSquareAtIndex(int location) {
        return allSquares.get(location);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
    }

    public void updatePanelPlayer2TextArea() {
		// TODO Auto-generated method stub
		String result = "";
		result += "Current Balance: "+player2.getWallet()+"\n";
		
		result += "Title Deeds: \n";
		for(int i = 0; i < player2.getTitleDeeds().size(); i++) {
			result += " - "+ getAllSquares().get(player2.getTitleDeeds().get(i)).getName()+"\n";
		}
		
		panelPlayer2TextArea.setText(result);
	}

	public void updatePanelPlayer1TextArea() {
		// TODO Auto-generated method stub
		String result = "";
		result += "Current Balance: "+player1.getWallet()+"\n";
		
		result += "Title Deeds: \n";
		for(int i = 0; i < player1.getTitleDeeds().size(); i++) {
			result += " - "+ getAllSquares().get(player1.getTitleDeeds().get(i)).getName()+"\n";
		}
		
		
		panelPlayer1TextArea.setText(result);
	}

    public static void errorBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.ERROR_MESSAGE);
	}

}
