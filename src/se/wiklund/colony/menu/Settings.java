package se.wiklund.colony.menu;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.colony.Assets;
import se.wiklund.colony.Main;
import se.wiklund.colony.State;
import se.wiklund.colony.component.Button;
import se.wiklund.colony.component.CheckBox;
import se.wiklund.colony.component.Component;
import se.wiklund.colony.component.Label;
import se.wiklund.colony.listener.CheckBoxListener;

public class Settings extends State {
	
	public static boolean antiAliasing = true, fullscreenChangeQueued, noSplash;
	
	private List<Component> components;
	
	private Label lblTitle;
	private CheckBox chkFullscreen, chkAA;
	private Button btnBack;
	
	public Settings() {
		components = new ArrayList<Component>();
		
		lblTitle = new Label("Settings", Assets.FONT_TITLE, 0, Label.TITLE_Y);
		chkFullscreen = new CheckBox("Fullscreen", 500, 250);
		chkAA = new CheckBox("Anti-Aliasing", 500, 250 + 30 + Assets.FONT_CHECKBOX.getSize());
		btnBack = new Button("Back", 0, Button.BTN_BACK_Y);
		
		lblTitle.centerX();
		btnBack.centerX();
		
		chkFullscreen.setChecked(Main.getWindow().isFullscreen());
		chkAA.setChecked(antiAliasing);

		chkFullscreen.addListener(new SettingsListener());
		chkAA.addListener(new SettingsListener());
		
		components.add(lblTitle);
		components.add(chkFullscreen);
		components.add(chkAA);
		components.add(btnBack);
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
	public void onMouseClick(int button, int x, int y) {
		if (button == 1) {
			if (btnBack.containsCoord(x, y)) {
				Main.setState(new Menu());
			}
		}
	}
	
	class SettingsListener implements CheckBoxListener {

		@Override
		public void onChangeValue(CheckBox box, boolean checked) {
			if (box == chkFullscreen) {
				fullscreenChangeQueued = true;
			}
			if (box == chkAA) {
				antiAliasing = checked;
			}
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
		
	}
	
	@Override
	public void onMouseScoll(int amount) {
		
	}
}
