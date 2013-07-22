package boards;

abstract public class Desk {

	public final static int DESK_PROCESS = 0;

	private final int width;
	private final int height;
	private Item[][] field;

	public Desk(int _w, int _h) {
		width = _w;
		height = _h;
		field = new Item[height][width];
	}

	public void drawLine(int n) {
		for (int i = 0; i < width; i++) {
			field[n][i].display(i, n);
		}
	}

	public void drawField() {
		for (int i = 0; i < height; i++) {
			drawLine(i);
		}
	}

	abstract public int isFinished();
	abstract public void move(Move m);

}