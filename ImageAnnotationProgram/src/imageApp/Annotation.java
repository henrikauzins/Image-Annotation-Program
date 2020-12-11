package imageApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

// extends MainAnnotation class
public class Annotation extends MainAnnotation {

	public Annotation(String text, Color color, int coordinateX, int coordinateY) {
		//uses super() to get the variables from the super class
		super(text, color, coordinateX, coordinateY);
	}
	// gets and returns text of annotation
	public String getText() {
		return text;
	}
	// gets and returns colour of annotation
	public Color getColor() {
		return color;
	}
	// gets and returns integer value of Coordinate X
	public int getCoordinateX() {
		return coordinateX;
	}
	// gets and returns integer value of Coordinate Y
	public int getCoordinateY() {
		return coordinateY;
	}
	// overrides the public abstract paint() method in the MainAnnotation class
	@Override
	protected void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.create();
		// draws the colour, circle and annotation string
		g.setColor(color);
		g.drawOval(getCoordinateX(), getCoordinateY(), 5, 5);
		g.drawString(getText(), getCoordinateX(), getCoordinateY());	
	}
}
