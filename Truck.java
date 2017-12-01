import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

public class Truck extends Vehicle {

	public Truck(int x, int velocity) {
		super(x, 400, 100, 36, velocity);
		try{
			this.img = ImageIO.read(new File("frogger_sprites.png")).getSubimage(106, 303, 45, 17);	
		}catch( Exception e ){
			System.err.println("Failed to load sprite: Truck");
			System.exit(0);
		}
	}

	public Truck() {
		this(640, -5);// TODO Auto-generated constructor stub
	}
	
	@Override
	public void paint( Graphics g ){
		g.drawImage(img, x, y, width, height, null, null);
	}

}
