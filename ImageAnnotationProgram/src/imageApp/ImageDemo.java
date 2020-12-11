package imageApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageDemo extends JFrame {
	/**
	 * 
	 */
	//declaration of variables to be used for the program
	private static final long serialVersionUID = 7040254842291292255L;
	public JFrame frame = new JFrame();
	
	private JFileChooser fc = new JFileChooser();
	// CustomImage object declared to pass through parameters through choosing image files
	public CustomImage myCustomImage;
	
	
	public ImageDemo() {
	
		   setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		   
		   // sets up the frame with a title and preferred size
	        this.frame = new JFrame("Image Annotation Program");
			this.frame.setPreferredSize(new Dimension(630, 440));
			// two JPanels that will be used to house buttons and custom image components
	        JPanel buttonPanel = new JPanel();
	    	JPanel imagePanel = new JPanel();
	    	// buttons declared and initialised used for opening and saving image files
	    	JButton openImageButton = new JButton("Open");
	    	JButton saveImageButton = new JButton("Save");
	    	
	    	// sets preferred size of open image button
			openImageButton.setPreferredSize(new Dimension(310, 25));
			
			// action listener is added to openImage button to allow JFileChooser functionality
			openImageButton.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e){
					
			        if(e.getSource() == openImageButton){
			        	
			        	FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif", "png", "jfif");
			        	// sets a file filter to quickly access images
			        	fc.setFileFilter(filter);
			        	// shows dialog box
			            int returnVal = fc.showOpenDialog(ImageDemo.this);
			            //once selected file has been selected, this will run
			            if(returnVal == JFileChooser.APPROVE_OPTION){
			            	File selectedFile = fc.getSelectedFile();
			            	// initialises the CustomImage object and passes the file through as a parameter to the CustomImage class constructor 
							myCustomImage = new CustomImage(selectedFile);
							//adds the selected image file to the imagePanel taking the CustomImage object as a parameter
			            	imagePanel.add(myCustomImage);
			            	//revalidates the frame
			                frame.revalidate();    
			                
			            } 
			        }
			        	        
			    }
			});
			// sets preferred size of save image button
saveImageButton.setPreferredSize(new Dimension(310, 25));
			
//action listener is added to openImage button to allow JFileChooser functionality
saveImageButton.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e){
					
			        if(e.getSource() == saveImageButton){
			        	// file extension filter set up
			        	FileNameExtensionFilter filter = new FileNameExtensionFilter(".png", "png");
			        	fc.setFileFilter(filter);
			        	// file to be saved in png format
			        	File saveSelectedImage = new File("savedAnnotatedImage.png");
			        	//file chooser selects the file to be saved
				        fc.setSelectedFile(saveSelectedImage);
				        // shows the dialog box
			            int returnVal = fc.showSaveDialog(ImageDemo.this);
			            // if the user approves the file saving
			            if(returnVal == JFileChooser.APPROVE_OPTION){
			            	// gets selected file
			            	saveSelectedImage = fc.getSelectedFile();
			            	//passes the file through as a parameter to the saveImage() method in the CustomImage class
			            	myCustomImage.saveImage(saveSelectedImage);
			            	
			            	// revalidates the frame
			               frame.revalidate();    
			                
			            }	
			         	
			        }
			    }
});
			
			// sets up Box Layout for button panel
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
			// adds buttons to the image panel
			buttonPanel.add(openImageButton);
			buttonPanel.add(Box.createHorizontalGlue());
			buttonPanel.add(saveImageButton);
			
			
			// sets up BorderLayout Layout to place both image and button panel
			imagePanel.setLayout(new BorderLayout());
			
			
	        frame.add(buttonPanel, BorderLayout.SOUTH);
	        
	       
	        frame.add(imagePanel, BorderLayout.CENTER);
	      // packs the frame and sets the frame to be visible
	        frame.pack();
	        frame.setVisible(true);
	}
	
		// runs the code in the constructor 
	public static void launch() {
		//runs the constructor method 
	new ImageDemo();
	
		
	}
	// launches the program
	   public static void main(String[] args) {
	        SwingUtilities.invokeLater( () -> launch());
	    }
	}



	

	

