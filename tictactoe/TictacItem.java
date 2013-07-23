package tictactoe;

public class TictacItem extends boards.Item {

	public final static int STATE_NOUGHT = 1;
	public final static int STATE_CROSS = 2;
	private final static char[] symbol = new char[]{'.', 'o', 'x'};

	public void display(int row, int col) {
		System.out.print("[" + symbol[state] + "] ");
	}

}