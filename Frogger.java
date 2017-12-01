import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Frogger extends JFrame{
	public Frogger(){
		super();
		
		this.width = 640;
		this.height = 640;
		this.fps = 60;
		this.gameOver = false;
		this.godMode = false;
		
		this.setBounds(0, 0, width, height + 70);
		this.setLayout(new FlowLayout());
		this.setTitle("Frogger");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		
		this.obstacles = new ArrayList<>();
		
		this.obstacles.add( new Car() );
		this.obstacles.add( new Truck() );
		this.obstacles.add( new PinkCar() );
		this.obstacles.add( new YellowCar() );
		this.obstacles.add( new Plow() );
		this.obstacles.add( new Plow(25, 2) );
		this.obstacles.add( new Plow(100, 2) );
		this.obstacles.add( new Car( 0, 560, 5));
		
		this.obstacles.add( new Log(0, 240, 2, 2));
		this.obstacles.add( new Log(170, 160, 4, 0));
		this.obstacles.add( new Log(490, 120, 3, 1));
		
		this.obstacles.add( new Log(490, 240, 2, 2));
		this.obstacles.add( new Log(490, 160, 4, 0));
		this.obstacles.add( new Log(170, 120, 3, 1));
		
		this.obstacles.add( new Turtle(0, 280, -2));
		this.obstacles.add( new Turtle(65, 280, -2));
		this.obstacles.add( new Turtle(130, 280, -2));
		
		this.obstacles.add( new Turtle(0, 200, -4));
		this.obstacles.add( new Turtle(65, 200, -4));
		this.obstacles.add( new Turtle(130, 200, -4));
		
		this.obstacles.add( new SubmergableTurtle(250, 200, -4, 0, 2 * fps));
		this.obstacles.add( new SubmergableTurtle(315, 200, -4, 0, 2 * fps));
		this.obstacles.add( new SubmergableTurtle(380, 200, -4, 0, 2 * fps));
		
		this.obstacles.add( new Turtle(0, 80, -3));
		this.obstacles.add( new Turtle(65, 80, -3));
		this.obstacles.add( new Turtle(130, 80, -3));
		
		this.obstacles.add( new SubmergableTurtle(300, 80, -3, 2 * fps/2, 4 * fps));
		this.obstacles.add( new SubmergableTurtle(365, 80, -3, 2 * fps/2, 4 * fps));
		this.obstacles.add( new SubmergableTurtle(430, 80, -3, 2 * fps/2, 4 * fps));
		
		
		this.goals = new ArrayList<>();
		this.goals.add(new Goal( 20, 25 ));
		this.goals.add(new Goal( 157, 25 ));
		this.goals.add(new Goal( 294, 25 ));
		this.goals.add(new Goal( 430, 25 ));
		this.goals.add(new Goal( 566, 25 ));
		
		this.frog = new Frog( width/2 - 16, height - 40, 32, 32);
		frog.lives = 0;
		this.stage = new Stage( 0, 0, width, height, obstacles, this.frog );
		
		view = new GamePanel(this.width, this.height, this.frog, this.obstacles, this.goals);
		this.add(view);
		view.setFocusable(true);
		view.requestFocusInWindow();
        view.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
            	int key = e.getKeyCode();
            	switch(key){
	            	case 37: view.frog.move(0); break; //left arrow
	            	case 38: view.frog.move(1); break; //up arrow
	            	case 39: view.frog.move(2); break; // right arrow
	            	case 40: view.frog.move(3); break; //down arrow
	            	case 71: toggleGodMode(); break;
            	}
            	Frogger.this.repaint();
            }
        });
        
        status = new JLabel();
		status.setText("Press Start To Begin");
		status.setVisible(true);
		this.add(status);
        
		JButton startBtn = new JButton("Start");
		this.add(startBtn);
		startBtn.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent e ){
				if(gameOver){
					frog.resetPosition();
					frog.lives = 3;
					frog.unpause();
					stage.resetStage(obstacles);
					view.requestFocusInWindow();
					for ( Goal g : goals ){
						g.reset();
					}
					timer.start();
					status.setVisible(false);
					gameOver = false;
				}
			}
		});
		
		JButton pauseBtn = new JButton("Pause");
		this.add(pauseBtn);
		pauseBtn.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed( ActionEvent e ){
				if(!view.frog.isPaused()){
					Frogger.this.timer.stop();
					view.frog.pause();
					status.setText("Game Paused");
					status.setVisible(true);
				}else{
					status.setVisible(false);
					Frogger.this.timer.start();
					view.frog.unpause();
					view.requestFocusInWindow();
				}
			}
		});
		
		JButton quitBtn =  new JButton("Quit");
		this.add(quitBtn);
		quitBtn.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//quit game
				System.exit(0);
			}
		});
		
		lives = new JLabel( "Lives: " + frog.lives );
		this.add(lives);
		if( godMode )
			lives.setText("Lives: INFINITY");
		
		timer = new Timer( 1000/fps, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(godMode && !status.isVisible()){
					status.setText("God Mode Enabled");
					status.setVisible(true);
				}
				if( Frogger.this.frog.lives == 0 ){
					frog.pause();
					timer.stop();
					gameOver = true;
					frog.lives = 3;
				}
				boolean hitObstacle = false;
				for( Obstacle o : obstacles ){
					if(frog.lives <= 0)
						break;
					o.tick();
					
					if(!godMode){
						if(stage.checkCollision(o))
							hitObstacle = true;
					}
				}

				numGoals = 0;
				for( Goal g : goals ){
					if(godMode && !g.hasCroc && g.open)
						g.reset();
					else if( !godMode)
						g.tick();
					if(stage.checkCollision(g))
						hitObstacle = true;
					if(!g.open)
						numGoals++;
				}
				if( numGoals == 5 ){
					status.setText("You Win!");
					status.setVisible(true);
					frog.pause();
					gameOver = true;
					frog.lives = 3;
					for( Goal g : goals){
						g.reset();
					}
					timer.stop();
				}
				
				frog.setOverWater();
				
				if( !hitObstacle && frog.overWater && !godMode)
					frog.kill();
				else if( frog.overWater && !godMode){
					if( frog.x + frog.width < 0 || frog.x > Frogger.this.width )
						frog.kill();
				}
				
				if( godMode )
					lives.setText("Lives: INFINITY");
				else
					lives.setText("Lives: " + frog.lives );
				Frogger.this.repaint();
			}
		});
		
		this.setVisible(true);
	}
	
	void toggleGodMode(){
		if(!godMode){
			for( Goal g : goals){
				if(g.open)
					g.reset();
			}
			godMode = true; 
			status.setText("God Mode Enabled");
			status.setVisible(true);
		}else{
			godMode = false;
			status.setVisible(false);
		}
	}
	
	GamePanel view;
	JLabel time, lives, status;
	Frog frog;
	ArrayList<Obstacle> obstacles;
	ArrayList<Goal> goals;
	Stage stage;
	boolean gameOver, godMode, canMove;
	Timer timer;
	final int width, height;
	int numGoals;
	int fps;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frogger frogger = new Frogger();
		frogger.timer.start();
	}

}
