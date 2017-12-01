public class WaterObstacle extends Obstacle {
	boolean canSubmerge;
	boolean submerged;
	
	public WaterObstacle( int x, int y, int width, int height, int velocity, boolean canSubmerge, boolean submerged ){
		super(x, y, width, height, velocity, true);
		this.canSubmerge = canSubmerge;
		if(this.canSubmerge)
			this.submerged = false;
		else
			this.submerged = submerged;
	}
	
	public WaterObstacle(){
		this(0, 280, 160, 36, 2, false, false);
	}
}
