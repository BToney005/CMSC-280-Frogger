import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Goal {
	int x, y;
	int width, height;
	int count;
	boolean open, hasCroc;
	Random r;
	ArrayList<Image> sprites;
	
	public Goal( int x, int y, int width, int height ){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.count = 0;
		r = new Random();
		open = true;
		hasCroc = false;
		sprites = new ArrayList<>();
		try{
			this.sprites.add(ImageIO.read(new File("frogger_sprites.png")).getSubimage(82, 370, 22, 16));
			this.sprites.add(ImageIO.read(new File("frogger_sprites.png")).getSubimage(206, 332, 38, 29));
		}catch( Exception e){
			System.err.println("Failed to load sprite: Goal");
			System.exit(0);
		}
	}
	
	public Goal( int x, int y ){
		this(x, y, 47, 35);
	}
	
	public void reset(){
		hasCroc = false;
		open = true;
		count = 0;
		
	}
	
	public void tick(){
		if( !this.hasCroc && this.open ){
			if(r.nextInt(1000) == 1)
				hasCroc = true;
		}else if( this.hasCroc ){
			if(count == (4 * 60)){
				hasCroc = false;
				count = 0;
			}else
				count++;
		}
	}
	
	public void paint( Graphics g ){
		/*g.drawImage(sprites.get(0), x, y, width, height, null, null);
		if( !this.open )
			g.drawImage(sprites.get(1), x, y, width, height, null, null);
		*/
		if( !this.open ){	
			g.setColor(Color.GREEN);
			g.fillOval(x,y,width,height);
			g.drawImage(sprites.get(0), x+8, y, 32, 32, null, null);
		}else{
			if( this.hasCroc )
				g.drawImage(sprites.get(1), x, y, width, height, null, null);
		}
	}
}
