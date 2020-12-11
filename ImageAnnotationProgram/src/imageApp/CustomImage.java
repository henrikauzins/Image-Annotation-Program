package imageApp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RenderedImage;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class CustomImage extends JComponent implements MouseListener{

	private static final long serialVersionUID = 2685761236291952025L;
	
	// variables declared for Custom Image class
	
	private BufferedImage image;
	private BufferedImage newImage;
	private boolean validFile;
	
	protected String annotationText;
	
	public int margin = 0;
	private JOptionPane errorMessage;
	private JFrame errorFrame = new JFrame();
	
	// Linked Hash Set collection to store annotations
	private final Collection<IAnnotations> annotations = new LinkedHashSet<IAnnotations>();
	// annotation object declared 
	private Annotation annotation;
	// boolean to check if an annotation exists
	private boolean annotationExists;
		
	public CustomImage(File imageFile)  {
		
			 try {
				 //reads in the file
	  	       this.image = ImageIO.read(imageFile);
	  	       validFile = true;
	  	        if (image == null) {
	  	        	// if loaded image is not in an image format, an error message will be shown and not load the file
	  	        	validFile = false;
	  	        	imageLoadError();
	  	        }
	  		} catch (IOException e) {
	  			validFile = false;
	  			e.printStackTrace();
	  				
	  		}	
			 //sets the preferred size of the image component to the image
			 setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
			 
			 // adds mouse listener to image component to allow for annotations to be added and removed
			 this.addMouseListener(this);
			 		 
			 }
	 
	// if the file loaded is not an image this method will run and will not display the incorrect file
	public void imageLoadError() {
		JOptionPane.showMessageDialog(errorFrame, "CANNOT LOAD FILE AS FILE IS NOT AN IMAGE!! :(", "Image Loading Error", JOptionPane.ERROR_MESSAGE);
	}
	
	
	// mouse click method to add/remove annotations
	@Override
	public void mouseClicked(MouseEvent e) {
		
		// if the left mouse button is pressed a JOPtion pane will appear to prompt the user to add an annotation
		if(SwingUtilities.isLeftMouseButton(e)) {
		annotationText = JOptionPane.showInputDialog(null, "add an annotation");
			// adds annotation to collection
			// also ensure mouse cursor is in the middle of the drawn oval to get precise x,y location
			this.addAnnotation(new Annotation(annotationText, Color.YELLOW, e.getX() - 1, e.getY() - 1));
			annotationExists = true;
		}
		// user can only right click if annotation(s) exists on the image
			else if (annotationExists == true) {
		
		// if the right mouse button is pressed a JOption pane will appear to prompt the user to add an annotation
			if(SwingUtilities.isRightMouseButton(e)) {
			
			
			int deleteAnnotation = JOptionPane.showConfirmDialog(null, "Remove Annotation?", "Remove Annotation",
	                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			
	        // if the yes button is pressed it will delete the selected annotation
			if(deleteAnnotation == JOptionPane.YES_OPTION) {
				
				
				// iterates through collection of stored annotations on the image using Iterator
				
				
				Iterator<IAnnotations> it = annotations.iterator();
				// gets the next item in the collection
				while (it.hasNext()) {

				    annotation = (Annotation) it.next();
				    
				    if(annotation.equals(annotation)) {
				    	
				    	 //removes annotation from the collection and repaints the image
					    this.removeAnnotation(annotation);
					    it.remove();
				   
				    	 annotationExists = false;
				    	 break;
				}
				}
								
			}
						
		}
				
	}
	}
	// adds annotation to collection
	public void addAnnotation(IAnnotations a) {
        this.annotations.add(a);
        repaint();
    }
	// removes annotation from collection
	public void removeAnnotation(IAnnotations a) {
        this.annotations.remove(a);
        repaint();
    }
	// paints images and annotations onto the JComponent
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g = g.create();
	
    	//painting chosen image onto the CustomImage Component
    	g.drawImage(this.image, margin, margin, this.getWidth(), this.getHeight(), this);
    
    	// paints annotation over the loaded image after the user has typed in the text through the JOptionPane
    	
    	for (IAnnotations a : this.annotations) {
			a.draw(g);
			g.drawString(annotationText, getX(), getY());
		}	
    	// repaints the image 
    	repaint();
	}
	
	// method that saves the annotated image as a new image file
	public void saveImage(File newImageFile) {
		// sets height and width of new image to the size of the JComponent
		int h = this.getHeight();
		int w = this.getWidth();
		
		//initialises new empty buffered image 
		newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		
		// Graphics class to draw on the image and annotations
		Graphics g2 = newImage.getGraphics();
	// draws the image and annotations onto newImage
			
			g2.drawImage(this.image, margin, margin, this.getWidth(), this.getHeight(), this);
    
			for (IAnnotations a : this.annotations) {
				a.draw(g2);
				g2.drawString(this.annotationText, getX(), getY());
			}
	  
			g2.dispose();
			
			
		try {
				// saves the image as a png file
	  	       ImageIO.write(newImage, "png", newImageFile);
	  	         
	  		} catch (IOException e) {
	  			e.printStackTrace();
	  			System.out.println("error in saving image");
	  		}
	}
	
		
// these mouseclick methods do not have given functionality and are not required for the program
	@Override
	public void mousePressed(MouseEvent e) {
		//TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}



