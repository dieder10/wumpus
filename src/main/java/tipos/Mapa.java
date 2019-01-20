package tipos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import tipos.Position.CharacterContent;

public class Mapa {

	private int width;
	private int height;
	private int nHoles;
	private Position[][] mapArray;
	private Position exitPos;
	private Position gold;
	private Position wumpus;
	private ArrayList<Position> holeList;

	/**
	 * 
	 * @param size
	 */
	public Mapa(int size, int nHoles) {
		this.width = size;
		this.height = size;
		this.nHoles = nHoles;
		this.mapArray = new Position[height][width];
		holeList = new ArrayList<>();
		generateNewMap();
	}

	/**
	 * Initializes a new map with the initial parameters
	 */
	private void generateNewMap() {
		exitPos = getRandomPosition();
		exitPos.setContent(CharacterContent.EXIT);
		mapArray[exitPos.getY()][exitPos.getX()] = exitPos;
		gold = getRandomPosition();
		gold.setContent(CharacterContent.GOLD);
		mapArray[gold.getY()][gold.getX()] = gold;
		wumpus = getRandomPosition();
		wumpus.setContent(CharacterContent.WUMPUS);
		mapArray[wumpus.getY()][wumpus.getX()] = wumpus;
		for (int i = 0; i < nHoles; i++) {
			Position auxPos = getRandomPosition();
			auxPos.setContent(CharacterContent.HOLE);
			mapArray[auxPos.getY()][auxPos.getX()] = auxPos;
			holeList.add(auxPos);
		}
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (mapArray[i][j] == null) {
					mapArray[i][j] = new Position(j, i);
				}

			}
		}
	}

	/**
	 * Generates a new Position
	 * 
	 * @return Not duplicated position
	 */
	private Position getRandomPosition() {
		int x = (int) (Math.random() * (width));
		int y = (int) (Math.random() * (height));
		if (mapArray[y][x] == null) {
			return new Position(x, y);
		} else {
			return getRandomPosition();
		}
	}

	public Position getPositionAt(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			return mapArray[y][x];
		} else {
			return null;
		}
	}

	public HashSet<CharacterContent> getNearPosition(Position pos) {
		HashSet<CharacterContent> auxList = new HashSet<>();
		Position auxPos = getPositionAt(pos.getX(), pos.getY() + 1);
		if (auxPos != null)
			auxList.add(auxPos.getContent());
		auxPos = getPositionAt(pos.getX(), pos.getY() - 1);
		if (auxPos != null)
			auxList.add(auxPos.getContent());
		auxPos = getPositionAt(pos.getX() + 1, pos.getY());
		if (auxPos != null)
			auxList.add(auxPos.getContent());
		auxPos = getPositionAt(pos.getX() - 1, pos.getY());
		if (auxPos != null)
			auxList.add(auxPos.getContent());

		// If we showed it in order without adding it to the list, it would give some
		// clues to know the order of perceptions
		for (CharacterContent content : auxList) {
			lashAlert(content);
		}
		return auxList;
	}

	public void lashAlert(CharacterContent content) {
		switch (content) {
		case GOLD:
			System.out.println("You are seeing golden flashes!");
			break;
		case HOLE:
			System.out.println("You feel a light breeze on your face");
			break;
		case WUMPUS:
			System.out.println("UGH! Here it smells horrible!");
			break;
		}
	}

	public Position[][] getArray() {
		return mapArray;
	}

	public Position getExitPos() {
		return exitPos;
	}

	public Position getGoldPosition() {
		return gold;
	}
}
