package se.wiklund.colony.game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.colony.Assets;
import se.wiklund.colony.Main;
import se.wiklund.colony.component.Component;
import se.wiklund.colony.component.Label;

public class HUD {
	
	private List<Component> components;
	private int sidebarY = 20;
	
	private Label lblTPS, lblFPS;
	private int lastTPS, lastFPS;
	
	public HUD() {
		components = new ArrayList<Component>();
		
		lblTPS = addSidebarRow("TPS: N/A");
		lblFPS = addSidebarRow("FPS: N/A");
	}
	
	public void tick() {
		for (Component component : components) {
			component.tick();
		}
		
		if (lastTPS != Main.getTPS()) {
			lastTPS = Main.getTPS();
			lblTPS.setText("TPS: " + lastTPS);
		}
		
		if (lastFPS != Main.getFPS()) {
			lastFPS = Main.getFPS();
			lblFPS.setText("FPS: " + lastFPS);
		}
	}
	
	public void render(Graphics2D g) {
		for (Component component : components) {
			component.render(g);
		}
	}
	
	public Label addSidebarRow(String text) {
		Label label = new Label(text, Assets.FONT_SIDEBAR, 15, sidebarY);
		components.add(label);
		sidebarY += Assets.FONT_SIDEBAR.getSize();
		return label;
	}
}
