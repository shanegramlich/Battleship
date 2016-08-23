package co.gramlich.battleship.sprites;



import android.graphics.Canvas;

import co.gramlich.battleship.R;
import co.gramlich.battleship.shared.GameView;

public class Submarine extends Enemy {

	public static final int ELEVATION_UPPER_BOUND = 700;
	public static final int DISTANCE_BELOW_UPPER_BOUND = 300;

	public Submarine(Canvas canvas) {
		super(canvas);
	}

	@Override
	protected void initializeSmallEnemy() {
		setPointValue(150);
		setImageLeft(GameView.skin.getSmallSubmarineLeft());
		setImageRight(GameView.skin.getSmallSubmarineRight());
		setImageExplosion(R.drawable.submarine_explosion);
	}

	@Override
	protected void initializeMediumEnemy() {
		setPointValue(40);
		setImageLeft(GameView.skin.getMediumSubmarineLeft());
		setImageRight(GameView.skin.getMediumSubmarineRight());
		setImageExplosion(R.drawable.submarine_explosion);
	}

	@Override
	protected void initializeLargeEnemy() {
		setPointValue(25);
		setImageLeft(GameView.skin.getLargeSubmarineLeft());
		setImageRight(GameView.skin.getLargeSubmarineRight());
		setImageExplosion(R.drawable.submarine_explosion);
	}

	@Override
	protected float getElevation() {
		return (float) random.nextInt(DISTANCE_BELOW_UPPER_BOUND) + ELEVATION_UPPER_BOUND;
	}
}
