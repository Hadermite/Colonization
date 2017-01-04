package se.wiklund.colony.game.world;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.colony.Assets;
import se.wiklund.colony.game.Camera;
import se.wiklund.colony.input.Mouse;
import se.wiklund.colony.util.WorldUtils;

public class World {

	private Camera camera;
	private List<Chunk> chunks;
	private double centerX, centerY;

	public World() {
		camera = new Camera();
		chunks = new ArrayList<Chunk>();

		WorldUtils.setCamera(camera);
		
		chunks.add(new Chunk(0, 0));
	}

	public void tick() {
		for (Chunk chunk : chunks) {
			chunk.tick();
		}

		camera.tick(centerX, centerY);
	}

	public void render(Graphics2D g) {
		g.scale(camera.getScale(), camera.getScale());
		g.translate(camera.getRenderOffsetX(), camera.getRenderOffsetY());
		for (Chunk chunk : chunks) {
			chunk.render(g);
		}
		
		Tile tile = getTileOnScreen(Mouse.getX(), Mouse.getY());
		if (tile != null) {
			g.drawImage(Assets.MARKER, (int) tile.getX(), (int) tile.getY(), Tile.SIZE, Tile.SIZE, null);
		}
		g.translate(-camera.getRenderOffsetX(), -camera.getRenderOffsetY());
	}
	
	public void onMouseDown(int button, int x, int y) {
		double worldX = WorldUtils.getWorldX(x);
		double worldY = WorldUtils.getWorldY(y);
		
		if (button == 1) {
			System.out.println(getTile(worldX, worldY).getBounds());
		}
	}

	public void onMouseDrag(int button, double deltaX, double deltaY) {
		deltaX /= camera.getScale();
		deltaY /= camera.getScale();
		if (button == 3) {
			centerX += deltaX;
			centerY += deltaY;
		}
	}

	public void onMouseUp(int button, int x, int y) {
		// Change x and y to match scale!
	}

	public void onMouseClick(int button, int x, int y) {
		// Change x and y to match scale!
	}

	public void onMouseScoll(int amount) {
		camera.zoom(-amount);
	}
	
	public Tile getTile(double x, double y) {
		for (Chunk chunk : chunks) {
			if (chunk.containsCoord(x, y)) {
				return chunk.getTile((int) (x - chunk.getX()) / Tile.SIZE, (int) (y - chunk.getY()) / Tile.SIZE);
			}
		}
		System.err.println("That tile does not belong to any chunk!");
		return null;
	}
	
	public Tile getTileOnScreen(double screenX, double screenY) {
		return getTile(WorldUtils.getWorldX(screenX), WorldUtils.getWorldY(screenY));
	}
}
