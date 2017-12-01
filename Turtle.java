import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Turtle extends WaterObstacle {
	public Turtle(int x, int y, int velocity){
		super(x, y, 0, 36, velocity, false, false);
		this.width = 60;
		
		try{
			this.img = ImageIO.read(new File("frogger_sprites.png")).getSubimage(14, 406, 31, 21);
		}catch( Exception e){
			System.err.println("Failed to load sprite: Turtle");
			System.exit(0);
		}
	}

	@Override
	public void paint( Graphics g ){
		g.drawImage(img, x, y, width, height, null, null);
	}

}
