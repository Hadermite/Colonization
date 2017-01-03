package se.wiklund.colony.game.world;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GameObject {

	protected BufferedImage texture;
	protected double x, y;
	protected int width, height;
	private Rectangle bounds;
	
	public GameObject(BufferedImage texture, double x, double y, int width, int height) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle((int) x, (int) y, width, height);
	}
	
	public void tick() {
		bounds.setBounds((int) x, (int) y, width, height);
	}
	
	public void render(Graphics2D g) {
		if (texture != null)
			g.drawImage(texture, (int) x, (int) y, width, height, null);
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
	public boolean intersects(GameObject other) {
		return bounds.intersects(other.getBounds());
	}
	
	// Getters and Setters
	public BufferedImage getTexture() {
		return texture;
	}
	
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
