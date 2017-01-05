package se.wiklund.colony;

import java.awt.Font;
import java.awt.image.BufferedImage;

import se.wiklund.colony.util.Loader;

public class Assets {
	
	//Sprites
	public static final BufferedImage PLAYER = Loader.loadImage("/textures/sprites/player.png");
	
	//Other Textures
	public static final BufferedImage SPLASH_SCREEN = Loader.loadImage("/textures/splash_screen.png");
	public static final BufferedImage GROUND = Loader.loadImage("/textures/ground.png");
	public static final BufferedImage MARKER = Loader.loadImage("/textures/marker.png");
	
	//Fonts
	public static final Font FONT_SIDEBAR = Loader.loadFont("/fonts/comfortaa.ttf", 40);
	public static final Font FONT_BUTTON = Loader.loadFont("/fonts/comfortaa.ttf", 70);
	public static final Font FONT_TITLE = Loader.loadFont("/fonts/comfortaa.ttf", 140);
	public static final Font FONT_CHECKBOX = Loader.loadFont("/fonts/comfortaa.ttf", 50);
}
