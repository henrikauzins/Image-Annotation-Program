package imageApp;

import java.awt.Color;
import java.awt.Graphics;

// implements IAnnotations interface 
public abstract class MainAnnotation implements IAnnotations {

	// declared protected variables for MainAnnotation class
	protected String text;
	protected Color color;
	protected int coordinateX;
	protected int coordinateY;
	
	public MainAnnotation(String text, Color color, int coordinateX, int coordinateY){
		
		this.text = text;
		this.color = color;
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		
	}
	
	// implemented draw and erase interface methods
	public void draw(Graphics g) {
	    g.setColor(this.color);
	    paint(g);
	  }

	  public void erase(Graphics g) {
	    g.setColor(this.color);
	    paint(g);
	  }
	  // abstract method to paint the contents of the class using the Graphics class
	  protected abstract void paint(Graphics g);
}
