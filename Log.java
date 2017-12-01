import java.awt.Graphics;
import java.io.File;
import javax.imageio.ImageIO;

public class Log extends WaterObstacle {
	public Log(int x, int y, int velocity, int type){
		super(x, y, 0, 36, velocity, false, false);
		try{
			switch(type){
				case 0: this.width = 120; this.img = ImageIO.read(new File("frogger_sprites.png")).getSubimage(7, 230, 84, 20); break;
				case 1: this.width = 180; this.img = ImageIO.read(new File("frogger_sprites.png")).getSubimage(7, 198, 199, 20);break;
				case 2: this.width = 240; this.img = ImageIO.read(new File("frogger_sprites.png")).getSubimage(7, 166, 177, 20);break;
				default: this.width = 120; this.img = ImageIO.read(new File("frogger_sprites.png")).getSubimage(7, 230, 84, 20);break;
			}
		}catch( Exception e){
			System.err.println("Failed to load sprite: Log");
			System.exit(0);
		}
	}
	
	@Override
	public void paint( Graphics g ){
		g.drawImage(img, x, y, width, height, null, null);
	}
	
}
