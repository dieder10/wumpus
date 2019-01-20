package tipos;

public class Character {

	public enum Heading {
		NORTH, EAST, SOUTH, WEST
	}

	private Position position;
	private Heading heading;
	private boolean hasGold = false;
	private int nArrows;

	public Character(Position startPos, int nArrows) {
		this.position = startPos;
		this.heading = Heading.NORTH;
		this.nArrows = nArrows;
	}

	public void rotate(int rotation) {
		if (rotation == -1) { // LEFT
			switch (heading) {
			case EAST:
				heading = Heading.NORTH;
				break;
			case NORTH:
				heading = Heading.WEST;
				break;
			case SOUTH:
				heading = Heading.EAST;
				break;
			case WEST:
				heading = Heading.SOUTH;
				break;
			}
		} else if (rotation == 1) { // RIGHT
			switch (heading) {
			case EAST:
				heading = Heading.SOUTH;
				break;
			case NORTH:
				heading = Heading.EAST;
				break;
			case SOUTH:
				heading = Heading.WEST;
				break;
			case WEST:
				heading = Heading.NORTH;
				break;
			}
		}
	}

	public HeadingValues getHeadingValues() {
		int x = 0;
		int y = 0;
		switch (heading) {
		case EAST:
			x = 1;
			break;
		case NORTH:
			y = -1;
			break;
		case SOUTH:
			y = 1;
			break;
		case WEST:
			x = -1;
			break;
		}
		return new HeadingValues(x, y);
	}

	public int getnArrows() {
		return nArrows;
	}

	public void setnArrows(int nArrows) {
		this.nArrows = nArrows;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Heading getHeading() {
		return heading;
	}

	public void setHeading(Heading heading) {
		this.heading = heading;
	}

	public boolean isHasGold() {
		return hasGold;
	}

	public void setHasGold(boolean hasGold) {
		this.hasGold = hasGold;
	}

}
