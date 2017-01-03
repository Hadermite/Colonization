package se.wiklund.colony.game.world;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.colony.game.Camera;

public class World {

	private Camera camera;
	private List<Chunk> chunks;
	private double centerX, centerY;

	public World() {
		camera = new Camera();
		chunks = new ArrayList<Chunk>();

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
		g.translate(-camera.getRenderOffsetX(), -camera.getRenderOffsetY());
	}

	public void onMouseDown(int button, int x, int y) {
		// Change x and y to match scale!
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
}
