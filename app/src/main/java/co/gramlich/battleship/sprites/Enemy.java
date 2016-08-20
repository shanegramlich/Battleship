package co.gramlich.battleship.sprites;

import android.graphics.Canvas;
import co.gramlich.battleship.BattleshipActivity;

public abstract class Enemy extends Sprite {

	protected int pointValue;
	protected Canvas canvas;
	private int velocityDirection;
	private boolean explode;
	private float maxVelocity;
	private int imageLeft;
	private int imageRight;
	private int imageExplosion;


	public Enemy(Canvas canvas) {
		super();
		this.canvas = canvas;
		maxVelocity = canvasWidth * 0.00625f;//TODO replace with preferences value;
		reset();
	}

	public void reset() {
		explode = false;
		rollEnemyVelocity();
		rollEnemySize();
		rollEnemyDirection();
		rollEnemyElevation();
	}

	private void rollEnemyVelocity() {
		velocity.x = (float)(Math.random() * maxVelocity) * velocityDirection;
	}

	private void rollEnemySize() {
		double sizeIndex = Math.random();
		if (sizeIndex < 0.333) {
			getSmall();
		} else if (sizeIndex < 0.667) {
			getMedium();
		} else {
			getLarge();
		}
	}

	private void rollEnemyDirection() {
		if (Math.random() < 0.5) {
			setImage(imageRight, canvas);
			velocityDirection = 1;
			setRight(0);
		} else {
			setImage(imageLeft, canvas);
			velocityDirection = -1;
			setLeft(canvasWidth);
		}
	}

	protected void rollEnemyElevation() {
		bounds.offsetTo(bounds.left, getElevation());
	}

	@Override
	public void move() {
		super.move();
		if (Math.random() < 0.40) {
			rollEnemyVelocity();
		}
		if (isOffscreen()) {
			reset();
		}
	}

	private boolean isOffscreen() {
		return bounds.left > canvasWidth || bounds.right < 0;
	}

	public void explode() {
		explode = true;
	}

	@Override
	public void draw(Canvas canvas) {
		if (explode) {
			float mx = (bounds.right - bounds.left) / 2 + bounds.left;
			float my = (bounds.bottom - bounds.top) / 2 + bounds.top;
			image = BattleshipActivity.loadBitmap(imageExplosion);
			int xw = image.getScaledWidth(canvas)/2;
			int xh = image.getScaledHeight(canvas)/2;
			bounds.set(mx - xw, my - xh, mx + xw, my + xh);
			velocity.set(0, 0);
		}
		super.draw(canvas);
		if (explode) {
			reset();
		}
	}

	protected abstract void getSmall();

	protected abstract void getMedium();

	protected abstract void getLarge();

	public int getPointValue() {
		return pointValue;
	}

	protected void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	protected void setImageLeft(int imageLeft) {
		this.imageLeft = imageLeft;
	}

	protected void setImageRight(int imageRight) {
		this.imageRight = imageRight;
	}

	protected void setImageExplosion(int imageExplosion) {
		this.imageExplosion = imageExplosion;
	}

	protected abstract float getElevation();
}
