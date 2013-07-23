import tictactoe.Console;
import tictactoe.Manual;
import tictactoe.TictacDesk;
import tictactoe.TictacItem;
import boards.Desk;
import boards.Messenger;
import boards.Player;

public class TicTacToe {

	static final int defaultSize = 3;
	static final int playerCount = 2;

	public static void main(String[] args) {
		Messenger console = new Console();
		Desk desk = new TictacDesk(defaultSize, defaultSize, console);
		desk.drawField();

		Player[] players = new Player[playerCount];
		players[0] = new Manual(desk, TictacItem.STATE_CROSS);
		players[1] = new Manual(desk, TictacItem.STATE_NOUGHT);

		int turn = 0, running = Desk.DESK_PROCESS;
		while (running == Desk.DESK_PROCESS) {
			console.message("Player #" + (turn % playerCount + 1) + " turns...");
			desk.move(players[turn % playerCount].getMove());
			desk.drawField();
			running = desk.isFinished();
			turn++;
		}

		switch (running) {
			case TictacDesk.DESK_CROSSES:
				console.message("Crosses win!");
				break;
			case TictacDesk.DESK_NOUGHTS:
				console.message("Noughts win!");
				break;
			default:
				console.message("Fighting draw...");
		}
	}

}