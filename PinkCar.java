import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

public class PinkCar extends Vehicle {

	public PinkCar(int x, int velocity) {
		super(x, 520, 60, 36, velocity);
		try{
			this.img = ImageIO.read(new File("frogger_sprites.png")).getSubimage(10, 266, 27, 20);	
		}catch( Exception e ){
			System.err.println("Failed to load sprite: Truck");
			System.exit(0);
		}
	}

	public PinkCar() {
		this(320, -5);// TODO Auto-generated constructor stub
	}
	
	@Override
	public void paint( Graphics g ){
		g.drawImage(img, x, y, width, height, null, null);
	}

}
