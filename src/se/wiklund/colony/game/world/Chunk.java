package se.wiklund.colony.game.world;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Chunk {
	
	public static final int SIZE = 16;
	
	private double x, y;
	private Rectangle bounds;
	private Tile[] tiles;
	
	public Chunk(int xPos, int yPos) {
		this.x = xPos * SIZE * Tile.SIZE;
		this.y = yPos * SIZE * Tile.SIZE;
		bounds = new Rectangle((int) x, (int) y, SIZE * Tile.SIZE, SIZE * Tile.SIZE);
		tiles = new Tile[SIZE * SIZE];
		
		populateTiles();
	}
	
	private void populateTiles() {
		for (int xPos = 0; xPos < SIZE; xPos++) {
			for (int yPos = 0; yPos < SIZE; yPos++) {
				tiles[xPos + yPos * SIZE] = new Tile((int) (x / Tile.SIZE) + xPos, (int) (y / Tile.SIZE) + yPos);
			}
		}
	}
	
	public void tick() {
		for (Tile tile : tiles) {
			tile.tick();
		}
	}
	
	public void render(Graphics2D g) {
		for (Tile tile : tiles) {
			tile.render(g);
		}
	}
	
	public Tile getTile(int xPos, int yPos) {
		return tiles[xPos + yPos * SIZE];
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public boolean containsCoord(double x, double y) {
		return x > this.x && x < this.x + SIZE * Tile.SIZE && y > this.y && y < this.y + SIZE * Tile.SIZE;
	}
}
