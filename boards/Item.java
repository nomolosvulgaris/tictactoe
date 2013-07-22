package boards;

abstract public class Item {

	public final static int STATE_EMPTY = 0;

	private int state = 0;

	public void setState(int newState) {
		state = newState;
	}

	public int getState() {
		return state;
	}

	abstract public void display(int x, int y);

}
