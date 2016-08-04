package co.gramlich.battleship.sprites;


import android.graphics.Canvas;
import android.util.Log;

import co.gramlich.battleship.GameView;
import co.gramlich.battleship.R;


public class Airplane extends Enemy {

	public Airplane(Canvas c) {
		super(c);
		Log.d("CS203", ""+bounds);
	}
	
	@Override
	protected int explodingImage() {
		return R.drawable.airplane_explosion;
	}

	public float getMaxY() {
		return (canvasHeight/2) * 0.5f;
	}

	public float getMinY() {
		return getHeight();
	}

	@Override
	public void reset() {
		super.reset();
		switch (getSize()) {
		case SMALL:
			pointValue = 75;
			if (getDirection() == Direction.LEFT_TO_RIGHT) {
				setImage(GameView.skin.getSmallAirplaneRight(), canvas);
			} else {
				setImage(GameView.skin.getSmallAirplaneLeft(), canvas);
			}
			break;
		case MEDIUM:
			pointValue = 20;
			if (getDirection() == Direction.LEFT_TO_RIGHT) {
				setImage(R.drawable.medium_airplane_flip, canvas);
			} else {
				setImage(R.drawable.medium_airplane, canvas);
			}
			break;
		case LARGE:
			pointValue = 15;
			if (getDirection() == Direction.LEFT_TO_RIGHT) {
				setImage(R.drawable.big_airplane_flip, canvas);
			} else {
				setImage(R.drawable.big_airplane, canvas);
			}
			break;
		}
		setStartingPosition();

	}


	//@Override
	//public int compareTo(Sprite other) {
	//	//airplanes are sorted bottom to top
	//	if (bounds.top > other.bounds.top) {
	//		return 1;
	//	}
	//	if (bounds.top < other.bounds.top) {
	//		return -1;
	//	}
	//	return 0;
	//}
}
