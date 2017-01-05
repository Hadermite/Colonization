package se.wiklund.colony.menu;

import java.awt.Graphics2D;

import se.wiklund.colony.Assets;
import se.wiklund.colony.Main;
import se.wiklund.colony.State;

public class SplashScreen extends State {
	
	private int timer;
	
	@Override
	public void tick() {
		timer++;
		
		if (timer >= 2 * Main.TICKRATE) {
			Main.setState(new Menu());
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(Assets.SPLASH_SCREEN, 0, 0, Main.WIDTH, Main.HEIGHT, null);
	}
	
	@Override
	public void onMouseDown(int button, int x, int y) {
		
	}

	@Override
	public void onMouseDrag(int button, double deltaX, double deltaY) {
		
	}

	@Override
	public void onMouseUp(int button, int x, int y) {
		
	}

	@Override
	public void onMouseScoll(int amount) {
		
	}
}
