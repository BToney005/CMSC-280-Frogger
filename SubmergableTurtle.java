import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SubmergableTurtle extends Turtle{
	public SubmergableTurtle(int x, int y, int velocity, int count, int subFreq) {
		super(x, y, velocity);
		this.canSubmerge = true;
		this.submerged = false;
		this.count = count;
		this.submergeFrequency = subFreq; // seconds * fps 
		this.sprites = new ArrayList<>();
		try{
			this.sprites.add(ImageIO.read(new File("frogger_sprites.png")).getSubimage(14, 406, 31, 21));
			this.sprites.add(ImageIO.read(new File("frogger_sprites.png")).getSubimage(176, 407, 30, 21));
			this.sprites.add(ImageIO.read(new File("frogger_sprites.png")).getSubimage(134, 407, 28, 21));
		}catch( Exception e){
			System.err.println("Failed to load sprite: Turtle");
			System.exit(0);
		}
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick(){
		x+=velocity;
		if( velocity < 0 && x + width <= 0 )
			this.x = 640;
		else if( velocity > 0 && x >= 640 )
			this.x = -1 * width;
		if(count == submergeFrequency){
			if(submerged)
				submerged = false;
			else
				submerged = true;
			count = 0;
			
		}
		count++;
	}
	
	@Override
	public void paint( Graphics g ){
		if(!submerged){
			if( count < submergeFrequency/3 )
				img = sprites.get(2);
			else if( count > 2 * submergeFrequency/3 )
				img = sprites.get(2);
			else
				img = sprites.get(0);
		}else if(submerged){
			img = sprites.get(1);
		}
		g.drawImage(img, x, y, width, height, null, null);

	}

	ArrayList<Image> sprites;
	int count, submergeFrequency;
}
