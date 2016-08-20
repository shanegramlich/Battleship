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
	protected void getSmall() {
		setPointValue(150);
		setImageLeft(R.drawable.little_submarine_flip);
		setImageRight(R.drawable.little_submarine);
		setImageExplosion(R.drawable.submarine_explosion);
	}

	@Override
	protected void getMedium() {
		setPointValue(40);
		setImageLeft(R.drawable.medium_submarine_flip);
		setImageRight(R.drawable.medium_submarine);
		setImageExplosion(R.drawable.submarine_explosion);
	}

	@Override
	protected void getLarge() {
		setPointValue(25);
		setImageLeft(R.drawable.big_submarine_flip);
		setImageRight(R.drawable.big_submarine);
		setImageExplosion(R.drawable.submarine_explosion);
	}

	@Override
	protected float getElevation() {
		return (float) random.nextInt(DISTANCE_BELOW_UPPER_BOUND) + ELEVATION_UPPER_BOUND;
	}
}
