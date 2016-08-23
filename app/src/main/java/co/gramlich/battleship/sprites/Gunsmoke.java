package co.gramlich.battleship.sprites;



import android.graphics.Canvas;

import co.gramlich.battleship.R;
import co.gramlich.battleship.shared.GameView;

public class Gunsmoke extends Sprite {

	public Gunsmoke(Canvas canvas) {
		setImage(GameView.skin.getGunsmoke(), canvas);
	}
}
