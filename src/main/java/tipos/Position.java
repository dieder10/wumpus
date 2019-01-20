package tipos;

import java.util.LinkedHashSet;
import java.util.Set;

public class Position {

	private int x;
	private int y;
	private CharacterContent content;

	private LinkedHashSet<CharacterContent> lashList = new LinkedHashSet<>();

	public Position(int x, int y) {
		this(x, y, CharacterContent.VOID);
	}

	public Position(int x, int y, CharacterContent content) {
		this.x = x;
		this.y = y;
		this.content = content;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public CharacterContent getContent() {
		return content;
	}

	public void setContent(CharacterContent content) {
		this.content = content;
	}

	public enum CharacterContent {
		WUMPUS, HOLE, GOLD, EXIT, VOID
	}

	/**
	 * Adds a new lash to this position
	 * 
	 * @param lash Lash of the nearby positions
	 */
	public void setLash(CharacterContent lash) {
		lashList.add(lash);
	}

	public Set<CharacterContent> getLashes() {
		return lashList;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Position) {
			Position auxPos = (Position) obj;
			return this.getX() == auxPos.getX() && this.getY() == auxPos.getY();
		}
		return false;
	}

}
