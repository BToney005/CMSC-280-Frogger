import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Frog {
	public Frog( int x, int y, int width, int height ){
		this.x = x;
		this.y = y;
		def_x = this.x;
		def_y = this.y;
		this.width = width;
		this.height = height;
		hop = 40;
		dir = 1;
		paused = false;
		img = null;
		sprites = new ArrayList<Image>();
		lives = 3;
		overWater = false;
	}
	
	public void move( int dir ){
		if(!paused){
			this.dir = dir;
			switch(this.dir){
				case 0: if((!overWater && x+width-hop > 0) || overWater) x-=hop; break;
				case 1: y-=hop; break;
				case 2: if((!overWater && x+hop < 640) || overWater) x+=hop; break;
				case 3: if(y+hop < 640) y+=hop; break;
			}
		}
	}
	
	public void handleCollision( Obstacle obs ){
		if( !obs.isPlatform )
			this.kill();
		else{
			if( obs instanceof WaterObstacle )
				if( ((WaterObstacle)obs).submerged )
					this.kill();
			this.x+=obs.velocity;
		}
	}
	
	public void resetPosition(){
		x = def_x;
		y = def_y;
		dir = 1;
	}
	
	public void kill(){
		lives--;
		resetPosition();
	}
	
	public void setOverWater(){
		if( y < 320 )
			overWater = true;
		else 
			overWater = false;
	}
	
	void addImage( Image img ){
		sprites.add(img);
	}
	
	void setImage( Image img ){
		this.img = img;
	}
	
	public boolean isPaused(){
		return paused;
	}
	
	public void pause(){
		paused = true;
	}
	
	public void unpause(){
		paused = false;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public void paint( Graphics g ){
		switch(dir){
			case 0: setImage(sprites.get(0)); break;
			case 1: setImage(sprites.get(1)); break;
			case 2: setImage(sprites.get(2)); break;
			case 3: setImage(sprites.get(3)); break;
			//default: setImage(sprites.get(0)); break;
		}
		//g.setColor(Color.GREEN);
		g.drawImage( img, x, y, width, height, null, null);
		//g.fillRect(x, y, width, height);
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	int x, y;
	private int def_x, def_y;
	final int width, height; 
	private int hop, dir;
	private boolean paused;
	private Image img;
	private ArrayList<Image> sprites;
	boolean overWater;
	
	int lives;

}
