package se.wiklund.colony.game.world;

import se.wiklund.colony.Assets;

public class Tile extends GameObject {
	
	public static final int SIZE = 64;
	
	public Tile(int xPos, int yPos) {
		super(Assets.GROUND, xPos * SIZE, yPos * SIZE, SIZE, SIZE);
	}
}
