package co.gramlich.battleship.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;

public class Bullet extends Sprite {

	private Direction dir;
	
	public Bullet(PointF pos, Direction d) {
		super();
		PointF p1 = pos;
		dir = d;
		paint.setColor(Color.rgb(128, 128, 128));
		float magic = canvasHeight/20;
		if (dir == Direction.RIGHT_TO_LEFT) {
			PointF p2 = new PointF(p1.x - magic, p1.y - magic);
			bounds.set(p2.x, p2.y, p1.x, p1.y);
			velocity.x = -magic;
		} else {
			PointF p2 = new PointF(p1.x + magic, p1.y - magic);
			bounds.set(p1.x, p2.y, p2.x, p1.y);
			velocity.x = magic;
		}
		velocity.y = -magic;		
	}
	
	@Override
	public void draw(Canvas c) {
		//paint.setColor(color);
		if (dir == Direction.RIGHT_TO_LEFT) {
			c.drawLine(bounds.right, bounds.bottom,
					bounds.left, bounds.top, paint);
		} else {
			c.drawLine(bounds.left, bounds.bottom,
					bounds.right, bounds.top, paint);
		}
	}
	
	public boolean isVisible() {
		return (bounds.top > 0);
	}
	
	public Direction direction() {
		return dir;
	}

}
