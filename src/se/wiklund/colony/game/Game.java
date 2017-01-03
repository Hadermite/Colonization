package se.wiklund.colony.game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import se.wiklund.colony.Main;
import se.wiklund.colony.State;
import se.wiklund.colony.game.world.World;
import se.wiklund.colony.input.Keyboard;
import se.wiklund.colony.util.blur.GaussianFilter;

public class Game extends State {
	
	private World world;
	private PauseMenu pauseMenu;
	private boolean paused, blocksMenuOpen;
	private BufferedImage pausedBackground;
	
	public Game() {
		world = new World();
		pauseMenu = new PauseMenu(this);
	}
	
	public void tick() {
		if (paused) {
			pauseMenu.tick();
		} else {
			world.tick();
		}
		
		if (Keyboard.isKeyPressed(KeyEvent.VK_B) && !paused) {
			blocksMenuOpen = !blocksMenuOpen;
		}
		
		if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)) {
			if (!paused) {
				BufferedImage screenshot = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_ARGB);
				//Graphics2D g = (Graphics2D) screenshot.getGraphics();
				
				//Render screen to screenshot
				
				GaussianFilter blur = new GaussianFilter(5);
				pausedBackground = blur.filter(screenshot, null);
			}
			
			paused = !paused;
		}
	}
	
	public void render(Graphics2D g) {
		if (paused) {
			g.drawImage(pausedBackground, 0, 0, Main.WIDTH, Main.HEIGHT, null);
			pauseMenu.render(g);
		} else {
			world.render(g);
		}
	}
	
	@Override
	public void onMouseDown(int button, int x, int y) {
		if (!paused) world.onMouseDown(button, x, y);
	}
	
	@Override
	public void onMouseDrag(int button, double deltaX, double deltaY) {
		if (!paused) world.onMouseDrag(button, deltaX, deltaY);
	}

	@Override
	public void onMouseUp(int button, int x, int y) {
		if (!paused) world.onMouseUp(button, x, y);
	}
	
	@Override
	public void onMouseClick(int button, int x, int y) {
		if (paused) {
			pauseMenu.onMouseClick(button, x, y);
		} else {
			world.onMouseClick(button, x, y);
		}
	}
	
	@Override
	public void onMouseScoll(int amount) {
		if (!paused) {
			world.onMouseScoll(amount);
		}
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}