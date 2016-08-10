package co.gramlich.battleship.sprites;



import android.graphics.Canvas;

import co.gramlich.battleship.R;
import co.gramlich.battleship.shared.SoundFX;

public class DepthCharge extends Sprite {

	private int beepDelay;
	
	public DepthCharge(Canvas c) {
		setImage(R.drawable.depth_charge, c);
		setTop(canvasHeight/2);
		setCenterX(canvasWidth/2);
		velocity.y = canvasHeight/100;
		beepDelay = 0;
	}
	
	public void move(SoundFX fx) {
		move();
		if (beepDelay % 10 == 0 && bounds.top < canvasHeight) {
			fx.dcBeep();
		}
		++beepDelay;
	}

	public boolean isSinking() {
		return (bounds.top < canvasHeight);
	}
}
