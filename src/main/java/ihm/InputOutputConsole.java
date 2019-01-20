package ihm;

import java.util.Scanner;

import tipos.InitialOrder;
import tipos.Order;

public class InputOutputConsole {

	public static final InputOutputConsole INSTANCE = new InputOutputConsole();

	private Scanner sc;

	public InputOutputConsole() {
		sc = new Scanner(System.in);
	}

	/**
	 * Ask for a new order
	 * 
	 * @return Order
	 */
	public Order getOrder() {
		System.out.println("What do you want to do next?");
		System.out.println("Rotate 'rotate left|right' ");
		System.out.println("Move on 'move'");
		System.out.println("Shoot yout weapon 'shoot'");
		System.out.println("Exit 'exit'");

		String line = sc.nextLine();
		String[] words = line.split(" ");
		Order newOrder;
		switch (words[0].toLowerCase()) {
		case "rotate":
			if (words.length > 1) {
				if (words[1].equalsIgnoreCase("left")) {
					newOrder = new Order(-1, false, false, false);
				} else if (words[1].equalsIgnoreCase("right")) {
					newOrder = new Order(1, false, false, false);
				} else {
					System.out.println("Invalid instruction. Valid rotation: 'left' or 'right'");
					return getOrder();
				}
				return newOrder;
			}
			return getOrder();
		case "move":
			newOrder = new Order(0, false, true, false);
			break;
		case "shoot":
			newOrder = new Order(0, true, false, false);
			break;
		case "exit":
			newOrder = new Order(0, false, false, true);
			break;
		default:
			return getOrder();
		}
		return newOrder;
	}

	/**
	 * Ask for the initial parameters
	 * 
	 * @return Initial parameters
	 */
	public InitialOrder askParameters() {
		System.out.println(
				"Enter the size of the map, the number of holes and the number of the available arrows as follows. Example 5 4 2");
		String line = sc.nextLine();
		String[] parameters = line.split(" ");
		int[] parametersInt = new int[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			parametersInt[i] = Integer.parseInt(parameters[i]);
		}
		if (parametersInt.length == 3)
			return new InitialOrder(parametersInt[0], parametersInt[1], parametersInt[2]);
		else {
			System.out.println("Incorrect number of parameters.");
			return askParameters();
		}
	}
}
