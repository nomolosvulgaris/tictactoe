package boards;

abstract public class Messenger {

	abstract public void message(String msg);
	abstract public String request(String msg);

	public int requestInteger(String msg) {
		String probInt = request(msg);
		return Integer.parseInt(probInt);
	}

	public boolean requestBoolean(String msg) {
		String responce = request(msg + " (y/n)");
		return responce.equals("y");
	}

}
