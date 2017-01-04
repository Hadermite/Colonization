package se.wiklund.colony.game;

import se.wiklund.colony.Main;
import se.wiklund.colony.game.entity.Entity;

public class Camera {
	
	private int rawX, rawY;
	private int x, y;
	private int renderOffsetX, renderOffsetY;
	private double scale = 1;
	private Entity focusEntity;
	
	public void tick(Entity entity) {
		this.focusEntity = entity;
		this.x = (int) entity.getX();
		this.y = (int) entity.getY();
		this.renderOffsetX = (Main.WIDTH / 2) - x - (entity.getWidth() / 2);
		this.renderOffsetY = (Main.HEIGHT / 2) - y - (entity.getHeight() / 2);
	}
	
	public void tick(double centerX, double centerY) {
		this.rawX = (int) centerX;
		this.rawY = (int) centerY;
		
		x = (int) (rawX * scale);
		y = (int) (rawY * scale);
		renderOffsetX = (int) (Main.WIDTH / (2 * scale) - rawX);
		renderOffsetY = (int) (Main.HEIGHT / (2 * scale) - rawY);
	}
	
	private void setScale(double scale) {
		if (scale < 0.2) scale = 0.2;
		if (scale > 8) scale = 8;
		this.x = (int) (rawX * scale);
		this.y = (int) (rawY * scale);
		this.renderOffsetX = (int) (Main.WIDTH / (2 * scale) - rawX);
		this.renderOffsetY = (int) (Main.HEIGHT / (2 * scale) - rawY);
		this.scale = scale;
	}
	
	public void zoom(int amount) {
		setScale(scale * ((double) amount / 10) + scale);
	}
	
	public double getRenderOffsetX() {
		return renderOffsetX;
	}
	
	public double getRenderOffsetY() {
		return renderOffsetY;
	}
	
	public double getScale() {
		return scale;
	}
	
	public Entity getFocusEntity() {
		return focusEntity;
	}
}
