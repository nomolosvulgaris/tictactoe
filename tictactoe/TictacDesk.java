package tictactoe;

import boards.Messenger;

public class TictacDesk extends boards.Desk {

	public final static int DESK_NOUGHTS = 1;
	public final static int DESK_CROSSES = 2;
	public final static int DESK_DRAW = 3;

	private int winSequenceLength = 3;

	private int[][] shift = new int[][]{{0, 1}, {1, 0}, {1, 1}};

	public TictacDesk(int width, int height, Messenger messenger) {
		super(width, height, messenger);
		setWinSequenceLength((width + height) / 2);
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				field[row][col] = new TictacItem();
			}
		}
	}

	public void drawField() {
		System.out.print(" R/C");
		for (int col = 0; col < width; col++) {
			System.out.print(" " + (col + 1) + "  ");
		}
		System.out.print('\n');
		super.drawField();
	}

	public void drawRow(int row) {
		System.out.print(String.format("%2d  ", row + 1));
		super.drawRow(row);
		System.out.print('\n');
	}

	public int howLongPotentially(int row, int col, int itemState, int[] shift) {
		int solid = howLong(row, col, itemState, shift),
			empties = howLong(row + shift[0] * solid, col + shift[1] * solid, TictacItem.STATE_EMPTY, shift);
		return solid + empties;
	}

	public int howLong(int row, int col, int itemState, int[] shift) {
		if (!isState(row, col, itemState)) {
			return 0;
		}
		int shiftedRow, shiftedCol;
		for (int distance = 1; distance < winSequenceLength; distance++) {
			shiftedRow = row + distance * shift[0];
			shiftedCol = col + distance * shift[1];
			if (!isValid(shiftedRow, shiftedCol) || field[shiftedRow][shiftedCol].getState() != itemState) {
				return distance;
			}
		}
		return winSequenceLength;
	}

	private boolean isConsummate(int row, int col) {

		int itemState = field[row][col].getState();
		for (int direction = 0; direction < shift.length; direction++) {
			if (howLong(row, col, itemState, shift[direction]) >= winSequenceLength) {
				return true;
			}
		}

		return false;
	}

	public boolean isState(int row, int col, int state) {
		return isValid(row, col) && field[row][col].getState() == state;
	}

	public boolean isEmpty(int row, int col) {
		return isState(row, col, TictacItem.STATE_EMPTY);
	}

	public int isFinished() {
		int itemState = TictacItem.STATE_EMPTY, empties = 0;
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (isEmpty(row, col)) {
					empties++;
				} else if (isConsummate(row, col)) {
					itemState = field[row][col].getState();
					return itemState == TictacItem.STATE_CROSS ? DESK_CROSSES : DESK_NOUGHTS;
				}
			}
		}

		return empties == 0 ? DESK_DRAW : DESK_PROCESS;
	}

	public void setWinSequenceLength(int winSequenceLength) {
		this.winSequenceLength = winSequenceLength;
	}

	public int getWinSequenceLength() {
		return winSequenceLength;
	}

}
