package boards;

abstract public class Item {

	public final static int STATE_EMPTY = 0;

	protected int state = STATE_EMPTY;

	public void setState(int newState) {
		state = newState;
	}

	public int getState() {
		return state;
	}

	abstract public void display(int row, int col);

}
