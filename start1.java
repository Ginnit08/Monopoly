public class Start extends JFrame {
    private JButton jButton;
    private JLabel jLabel;

    public Start() {
        this.setTitle("monopoly");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        URL urlIconNotepad = Start.class.getResource("icon_notepad.jpg");
        Image img = Toolkit.getDefaultToolkit().createImage(urlIconNotepad);
        this.setIconImage(img);
        jLabel = new JLabel();
        jLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Start.class.getResource("Background_Start.png"))));
        jButton = new JButton("");
        jButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Start.class.getResource("start.png"))));
        jButton.setFont(new Font("Arial", Font.BOLD, 50));
        jButton.setSize(1, 1);
        this.add(jLabel, BorderLayout.CENTER);
        this.add(jButton, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new Start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
