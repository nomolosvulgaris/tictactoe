package boards;

abstract public class Desk {

	public final static int DESK_PROCESS = 0;

	protected final int width;
	protected final int height;
	protected Item[][] field;
	protected Messenger messenger;

	public Desk(int width, int height, Messenger messenger) {
		this.width = width;
		this.height = height;
		this.messenger = messenger;
		field = new Item[height][width];
	}

	public Messenger getMessenger() {
		return messenger;
	}

	public void drawRow(int row) {
		for (int col = 0; col < width; col++) {
			field[row][col].display(row, col);
		}
	}

	public void drawField() {
		for (int row = 0; row < height; row++) {
			drawRow(row);
		}
	}

	public boolean isValid(int row, int col) {
		return (row >= 0 && row < height) && (col >= 0 && col < width);
	}

	public void move(Move move) {
		field[move.row][move.col].setState(move.state);
	}

	abstract public int isFinished();

}