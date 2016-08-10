package co.gramlich.battleship.sprites;

import java.util.LinkedList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.RectF;

import co.gramlich.battleship.BattleshipActivity;

public abstract class Enemy extends Sprite {

	//protected int explodingImage;
	private static List<RectF> yRegistry = new LinkedList<RectF>();
	public RectF yRect;
	protected int pointValue;
	protected Canvas canvas;
	private int vx;
	private Direction direction;
	private Size size;
	//protected Bitmap explosion;
	private boolean exploding;
	private float maxVelocity;


	public Enemy(Canvas canvas) {
		super();
		this.canvas = canvas;
		maxVelocity = getMaxVelocity();
		reset();
	}

	private float getMaxVelocity() {
		return canvasWidth * 0.00625f;//TODO replace with preferences value
	}

	public float maxVelocity() {
		return maxVelocity;
	}
	
	public void reset() {
		exploding = false;
		if (Math.random() < 0.5) {
			direction = Direction.RIGHT;
			vx = 1;
		} else {
			direction = Direction.LEFT;
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
		return direction;
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
		if (isOffscreen()) {
			reset();
		}
	}

	private boolean isOffscreen() {
		return bounds.left > canvasWidth || bounds.right < 0;
	}

	private void randomVelocity() {
		velocity.x = (float)(Math.random() * maxVelocity()) * vx;
	}

	protected void setStartingPosition() {
		if (direction == Direction.RIGHT) {
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
	public void draw(Canvas canvas) {
		if (exploding) {
			float mx = (bounds.right - bounds.left) / 2 + bounds.left;
			float my = (bounds.bottom - bounds.top) / 2 + bounds.top;
			image = BattleshipActivity.loadBitmap(explodingImage());
			int xw = image.getScaledWidth(canvas)/2;
			int xh = image.getScaledHeight(canvas)/2;
			bounds.set(mx - xw, my - xh, mx + xw, my + xh);
			velocity.set(0, 0);
		}
		super.draw(canvas);
		if (exploding) {
			reset();
		}
	}

	protected abstract int explodingImage();

	//	protected boolean onCollisionCourse(RectF other) {
	//		if (other == null) return false;
	//		RectF tmp1 = new RectF(0, bounds.top, 1, bounds.bottom);
	//		RectF tmp2 = new RectF(0, other.bounds.top, 1, other.bounds.bottom);
	//		return RectF.intersects(tmp1, tmp2);
	//	}

	public abstract float getMaxY();

	public abstract float getMinY();
}
