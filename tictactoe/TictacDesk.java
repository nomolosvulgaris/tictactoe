package tictactoe;

import boards.Item;
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

	public void drawRow(int row) {
		super.drawRow(row);
		System.out.print('\n');
	}

	private boolean isConsummate(int row, int col, int itemState) {

		int shiftedRow, shiftedCol;
		for (int direction = 0; direction < shift.length; direction++) {
			for (int distance = 1; distance < winSequenceLength; distance++) {
				shiftedRow = row + distance * shift[direction][0];
				shiftedCol = col + distance * shift[direction][1];
				if (isValid(shiftedRow, shiftedCol) && field[shiftedRow][shiftedCol].getState() == itemState) {
					if (distance == winSequenceLength - 1) {
						return true;
					}
				} else {
					break;
				}
			}
		}

		return false;
	}

	public int isFinished() {
		int itemState = TictacItem.STATE_EMPTY, empties = 0;
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				itemState = field[row][col].getState();
				if (itemState == TictacItem.STATE_EMPTY) {
					empties++;
				} else if (isConsummate(row, col, itemState)) {
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
