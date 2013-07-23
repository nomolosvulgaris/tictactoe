package tictactoe;

import boards.Desk;
import boards.Messenger;
import boards.Move;
import boards.Player;

public class Manual extends Player {

	protected int state;
	Messenger messenger;

	public Manual(Desk desk, int state) {
		super(desk);
		messenger = desk.getMessenger();
		this.state = state;
	}

	public Move getMove() {
		int row = messenger.requestInteger("Enter the row") - 1;
		int col = messenger.requestInteger("Enter the column") - 1;
		return new Move(row, col, state);
	}
}
