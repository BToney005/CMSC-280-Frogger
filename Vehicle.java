public class Vehicle extends Obstacle {
	public Vehicle( int x, int y, int width, int height, int velocity){
		super(x, y, width, height, velocity, false);
	}
	
	public Vehicle(){
		this(0, 0, 0, 0, 0);
	}

}
