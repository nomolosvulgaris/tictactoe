package tictactoe;

import boards.Move;

public class Ai extends Manual {

	private static int[][] shift = new int[][]{ {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},          { 0, 1},
            { 1, -1}, { 1, 0}, { 1, 1} };

	private int competitor;
	private int width;
	private int height;
	private int winSequenceLength;

	public Ai(TictacDesk desk, int state) {
		super(desk, state);

		width = desk.getWidth();
		height = desk.getHeight();
		winSequenceLength = desk.getWinSequenceLength();

		if (state == TictacItem.STATE_NOUGHT) {
			competitor = TictacItem.STATE_CROSS;
		} else {
			competitor = TictacItem.STATE_NOUGHT;
		}
	}

	private void inform(int row, int col) {
		messenger.message("My row is " + (row + 1) + " and my column is " + (col + 1));
	}

	public Move getMove() {
		messenger.message("Me thinking...");

		// 1. prevent instant failure
		// 2. evolve a longest line if it could eventually become consummate
		// 3. put a sign into the first empty item

		int distance = -1, distanceWithEmpties = -1,
			maxDistance = -1, maxRow = -1, maxCol = -1;
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				for (int direction = 0; direction < shift.length; direction++) {
					if (desk.isState(row, col, competitor)) {
						distance = desk.howLong(row, col, competitor, shift[direction]);
						if (distance == winSequenceLength - 1) {
							distanceWithEmpties = desk.howLongPotentially(row, col, competitor, shift[direction]);
							if (distanceWithEmpties >= winSequenceLength) {
								maxDistance = winSequenceLength - 2;
								maxRow = row + shift[direction][0] * (winSequenceLength - 1);
								maxCol = col + shift[direction][1] * (winSequenceLength - 1);
							}
						}
					} else {
						distance = desk.howLong(row, col, state, shift[direction]);
						if (distance > maxDistance) {
							distanceWithEmpties = desk.howLongPotentially(row, col, state, shift[direction]);
							if (distanceWithEmpties >= winSequenceLength) {
								maxDistance = distance;
								maxRow = row + shift[direction][0] * distance;
								maxCol = col + shift[direction][1] * distance;
							}
						}
					}
				}
			}
		}

		inform(maxRow, maxCol);
		return new Move(maxRow, maxCol, state);

	}

}
