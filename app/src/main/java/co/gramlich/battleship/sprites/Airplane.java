package co.gramlich.battleship.sprites;


import android.graphics.Canvas;
import co.gramlich.battleship.shared.GameView;
import co.gramlich.battleship.R;


public class Airplane extends Enemy {

	public static final int ELEVATION_UPPER_BOUND = 0;
	public static final int DISTANCE_BELOW_UPPER_BOUND = 300;

	public Airplane(Canvas canvas) {
		super(canvas);
	}
	
	@Override
	protected void initializeSmallEnemy() {
		setPointValue(75);
		setImageLeft(GameView.skin.getSmallAirplaneLeft());
		setImageRight(GameView.skin.getSmallAirplaneRight());
		setImageExplosion(R.drawable.airplane_explosion);
	}

	@Override
	protected void initializeMediumEnemy() {
		setPointValue(20);
		setImageLeft(R.drawable.medium_airplane);
		setImageRight(R.drawable.medium_airplane_flip);
		setImageExplosion(R.drawable.airplane_explosion);
	}

	@Override
	protected void initializeLargeEnemy() {
		setPointValue(15);
		setImageLeft(R.drawable.big_airplane);
		setImageRight(R.drawable.big_airplane_flip);
		setImageExplosion(R.drawable.airplane_explosion);
	}

	@Override
	protected float getElevation() {
		return (float) random.nextInt(DISTANCE_BELOW_UPPER_BOUND) + ELEVATION_UPPER_BOUND;
	}
}
