package tictactoe;

import boards.Messenger;
import boards.Move;
import boards.Player;

public class Manual extends Player {

	protected int state;
	protected Messenger messenger;
	protected TictacDesk desk;

	public String playsFor() {
		if (state == TictacItem.STATE_NOUGHT) {
			return "noughts";
		} else {
			return "crosses";
		}
	}

	public Manual(TictacDesk desk, int state) {
		super(desk);
		this.desk = desk;
		messenger = desk.getMessenger();
		this.state = state;
	}

	public Move getMove() {
		int row, col;
		boolean isEmpty;
		do {
			row = messenger.requestInteger("Enter the row") - 1;
			col = messenger.requestInteger("Enter the column") - 1;
			isEmpty = desk.isEmpty(row, col);
			if (!isEmpty) {
				messenger.message("Forbidden move");
			}
		} while (!isEmpty);
		return new Move(row, col, state);
	}
}
