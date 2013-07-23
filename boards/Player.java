package boards;

abstract public class Player {

	protected Desk desk;

	public Player(Desk _d) {
		desk = _d;
	}

	abstract public Move getMove();

}
