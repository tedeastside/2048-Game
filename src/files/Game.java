package files;
import java.util.Random;
import java.util.ArrayList;

public class Game {
	 int[][] gameBoard;
	 int[][] gameBoardPrev;
	 private int score;
	 private int prevScore;
	
	public Game() {
		gameBoard = new int[4][4];
		gameBoardPrev = new int[4][4];
		addNumber();
	}
	public int getScore() {
		return score;
	}
	
	public void print() {
		for(int y=0;y<4;y++) {
			for(int x=0;x<4;x++) {
				System.out.print(gameBoard[y][x]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void addNumber() {
		ArrayList<Integer> xCoordinate = new ArrayList<>();
		ArrayList<Integer> yCoordinate = new ArrayList<>();
		for(int y=0;y<4;y++) {
			for(int x=0;x<4;x++) {
				if(gameBoard[y][x]==0) {
					xCoordinate.add(x);
					yCoordinate.add(y);
				}
			}
		}
		if(xCoordinate.size()==0) {
			return;
		}
		Random r = new Random();
		int index = r.nextInt(xCoordinate.size());
		int i = yCoordinate.get(index);
		int j = xCoordinate.get(index);
		int n = r.nextInt(10);
		if(n<3) {
			gameBoard[i][j] = 4;
		}else {
			gameBoard[i][j] = 2;
		}
		
	}
	
	public void upArrow() {
		boolean[][] flag = new boolean[4][4];
		boolean operation = false;
		prevScore = score;
		for(int i=1;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(i==1) {
				gameBoardPrev[i-1][j]=gameBoard[i-1][j];
				}
				gameBoardPrev[i][j]=gameBoard[i][j];
				if(gameBoard[i][j]!=0) {
					int value = gameBoard[i][j];
					int y = i-1;
					while(y>=0 && gameBoard[y][j]==0) {
						y--;
					}
					if(y==-1) {
						gameBoard[0][j] = value;
						gameBoard[i][j]=0;
						score+=value;
						operation = true;
					}else if(value==gameBoard[y][j] && !flag[y][j]) {
						
						gameBoard[y][j] = value*2;
						gameBoard[i][j] = 0;
						flag[y][j] = true;
						score+=value;
						score+=value;
						operation = true;
						
					}else {
						gameBoard[i][j]=0;
						gameBoard[y+1][j] = value;
						if(i!=y+1) {
							operation = true;
						}
						
					}
				}
			}
		}
		if(operation) {
		addNumber();
		}
	}
	
	public void downArrow() {
		boolean[][] flag = new boolean[4][4];
		boolean operation = false;
		prevScore = score;
		for(int i=2;i>=0;i--) {
			for(int j=3;j>=0;j--) {
				if(i==2) {
					gameBoardPrev[i+1][j]=gameBoard[i+1][j];
					}
					gameBoardPrev[i][j]=gameBoard[i][j];
				if(gameBoard[i][j]!=0) {
					int value = gameBoard[i][j];
					int y = i+1;
					while(y<4 && gameBoard[y][j]==0) {
						y++;
					}
					if(y==4) {
						gameBoard[3][j]=value;
						gameBoard[i][j]=0;
						operation = true;
					}else if(gameBoard[y][j]==value && !flag[y][j]) {
						
							gameBoard[y][j]=2*value;
							gameBoard[i][j]=0;
							flag[y][j]=true;	
							score+=value;
							operation = true;
					}else {
						gameBoard[i][j]=0;
						gameBoard[y-1][j]=value;
						if(i!=y-1) {
							operation = true;
						}
					}
				}
			}
		}
		if(operation) {
			addNumber();
			}
	}
	
	public void rightArrow() {
		boolean[][] flag = new boolean[4][4];
		boolean operation = false;
		prevScore = score;
		for(int j=2;j>=0;j--) {
			for(int i=3;i>=0;i--) {
				if(j==2) {
					gameBoardPrev[i][j+1]=gameBoard[i][j+1];
					}
					gameBoardPrev[i][j]=gameBoard[i][j];
				if(gameBoard[i][j]!=0) {
					int value = gameBoard[i][j];
					int x = j+1;
					while(x<4 && gameBoard[i][x]==0) {
						x++;
					}
					if(x==4) {
						gameBoard[i][3]=value;
						gameBoard[i][j]=0;
						operation = true;
					}else if(gameBoard[i][x]==value && !flag[i][x]) {
						gameBoard[i][x]=2*value;
						gameBoard[i][j]=0;
						flag[i][x]=true;
						operation = true;
						score+=value;
					}else {
						gameBoard[i][j]=0;
						gameBoard[i][x-1]=value;
						if(j!=x-1) {
							operation = true;
						}
					}
				}
			}
		}
		if(operation) {
			addNumber();
			}
	}
	
	public void leftArrow() {
		boolean[][] flag = new boolean[4][4];
		boolean operation = false;
		prevScore = score;
		for(int j=1;j<4;j++) {
			for(int i=0;i<4;i++) {
				if(j==1) {
					gameBoardPrev[i][j-1]=gameBoard[i][j-1];
					}
					gameBoardPrev[i][j]=gameBoard[i][j];
				if(gameBoard[i][j]!=0) {
					int value = gameBoard[i][j];
					int x = j-1;
					while(x>=0 && gameBoard[i][x]==0) {
						x--;
					}
					if(x==-1) {
						gameBoard[i][0]=value;
						gameBoard[i][j]=0;
						operation = true;
					}else if(gameBoard[i][x]==value && !flag[i][x]) {
						gameBoard[i][x]=2*value;
						gameBoard[i][j]=0;
						flag[i][x]=true;
						operation =true;
						score+=value;
					}else {
						gameBoard[i][j]=0;
						gameBoard[i][x+1]=value;
						if(j!=x+1) {
							operation = true;
						}
					}
				}
			}
		}
		if(operation) {
			addNumber();
			}
	}
	
	public void prevBoard() {
		score = prevScore;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				gameBoard[i][j] = gameBoardPrev[i][j];
			}
		}
	}
	
	
}









