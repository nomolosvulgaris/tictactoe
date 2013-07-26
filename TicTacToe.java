import tictactoe.Ai;
import tictactoe.Console;
import tictactoe.Manual;
import tictactoe.TictacDesk;
import tictactoe.TictacItem;
import boards.Desk;
import boards.Messenger;

public class TicTacToe {

	static final int defaultSize = 3;
	static final int playerCount = 2;

	public static void main(String[] args) {
		Messenger console = new Console();
		TictacDesk desk = new TictacDesk(defaultSize, defaultSize, console);
		desk.drawField();

		Manual[] players = new Manual[playerCount];
		players[0] = new Manual(desk, TictacItem.STATE_CROSS);
		players[1] = new Ai(desk, TictacItem.STATE_NOUGHT);

		int turn = 0, running = Desk.DESK_PROCESS;
		while (running == Desk.DESK_PROCESS) {
			console.message(String.format("Player #%d (%s) turns...",
					turn % playerCount + 1, players[turn % playerCount].playsFor()));
//			console.message("Player #" + (turn % playerCount + 1) + "("  +  players[turn % playerCount].playsFor() + ") turns...");
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