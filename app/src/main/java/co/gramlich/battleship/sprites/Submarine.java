package co.gramlich.battleship.sprites;



import android.graphics.Canvas;

import co.gramlich.battleship.R;
import co.gramlich.battleship.sprites.Enemy;

public class Submarine extends Enemy {
	

	public Submarine(Canvas c) {
		super(c);
	}
	
	@Override
	protected int explodingImage() {
		return R.drawable.submarine_explosion;
	}

	
//	@Override
//	protected float getSpeedRatio(Context c) {
//		return Options.getSubSpeed(c);
//	}
	
	@Override
	public void reset() {
		super.reset();
		switch (getSize()) {
		case SMALL:
			pointValue = 150;
			if (getDirection() == Direction.LEFT_TO_RIGHT) {
				setImage(R.drawable.little_submarine, canvas);
			} else {
				setImage(R.drawable.little_submarine_flip, canvas);
			}
			break;
		case MEDIUM:
			pointValue = 40;
			if (getDirection() == Direction.LEFT_TO_RIGHT) {
				setImage(R.drawable.medium_submarine, canvas);
			} else {
				setImage(R.drawable.medium_airplane_flip, canvas);
			}
			break;
		case LARGE:
			pointValue = 25;
			if (getDirection() == Direction.LEFT_TO_RIGHT) {
				setImage(R.drawable.big_submarine, canvas);
			} else {
				setImage(R.drawable.big_submarine_flip, canvas);
			}
			break;
		}
		setStartingPosition();

	}
	
//	@Override
//	protected void setStartingPosition() {
//		super.setStartingPosition();//x
//		float min = canvasHeight/2 * 1.2f;
//		float max = canvasHeight - getHeight();
//		float y = (float)(Math.random() * (max - min) + min);
//		//Log.d("CS203", "plane.y = " + y);
//		setCenterY(y);
//	}
	
//	@Override
//	public int compareTo(Sprite other) {
//		//subs are sorted top to bottom
//		if (bounds.top > other.bounds.top) {
//			return -1;
//		}
//		if (bounds.top < other.bounds.top) {
//			return 1;
//		}
//		return 0;
//	}

	@Override
	public float getMaxY() {
		return canvasHeight - getHeight();

	}

	@Override
	public float getMinY() {
		return canvasHeight/2 * 1.2f;
	}



}
