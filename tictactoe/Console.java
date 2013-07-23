package tictactoe;

import java.util.Scanner;

public class Console extends boards.Messenger {

	public void message(String msg) {
		System.out.println(msg);
	}

	public String request(String msg) {
		Scanner input = new Scanner(System.in);
		System.out.print(msg + "? ");
		return input.nextLine();
	}

}
