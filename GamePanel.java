import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	public GamePanel( int width, int height, Frog frog, ArrayList<Obstacle> obstacles, ArrayList<Goal> goals ){
		super();
		
		this.setPreferredSize(new Dimension(width, height));
		spriteSheet = null;
		try {
		    spriteSheet = ImageIO.read(new File("frogger_sprites.png"));
		} catch (IOException e) {
			System.err.println("File not found");
		}
		this.obstacles = new ArrayList<>(obstacles);
		this.goals = new ArrayList<>(goals);
		this.frog = frog;
		//Find a way to rotate image instead of using separate sprites
		frog.addImage(spriteSheet.getSubimage(82, 334, 16, 22));
		frog.addImage(spriteSheet.getSubimage(13, 370, 22, 16)); 
		frog.addImage(spriteSheet.getSubimage(13, 334, 16, 22));
		frog.addImage(spriteSheet.getSubimage(82, 370, 22, 16));
		
		this.stage = new Stage( 0, 0, width, height, new ArrayList<Obstacle>(), this.frog );
		stage.addImage(spriteSheet.getSubimage(0, 54, 398, 52)); //goals default
		stage.addImage(spriteSheet.getSubimage(0, 120, 398, 32)); //cross sections
		 
		
	}
	
	@Override
	public void paintComponent( Graphics g){
		super.paintComponent(g);
		this.stage.paint(g);
		for( Obstacle o : obstacles ){
			o.paint(g);
		}
		for( Goal goal : goals ){
			goal.paint(g);
		}
		this.frog.paint(g);
	}
	
	Frog frog;
	ArrayList<Obstacle> obstacles;
	ArrayList<Goal> goals;
	Stage stage;
	BufferedImage spriteSheet;
	//private int width, height;
}
