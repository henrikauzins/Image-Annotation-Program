package imageApp;

import java.awt.Graphics;
// declares two graphics methods to be utilised on MainAnnotation class
public interface IAnnotations {
	void draw(Graphics g);
    void erase(Graphics g);
}
