import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Obstacle {
	int x;
	final int y;
	int width;
	final int height;
	int velocity;
	boolean isPlatform;
	protected Image img;
	
	public Obstacle( int x, int y, int width, int height, int velocity, boolean isPlatform){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velocity = velocity;
		this.isPlatform = isPlatform;
		img = null;
	}
	
	public Obstacle( int x, int y, int width, int height ){
		this(x, y, width, height, width, false);
	}
	
	public void tick(){
		this.x+=velocity;
		if( velocity < 0 && x + width <= 0 )
			this.x = 640;
		else if( velocity > 0 && x >= 640 )
			this.x = -1 * width;
	}
	
	public void paint( Graphics g ){
		if( this.isPlatform)
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}

}
