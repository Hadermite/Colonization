package se.wiklund.colony.component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.colony.Assets;
import se.wiklund.colony.input.Mouse;
import se.wiklund.colony.input.MouseReader;
import se.wiklund.colony.listener.CheckBoxListener;

public class CheckBox extends Component implements MouseReader {
	
	protected Label label;
	protected boolean checked;
	protected List<CheckBoxListener> listeners;
	
	public CheckBox(String text, double x, double y) {
		super(x, y - 2, Assets.FONT_CHECKBOX.getSize(), Assets.FONT_CHECKBOX.getSize());
		
		label = new Label(text, Assets.FONT_CHECKBOX, x + width + 10, y);
		listeners = new ArrayList<CheckBoxListener>();
		
		Mouse.addMouseReader(this);
	}
	
	@Override
	public void tick() {
		super.tick();
	}
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.GRAY);
		g.fillRect((int) x, (int) y, width, height);
		Color color = Color.RED;
		if (checked) color = Color.GREEN;
		g.setColor(color);
		g.fillRect((int) x + 4, (int) y + 4, width - 8, height - 8);
		label.render(g);
	}
	
	public void addListener(CheckBoxListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(CheckBoxListener listener) {
		listeners.remove(listener);
	}
	
	public boolean isChecked() {
		return checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public void onMouseClick(int button, int x, int y) {
		if (button != 1) return;
		if (containsCoord(x, y)) {
			checked = !checked;
			
			for (CheckBoxListener listener : listeners) {
				listener.onChangeValue(this, checked);
			}
		}
	}
	
	@Override
	public boolean containsCoord(double x, double y) {
		double xMax = this.x + width + (label.getX() - this.x - width) + label.getWidth();
		return x >= this.x && x <= xMax && y >= this.y && y <= this.y + height;
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
