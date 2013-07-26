package boards;

abstract public class Player {

	protected Desk desk;

	public Player(Desk desk) {
		this.desk = desk;
	}

	abstract public Move getMove();

}
