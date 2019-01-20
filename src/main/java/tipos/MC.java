package tipos;

import javax.sound.midi.Soundbank;

import ihm.InputOutputConsole;
import tipos.Position.CharacterContent;

public class MC implements Runnable {

	int counter = 0;
	Mapa mapa;
	Character character;
	boolean alive = true;
	boolean win = false;

	/**
	 * Master of ceremonies
	 */
	public MC() {
		InitialOrder initialOrder = InputOutputConsole.INSTANCE.askParameters();
		while (!itsPossible(initialOrder.getSize(), initialOrder.getnHoles())) {
			initialOrder = InputOutputConsole.INSTANCE.askParameters();
		}
		mapa = new Mapa(initialOrder.getSize(), initialOrder.getnHoles());
		character = new Character(mapa.getExitPos(), initialOrder.getnArrows());
		mapa.getNearPosition(character.getPosition());
	}

	@Override
	public void run() {
		while (alive && !win) {
			System.out.println("Estamos en " + character.getPosition().getX() + " " + character.getPosition().getY());
			Order newOrder = InputOutputConsole.INSTANCE.getOrder();
			if (newOrder.isExit()) {
				if (character.getPosition().equals(mapa.getExitPos())) {
					if (character.isHasGold())
						win = true;
					else
						System.out.println("You don't have the gold ingot!!");

				} else {
					System.out.println("You are not in the exit");
				}
			}

			if (newOrder.isMove()) {
				alive = walk();
			}

			if (newOrder.isShoot()) {

				shoot();
			}

			character.rotate(newOrder.getRotate());

		}
		if (!alive) {
			System.out.println("You have DIED!");
		} else if (win) {
			System.out.println("Congratulations! You have won!");
		}
	}

	private boolean itsPossible(int size, int nHoles) {
		int nTiles = size * size;
		nTiles -= 2 + nHoles; // Wumpus - Gold - Holes
		return nTiles >= 0;
	}

	public boolean walk() {
		HeadingValues headingValues = character.getHeadingValues();
		System.out.println("orientacion " + character.getHeading());
		Position auxPos = mapa.getPositionAt(character.getPosition().getX() + headingValues.getX(),
				character.getPosition().getY() + headingValues.getY());
		if (auxPos == null) {
			System.out.println("THUMP!!!! You hit a wall!");
			return true;
		}
		switch (auxPos.getContent()) {
		case GOLD:
			System.out.println("You have found the gold!");
			character.setHasGold(true);
			mapa.getGoldPosition().setContent(CharacterContent.VOID);
			break;
		case HOLE:
			System.out.println("You just fell through a hole!");
			return false;
		case WUMPUS:
			System.out.println("You have gone straight to a hungry Wumpus");
			return false;
		default:
			System.out.println("No has encontrado nada");
		}
		character.setPosition(auxPos);
		mapa.getNearPosition(auxPos);
		return true;
	}

	private void shoot() {
		int arrows = character.getnArrows();
		if (arrows > 0) {
			System.out.println("You have shot an arrow");
			arrows--;
			character.setnArrows(arrows);
			HeadingValues headingValues = character.getHeadingValues();
			int count = 1;
			boolean injured = false;
			Position auxPos = mapa.getPositionAt(character.getPosition().getX() + headingValues.getX() * count,
					character.getPosition().getY() + headingValues.getY() * count);
			if (auxPos != null)
				injured = auxPos.getContent() == CharacterContent.WUMPUS;
			while (auxPos != null && !injured) {
				count++;
				auxPos = mapa.getPositionAt(character.getPosition().getX() + headingValues.getX() * count,
						character.getPosition().getY() + headingValues.getY() * count);
				if (auxPos != null)
					injured = auxPos.getContent() == CharacterContent.WUMPUS;
			}
			String auxString = "Now you have " + character.getnArrows();
			if (character.getnArrows() > 1)
				auxString += " arrows";
			else
				auxString += " arrow";
			System.out.println(auxString);

			if (injured) {
				System.out.println("AAAAAGH! \nYou have killed the Wumpus");
				auxPos.setContent(CharacterContent.VOID);
			}

		} else {
			System.out.println("You are out of arrows");
		}

	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new MC());
		t1.start();
	}

	public enum deathCause {
		WUMPUS, HOLE
	}

}
