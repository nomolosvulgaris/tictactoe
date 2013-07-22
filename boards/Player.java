package boards;

abstract public class Player {

	private Desk desk;

	public Player(Desk _d) {
		desk = _d;
	}

	abstract public Move getMove();

}
