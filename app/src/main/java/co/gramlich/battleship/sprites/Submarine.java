package co.gramlich.battleship.sprites;



import android.graphics.Canvas;

import co.gramlich.battleship.R;

public class Submarine extends Enemy {

	public static final int ELEVATION_UPPER_BOUND = 700;
	public static final int DISTANCE_BELOW_UPPER_BOUND = 300;

	public Submarine(Canvas canvas) {
		super(canvas);
	}

	@Override
	protected void initializeSmallEnemy() {
		setPointValue(150);
		setImageLeft(R.drawable.submarine_small_left);
		setImageRight(R.drawable.submarine_small_right);
		setImageExplosion(R.drawable.submarine_explosion);
	}

	@Override
	protected void initializeMediumEnemy() {
		setPointValue(40);
		setImageLeft(R.drawable.submarine_medium_left);
		setImageRight(R.drawable.submarine_medium_right);
		setImageExplosion(R.drawable.submarine_explosion);
	}

	@Override
	protected void initializeLargeEnemy() {
		setPointValue(25);
		setImageLeft(R.drawable.submarine_large_left);
		setImageRight(R.drawable.submarine_large_right);
		setImageExplosion(R.drawable.submarine_explosion);
	}

	@Override
	protected float getElevation() {
		return (float) random.nextInt(DISTANCE_BELOW_UPPER_BOUND) + ELEVATION_UPPER_BOUND;
	}
}
