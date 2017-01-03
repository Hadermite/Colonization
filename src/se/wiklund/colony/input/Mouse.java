package se.wiklund.colony.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import se.wiklund.colony.Main;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

	private static double x, y;
	private static List<MouseReader> readers = new CopyOnWriteArrayList<MouseReader>();
	int pressedButton = 0;
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		for (MouseReader reader : readers) {
			reader.onMouseScoll(e.getWheelRotation());
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		double lastX = x;
		double lastY = y;
		
		x = e.getX() / Main.scale;
		y = e.getY() / Main.scale;
		
		for (MouseReader reader : readers) {
			reader.onMouseDrag(pressedButton, lastX - x, lastY - y);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX() / Main.scale;
		y = e.getY() / Main.scale;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (MouseReader reader : readers) {
			reader.onMouseClick(e.getButton(), (int) x, (int) y);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		pressedButton = e.getButton();
		for (MouseReader reader : readers) {
			reader.onMouseDown(e.getButton(), (int) x, (int) y);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (MouseReader reader : readers) {
			reader.onMouseUp(e.getButton(), (int) x, (int) y);
		}
	}

	public static double getX() {
		return x;
	}

	public static double getY() {
		return y;
	}
	
	public static void addMouseReader(MouseReader reader) {
		readers.add(reader);
	}
	
	public static void removeMouseReader(MouseReader reader) {
		readers.remove(reader);
	}
}
