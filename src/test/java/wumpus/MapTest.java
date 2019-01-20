package wumpus;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tipos.Mapa;
import tipos.Position;
import tipos.Position.CharacterContent;

public class MapTest {
	Mapa map;

	@Before
	public void setUp() throws Exception {
		map = new Mapa(5, 1);
		map.getArray()[1][2] = new Position(2, 1, CharacterContent.WUMPUS);

		map.getArray()[2][3] = new Position(2, 1, CharacterContent.HOLE);
		map.getArray()[3][4] = new Position(2, 1, CharacterContent.WUMPUS);
		map.getArray()[3][2] = new Position(2, 1, CharacterContent.VOID);
		map.getArray()[4][3] = new Position(2, 1, CharacterContent.GOLD);
	}

	@Test
	public void testGetPositionAt() {
		Position pos = map.getPositionAt(2, 1);
		Position auxPos = new Position(2, 1, CharacterContent.WUMPUS);
		Assert.assertEquals("Should be in [2,1]", auxPos, pos);
	}

	@Test
	public void testGetNearPosition() {
		HashSet<CharacterContent> list = map.getNearPosition(new Position(3, 3));
		boolean containHole = list.contains(CharacterContent.HOLE);
		boolean containWumpus = list.contains(CharacterContent.WUMPUS);
		boolean containGold = list.contains(CharacterContent.GOLD);
		Assert.assertTrue("Should contain all messages", containHole && containGold && containWumpus);
	}

}
