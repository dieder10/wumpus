package tipos;

public class Order {

	/**
	 * Indicates if the player changes his angle
	 * -1: 90º to left
	 * 0: Stay
	 * 1: 90º to right
	 */
	private int rotate;
	private boolean shoot;
	private boolean move;
	private boolean exit;
	
	/**
	 * New order
	 * @param rotate Indicates if the player has to rotate
	 * @param shoot If the player shoots
	 * @param move If the player move on
	 * @param exit If the player exits
	 */
	public Order(int rotate, boolean shoot, boolean move, boolean exit) {
		this.rotate = rotate;
		this.shoot = shoot;
		this.move = move;
		this.exit = exit;
	}

	public int getRotate() {
		return rotate;
	}

	public void setRotate(int rotate) {
		this.rotate = rotate;
	}

	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	public boolean isMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}
	
	
	
}
