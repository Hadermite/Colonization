package se.wiklund.colony.game.world;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.colony.Assets;
import se.wiklund.colony.Main;
import se.wiklund.colony.game.Camera;
import se.wiklund.colony.input.Mouse;
import se.wiklund.colony.util.WorldUtils;

public class World {

	private static final int PRELOAD_CHUNKS = 10;

	private Camera camera;
	private List<Chunk> chunks;
	private Rectangle screenBounds;
	private double centerX, centerY;

	public World() {
		camera = new Camera();
		chunks = new ArrayList<Chunk>();
		screenBounds = new Rectangle();

		WorldUtils.setCamera(camera);

		for (int xPos = -PRELOAD_CHUNKS; xPos < PRELOAD_CHUNKS; xPos++) {
			for (int yPos = -PRELOAD_CHUNKS; yPos < PRELOAD_CHUNKS; yPos++) {
				chunks.add(new Chunk(xPos, yPos));
			}
		}
	}

	public void tick() {
		for (Chunk chunk : chunks) {
			chunk.tick();
		}

		camera.tick(centerX, centerY);
	}

	public void render(Graphics2D g) {
		screenBounds.setBounds((int) -camera.getRenderOffsetX(), (int) -camera.getRenderOffsetY(),
				(int) (Main.WIDTH / camera.getScale()), (int) (Main.HEIGHT / camera.getScale()));
		Graphics2D worldG = (Graphics2D) g.create();
		worldG.scale(camera.getScale(), camera.getScale());
		worldG.translate(camera.getRenderOffsetX(), camera.getRenderOffsetY());
		for (Chunk chunk : chunks) {
			if (chunk.getBounds().intersects(screenBounds.getBounds()))
				chunk.render(worldG);
		}
		Tile tile = getTileOnScreen(Mouse.getX(), Mouse.getY());
		if (tile != null) {
			worldG.drawImage(Assets.MARKER, (int) tile.getX(), (int) tile.getY(), Tile.SIZE, Tile.SIZE, null);
		}
	}

	public void onMouseDown(int button, int x, int y) {
		
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
		return null;
	}

	public Tile getTileOnScreen(double screenX, double screenY) {
		return getTile(WorldUtils.getWorldX(screenX), WorldUtils.getWorldY(screenY));
	}
}
