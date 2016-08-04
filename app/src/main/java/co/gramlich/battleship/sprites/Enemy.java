package co.gramlich.battleship.sprites;

import java.util.LinkedList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.RectF;

import co.gramlich.battleship.BattleshipActivity;

public abstract class Enemy extends Sprite {

	private int vx;
	private Direction dir;
	private Size size;
	protected int pointValue;
	protected Canvas canvas;
	//protected Bitmap explosion;
	private boolean exploding;
	//protected int explodingImage;
	private static List<RectF> yRegistry = new LinkedList<RectF>();
	public RectF yRect;
	private float maxVelocity;


	public enum Size {
		SMALL,
		MEDIUM,
		LARGE;
	}

	public Enemy(Canvas c) {
		super();
		canvas = c;
		maxVelocity = canvasWidth * 0.00625f;//TODO replace with preferences value
		reset();		
	}
	
	public float maxVelocity() {
		return maxVelocity;
	}

	public void reset() {
		exploding = false;
		if (Math.random() < 0.5) {
			dir = Direction.LEFT_TO_RIGHT;
			vx = 1;
		} else {
			dir = Direction.RIGHT_TO_LEFT;
			vx = -1;
		}
		randomVelocity();


		double s = Math.random();
		if (s < 0.333) {
			size = Size.SMALL;
		} else if (s < 0.667) {
			size = Size.MEDIUM;
		} else {
			size = Size.LARGE;
		}

	}

	public Direction getDirection() {
		return dir;
	}

	public Size getSize() {
		return size;
	}

	@Override
	public void move() {
		super.move();
		if (Math.random() < 0.1) {
			randomVelocity();
		}
		boolean offscreen = false;
		if (dir == Direction.LEFT_TO_RIGHT) {
			if (bounds.left > canvasWidth) {
				offscreen = true;
			}
		} else {
			if (bounds.right < 0) {
				offscreen = true;
			}
		}
		if (offscreen) {
			reset();
		}
	}

	private void randomVelocity() {
		velocity.x = (float)(Math.random() * maxVelocity()) * vx;
	}

	protected void setStartingPosition() {
		if (dir == Direction.LEFT_TO_RIGHT) {
			setRight(0);
		} else {
			setLeft(canvasWidth);
		}
		//		@Override
		//		protected void setStartingPosition() {
		//			super.setStartingPosition();//x
		yRegistry.remove(yRect);
		float min = getMinY();
		float max = getMaxY();
		int attempts = 0;
		final int MAX_ATTEMPTS = 10;
		outerloop:
			while (attempts < MAX_ATTEMPTS) {
				++attempts;
				float y = (float)(Math.random() * (max - min) + min);
				//Log.d("CS203", "plane.y = " + y);
				setCenterY(y);
				yRect = new RectF(/*0, bounds.top, 1, bounds.bottom*/bounds);
				for (RectF other : yRegistry) {
					//if (yRect.top < other.bottom || yRect.bottom > other.top) {
					if (y > other.top && y < other.bottom) {
						//Log.d("CS203", yRect + " intersects " + other);
						continue outerloop;
					} else {
						//Log.d("CS203", yRect + " no intersect " + other);
					}
				}
				yRegistry.add(yRect);
				break;
			}
	}


	public int getPointValue() {
		return pointValue;
	}

	public void explode() {
		exploding = true;		
	}

	@Override
	public void draw(Canvas c) {
		if (exploding) {
			float mx = (bounds.right - bounds.left) / 2 + bounds.left;
			float my = (bounds.bottom - bounds.top) / 2 + bounds.top;
			image = BattleshipActivity.loadBitmap(explodingImage());
			int xw = image.getScaledWidth(c)/2;
			int xh = image.getScaledHeight(c)/2;
			bounds.set(mx - xw, my - xh, mx + xw, my + xh);
			velocity.set(0, 0);
		}
		super.draw(c);
		if (exploding) {
			reset();
		}
	}

	//	protected boolean onCollisionCourse(RectF other) {
	//		if (other == null) return false;
	//		RectF tmp1 = new RectF(0, bounds.top, 1, bounds.bottom);
	//		RectF tmp2 = new RectF(0, other.bounds.top, 1, other.bounds.bottom);
	//		return RectF.intersects(tmp1, tmp2);
	//	}

	protected abstract int explodingImage();

	public abstract float getMaxY();

	public abstract float getMinY();


}
