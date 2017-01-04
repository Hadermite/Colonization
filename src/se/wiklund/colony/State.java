package se.wiklund.colony;

import java.awt.Graphics2D;

import se.wiklund.colony.input.MouseReader;

public abstract class State implements MouseReader {
	
	public abstract void tick();
	public abstract void render(Graphics2D g);
}
