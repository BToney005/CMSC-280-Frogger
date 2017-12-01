import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Stage {
	
	public Stage( int x, int y, int width, int height, ArrayList<Obstacle> obstacles, Frog frog ){
		this.setBounds(x, y, width, height);
		this.obstacles = obstacles;
		this.sprites = new ArrayList<>();
		this.frog = frog;
	}
	
	public Stage( ArrayList<Obstacle> obstacles ){
		this( 0, 0, 640, 480, obstacles, new Frog(0,0,40,40) );
	}
	
	public void setBounds( int x, int y, int width, int height ){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void resetStage( ArrayList<Obstacle> obs){
		obstacles = new ArrayList<>(obs);
	}
	
	public void addImage( Image img ){
		sprites.add(img);
	}
	
	public void paint( Graphics g ){
		g.setColor(Color.GRAY);
		g.fillRect(x, height/2, width, height/2);
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height/2);
		g.drawImage( sprites.get(0), 0, 0, width, 60, null, null); 
		g.drawImage(sprites.get(1), 0, height/2, width, 40, null, null);
		g.drawImage(sprites.get(1), 0, height - 40, width, 40, null, null);
		/*for( Obstacle obs : obstacles ){
			obs.paint(g);
		}*/
	}
	
	public boolean checkCollision( Obstacle obs ){
		boolean horizontalCollision = false;
		boolean verticalCollision = false;
		
		if( obs.x < frog.x && frog.x < obs.x + obs.width )
			horizontalCollision = true;
		else if( obs.x < frog.x + frog.width && frog.x + frog.width < obs.x + obs.width )
			horizontalCollision = true;
		
		if( obs.y < frog.y && frog.y < obs.y + obs.height )
			verticalCollision = true;
		else if( obs.y < frog.y + frog.height && frog.y + frog.height < obs.y + obs.height )
			verticalCollision = true;
		
		if( horizontalCollision && verticalCollision ){
			frog.handleCollision(obs);
			return true;
		}else if( !horizontalCollision && !verticalCollision){ 
			if( frog.x < obs.x && obs.x < frog.x + frog.width)
				if( frog.y < obs.y && obs.y < frog.y + frog.height ){
					frog.handleCollision(obs);
					return true; // The Object is completely emplaced inside the frog
				}
		}
		//FIX COLLISIONS <http://www.owenpellegrin.com/articles/vb-net/simple-collision-detection/>
		//if( frog.y < height/2)
			//frog.kill();
		return false;
		
		
	}
	
	public boolean checkCollision( Goal goal ){
		boolean horizontalCollision = false;
		boolean verticalCollision = false;
		
		if( goal.x < frog.x && frog.x < goal.x + goal.width )
			horizontalCollision = true;
		else if( goal.x < frog.x + frog.width && frog.x + frog.width < goal.x + goal.width )
			horizontalCollision = true;
		
		if( goal.y < frog.y && frog.y < goal.y + goal.height )
			verticalCollision = true;
		else if( goal.y < frog.y + frog.height && frog.y + frog.height < goal.y + goal.height )
			verticalCollision = true;
		
		if( horizontalCollision && verticalCollision ){
			if(goal.open && !goal.hasCroc){
				goal.open = false;
				frog.resetPosition();
			}else
				frog.kill();
			return true;
		}else if( !horizontalCollision && !verticalCollision){ 
			if( frog.x < goal.x && goal.x < frog.x + frog.width)
				if( frog.y < goal.y && goal.y < frog.y + frog.height ){
					if(goal.open && !goal.hasCroc){
						goal.open = false;
						frog.resetPosition();
					}else
						frog.kill();
					return true; // The Object is completely emplaced inside the frog
				}
		}
		return false;
	}
	
	private int x, y;
	private int width, height;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Image> sprites;
	private Frog frog;

}
