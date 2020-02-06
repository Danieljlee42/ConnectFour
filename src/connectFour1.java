import java.util.Scanner;
public class connectFour1 {
	public static void main(String[] args) {
		board b = new board();
		boolean gameProgress = true;
		Scanner input = new Scanner (System.in);
		while (gameProgress) {
			b.getboard();
			gameProgress = b.checkWinner("Y");
			if (gameProgress) {
				System.out.print("Drop a red disk at column (0-6): ");
				b.dropDisk("R",input.nextInt());
				b.getboard();
				gameProgress = b.checkWinner("R");
				if(gameProgress) {
					System.out.print("Drop a yellow disk at column (0-6): ");
					b.dropDisk("Y",input.nextInt());
				}
			}
		}
		input.close();
	}
}

class board{
	Scanner input = new Scanner(System.in);
	private String[][] b ={
			{"|"," ","|"," ","|"," ","|"," ","|"," ","|"," ","|"," ","|"},
			{"|"," ","|"," ","|"," ","|"," ","|"," ","|"," ","|"," ","|"},
			{"|"," ","|"," ","|"," ","|"," ","|"," ","|"," ","|"," ","|"},
			{"|"," ","|"," ","|"," ","|"," ","|"," ","|"," ","|"," ","|"},
			{"|"," ","|"," ","|"," ","|"," ","|"," ","|"," ","|"," ","|"},
			{"|"," ","|"," ","|"," ","|"," ","|"," ","|"," ","|"," ","|"}};
	private int [] counter = {5,5,5,5,5,5,5};
	public board () {
	}
	public void getboard() {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				System.out.print(b[i][j]);
			}
			System.out.println();
		}
		System.out.println("...............");
	}
	public static int getColumn(int c) {
		return 1+(c*2);
	}
	public void dropDisk (String color, int column) {
		boolean flag = true;
		while (flag) {
			try {
				b[counter[column]][getColumn(column)] = color;
				counter[column]--;
				flag = false;
			}catch(IndexOutOfBoundsException ex) {
				System.out.print("Input a valid number: ");
				column = input.nextInt();
				flag = true;
			}
		}
	}
	public boolean checkWinner (String color) {
		String name;
		if (color.equals("R")) {name = "red";}
		else name = "yellow";
		//vertical 
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 7; j++) {
				int inARow = 0;
				for (int k = 0; k < 4; k++) {
					if (b[2-i+k][getColumn(j)].equals(color)) {
						inARow++;
					}
				}
				if (inARow == 4) {
					System.out.println("The "+name+" player won.");
					return false;
					
				}
			}
		}
		//horizontal
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				int inARow = 0;
				for (int k = 0; k < 4; k++) {
					if (b[i][getColumn(j+k)].equals(color)) {
						inARow++;
					}
				}
				if (inARow == 4) {
					System.out.println("The "+name+" player won.");
					return false;
				}
			}
		}
		//diagonal left to right
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				int inARow = 0;
				for (int k = 0, l = 3; k < 4; k++, l--) {
					if (b[i+k][getColumn(j+l)].equals(color)) {
						inARow++;
					}
				}
				if (inARow == 4) {
					System.out.println("The "+name+" player won.");
					return false;
				}
			}
		}
		//diagonal right to left
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				int inARow = 0;
				for (int k = 0, l = 0; k < 4; k++, l++) {
					if (b[i+k][getColumn(j+l)].equals(color)) {
						inARow++;
					}
				}
				if (inARow == 4) {
					System.out.println("The "+name+" player won.");
					return false;
				}
			}
		}
		//Check draw 
		int sum = 0;
		for (int i = 0; i < 7; i++) {
			sum+=counter[i];
		}
		if (sum == -7) {
			System.out.println("draw");
			return false;
		}
		return true;
	}

}