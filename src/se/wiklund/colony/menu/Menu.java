package se.wiklund.colony.menu;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.colony.Assets;
import se.wiklund.colony.Main;
import se.wiklund.colony.State;
import se.wiklund.colony.component.Button;
import se.wiklund.colony.component.Component;
import se.wiklund.colony.component.Label;
import se.wiklund.colony.game.Game;

public class Menu extends State {

	private List<Component> components;

	private Label lblTitle;
	private Button btnPlay, btnSettings, btnQuit;

	public Menu() {
		components = new ArrayList<Component>();
		
		int startX = 250;
		lblTitle = new Label(Main.NAME, Assets.FONT_TITLE, 0, Label.TITLE_Y);
		btnPlay = new Button("Play", 0, startX + 175 * 0);
		btnSettings = new Button("Settings", 0, startX + 175 * 1);
		btnQuit = new Button("Quit", 0, startX + 175 * 2);
		
		btnPlay.centerX();
		lblTitle.centerX();
		btnSettings.centerX();
		btnQuit.centerX();

		components.add(lblTitle);
		components.add(btnPlay);
		components.add(btnSettings);
		components.add(btnQuit);
	}

	@Override
	public void tick() {
		for (Component component : components) {
			component.tick();
		}
	}

	@Override
	public void render(Graphics2D g) {
		for (Component component : components) {
			component.render(g);
		}
	}
	
	@Override
	public void onMouseDown(int button, int x, int y) {
		
	}

	@Override
	public void onMouseDrag(int button, double deltaX, double deltaY) {
		
	}
	
	@Override
	public void onMouseUp(int button, int x, int y) {
		if (button == 1) {
			if (btnPlay.containsCoord(x, y)) {
				Main.setState(new Game());
			}
			if (btnSettings.containsCoord(x, y)) {
				Main.setState(new Settings());
			}
			if (btnQuit.containsCoord(x, y)) {
				System.exit(0);
			}
		}
	}
	
	@Override
	public void onMouseScoll(int amount) {
		
	}
}
