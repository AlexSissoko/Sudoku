import java.util.Scanner;

public class Sudoku {

	int[][] board;

	Sudoku() {
		board = new int[9][9];
	}

	Sudoku(String s) {
		board = new int[9][9];
		initBoard(s);
	}

	public void initBoard(String s) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				board[i][j] = s.charAt(9 * i + j) - '0';
		}
	}

	private boolean checkRows(int r, int c) {
		for (int i = 0; i < 9; i++)
			if (i != c && board[r][c] == board[r][i]) return false;
		return true;
	}

	private boolean checkColumn(int r, int c) {
		for (int i = 0; i < 9; i++)
			if (i != r && board[r][c] == board[i][c]) return false;
		return true;
	}

	private boolean checkQuadrant(int r, int c) {
		for (int a = (r / 3) * 3, i = a; i < a + 3; i++) {
			for (int b = (c / 3) * 3, j = b; j < b + 3; j++)
				if (!(r == i && c == j) && board[r][c] == board[i][j])
					return false;
		}
		return true;

	}

	public boolean solveGrid() {
		int r, c = r = 0;
		for (; r < 9; r++) {
			for (; c < 9; c++)
				if (board[r][c] == 0) break;
			if (c != 9) break;
		}
		if (c == 9) return true; // Filled table
		for (int i = 1; i < 10; i++) {
			board[r][c] = i;
			if (checkRows(r, c) && checkColumn(r, c) && checkQuadrant(r, c))
				if (solveGrid()) return true; // Recurse

		}
		board[r][c] = 0; // value wasn't valid, don't keep it
		return false; // Go back from branch
	}

	public void printGrid() {
		for (int r = 0; r < 9; r++)
			for (int c = 0; c < 9; c++) {
				System.out.print(board[r][c] + " ");
				if (c == 8) System.out.print("\n");
			}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Sudoku table = new Sudoku(in.nextLine());
		if (table.solveGrid()) table.printGrid();
		in.close();
	}
}