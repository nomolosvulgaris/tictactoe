package boards;

abstract public class Messenger {

	abstract public void message(String msg);
	abstract public String request(String msg);

	public int requestInteger(String msg) {
		int result = 0;
		boolean repeat;
		String probInt;
		do {
			repeat = false;
			probInt = request(msg);
			try {
				result = Integer.parseInt(probInt);
			} catch (java.lang.NumberFormatException e) {
				message("Wrong number " + probInt + ". Try again.");
				repeat = true;
			}
		} while (repeat);
		return result;
	}

	public boolean requestBoolean(String msg) {
		String responce = request(msg + " (y/n)");
		return responce.equals("y");
	}

}
