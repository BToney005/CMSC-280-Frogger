import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

public class YellowCar extends Vehicle {

	public YellowCar(int x, int velocity) {
		super(x, 440, 60, 36, velocity);
		try{
			this.img = ImageIO.read(new File("frogger_sprites.png")).getSubimage(81, 265, 24, 23);	
		}catch( Exception e ){
			System.err.println("Failed to load sprite: Truck");
			System.exit(0);
		}
	}

	public YellowCar() {
		this(640, -6);// TODO Auto-generated constructor stub
	}
	
	@Override
	public void paint( Graphics g ){
		g.drawImage(img, x, y, width, height, null, null);
	}

}
