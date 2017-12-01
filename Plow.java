import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Plow extends Vehicle {

	public Plow(int x, int velocity) {
		super(x, 480, 50, 36, velocity);
		count = 0;
		sprites = new ArrayList<>();
		try{
			sprites.add(ImageIO.read(new File("frogger_sprites.png")).getSubimage(10, 300, 25, 22));	
			sprites.add(ImageIO.read(new File("frogger_sprites.png")).getSubimage(41, 300, 25, 22));	
			sprites.add(ImageIO.read(new File("frogger_sprites.png")).getSubimage(73, 300, 25, 22));	
		}catch( FileNotFoundException e ){
			System.err.println("Failed to load sprite: Truck");
			System.exit(0);
		}catch( Exception e ){
		}
	}
	
	public Plow() {
		this(640, 2);// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(){
		super.tick();
		count++;
	}
	
	@Override
	public void paint( Graphics g ){
		switch(count%7){
			case 0: img = sprites.get(0); break;
			case 3: img = sprites.get(1); break;
			case 5: img = sprites.get(2); break;
		}
		g.drawImage(img, x, y, width, height, null, null);
	}

	ArrayList<Image> sprites; 
	int count;

}
