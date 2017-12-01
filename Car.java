import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

public class Car extends Vehicle {
	public Car( int x, int y, int velocity ){
		super( x, y, 60, 36, velocity );
		try{
			this.img = ImageIO.read(new File("frogger_sprites.png")).getSubimage(45, 265, 29, 24);	
		}catch( Exception e ){
			System.err.println("Failed to load sprite: Car");
			System.exit(0);
		}
	}
	
	public Car(int x, int velocity) {
		this(x, 360, velocity );
	}

	public Car() {
		this(-60, 4);// TODO Auto-generated constructor stub
	}
	
	@Override
	public void paint( Graphics g ){
		g.drawImage(img, x, y, width, height, null, null);
	}

}
