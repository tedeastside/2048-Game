package files;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.HashMap;
import javax.swing.*;



 class GUI {
	GameBoard gb;
	int frameHeight = 683;
	int frameWidth = 512+32+30+12;
	int gameBoardSize = 512;
	int marginSize = 16;
	Color backgroundColor = new Color(71, 252, 240);
	JLabel scoreLabel;
	
	HashMap<Integer, ImageIcon> numberTiles;
	
//	Making a new Jframe
	MyFrame frame;
	Game game;
//	Constructor
	public GUI() {
		game = new Game();
		frame = new MyFrame();
		frame.addKeyListener(new MyFrame());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		numberTiles = new HashMap<>();
		loadNumberTiles();
		
//		Game Visual Part
		
		gb = new GameBoard();
		
//		North Panel
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout());
		northPanel.setPreferredSize(new Dimension(frameWidth, 112));
		
//		Label Maker
		JLabel gameLabel = new JLabel("2048", SwingConstants.CENTER);
		gameLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 50));
		northPanel.add(gameLabel);
		scoreLabel = new JLabel("<html><h1>Score:<br>0</h1></html>", SwingConstants.CENTER);
		northPanel.add(scoreLabel);
		northPanel.add(new JLabel("<html><h1>High Score:<br>1025</h1></html>", SwingConstants.CENTER));
		northPanel.setBackground(backgroundColor);
		
//		Other Panels
		JPanel westBuffer = new JPanel();
		westBuffer.setPreferredSize(new Dimension(marginSize, gameBoardSize));
		westBuffer.setBackground(backgroundColor);
		
		JPanel eastBuffer = new JPanel();
		eastBuffer.setPreferredSize(new Dimension(marginSize, gameBoardSize));
		eastBuffer.setBackground(backgroundColor);
		
		JPanel southBuffer = new JPanel();
		southBuffer.setPreferredSize(new Dimension(frameWidth, marginSize));
		southBuffer.setBackground(backgroundColor);
		
		
//		Adding above Panels
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		frame.getContentPane().add(westBuffer, BorderLayout.WEST);
		frame.getContentPane().add(eastBuffer, BorderLayout.EAST);
		frame.getContentPane().add(southBuffer, BorderLayout.SOUTH);
		frame.getContentPane().add(gb, BorderLayout.CENTER);
		
//		Making Sure its ready to go for all computer dimension
		frame.getContentPane().setPreferredSize(new Dimension(frameWidth, frameHeight));
		frame.pack();
		frame.setVisible(true);
		
		
		
	}
	
	public void loadNumberTiles() {
	
		numberTiles.put(0, new ImageIcon(getClass().getResource("/images/0.png")));
		numberTiles.put(2, new ImageIcon(getClass().getResource("/images/2.png")));
		numberTiles.put(4, new ImageIcon(getClass().getResource("/images/4.png")));
		numberTiles.put(8, new ImageIcon(getClass().getResource("/images/8.png")));
		numberTiles.put(16, new ImageIcon(getClass().getResource("/images/16.png")));
		numberTiles.put(32, new ImageIcon(getClass().getResource("/images/32.png")));
		numberTiles.put(64, new ImageIcon(getClass().getResource("/images/64.png")));
		numberTiles.put(128, new ImageIcon(getClass().getResource("/images/128.png")));
		numberTiles.put(256, new ImageIcon(getClass().getResource("/images/256.png")));
		numberTiles.put(512, new ImageIcon(getClass().getResource("/images/512.png")));
		numberTiles.put(1024, new ImageIcon(getClass().getResource("/images/1024.png")));
		numberTiles.put(2048, new ImageIcon(getClass().getResource("/images/2048.png")));
	}
	
	class GameBoard extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			g.setColor(new Color(255, 253, 208));
			g.fillRect(0, 0, this.getHeight(), this.getWidth());
				int[][] board = game.gameBoard;
				for(int i=0;i<4;i++) {
					for(int j=0;j<4;j++) {
						int thisNum = board[i][j];
						int x = 8*(j+1)+128*j;
						int y = 8*(i+1)+128*i;
						
						ImageIcon thisTile = numberTiles.get(thisNum);
						thisTile.paintIcon(this, g, x, y);
					}
				} 
		}
		
	}
	class MyFrame extends JFrame implements KeyListener{
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			if(key== KeyEvent.VK_UP) {
//				System.out.println("Up Key Pressed");
				game.upArrow();
//				game.print();
				gb.repaint();
				updateScore();
			}
			if(key== KeyEvent.VK_DOWN) {
//				System.out.println("Down Key Pressed");
				game.downArrow();
//				game.print();
				gb.repaint();
				updateScore();
			}
			if(key== KeyEvent.VK_LEFT) {
//				System.out.println("Left Key Pressed");
				game.leftArrow();
//				game.print();
				gb.repaint();
				updateScore();
			}
			if(key== KeyEvent.VK_RIGHT) {
//				System.out.println("Right Key Pressed");
				game.rightArrow();
//				game.print();
				gb.repaint();
				updateScore();
				 	
			}
			if(key == KeyEvent.VK_BACK_SPACE) {
//				System.out.println("Backspace Key Pushed");
				game.prevBoard();
//				game.print();
				gb.repaint();
				updateScore();
			}
			
		}
		public void updateScore() {
			scoreLabel.setText("<html><h1>Score:<br>"+ game.getScore()+"</h1></html>");
		}
	}
}
