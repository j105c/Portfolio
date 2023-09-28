import java.awt.*;
public class Bullet extends Circle {
	
	public static final int RADIUS = 10;
	public double rotation;
	public Point center;
	
	public Bullet(Point center, double rotation) {

		super(center, RADIUS); // define RADIUS in Bullet class

		this.rotation = rotation;
		this.center = center;

	}

	@Override
	public void paint(Graphics brush, Color color) {
		// TODO Auto-generated method stub
		brush.setColor(color);
		brush.fillOval((int) center.x, (int) center.y, radius, radius);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		center.x += 2 * Math.cos(Math.toRadians(rotation));
		center.y += 2 * Math.sin(Math.toRadians(rotation));
	}
	
	public boolean outOfBounds() {
		
		boolean out = false;
		
		if(center.x > Asteroids.SCREEN_WIDTH) {
            out = true;
        } else if(center.x < 0) {
        	out = true;
        }
		if(center.y > Asteroids.SCREEN_HEIGHT) {
        	out = true;
        } else if(center.y < 0) {
        	out = true;
        }
		return out;
		
		
	}
	
	public Point getCenter() {
		return center;
	}

}
