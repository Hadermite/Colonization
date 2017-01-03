package se.wiklund.colony.input;

public interface MouseReader {
	
	public void onMouseDown(int button, int x, int y);
	public void onMouseDrag(int button, double deltaX, double deltaY);
	public void onMouseUp(int button, int x, int y);
	public void onMouseClick(int button, int x, int y);
	public void onMouseScoll(int amount);
}
