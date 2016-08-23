package co.gramlich.battleship.skins;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;

import co.gramlich.battleship.R;

public class RetroSkin implements Skins {

	public RetroSkin() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getTextColor() {
		return Color.BLACK;
	}

	@Override
	public void drawBackground(Canvas c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawForeground(Canvas c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawOverlay(Canvas c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBattleship() {
		return R.drawable.battleship;
	}

	@Override
	public int getLargeAirplaneLeft() {
		return R.drawable.airplane_large_left;
	}

	@Override
	public int getMediumAirplaneLeft() {
		return R.drawable.airplane_medium_left;
	}

	@Override
	public int getSmallAirplaneLeft() {
		return R.drawable.airplane_small_left;
	}

	@Override
	public int getLargeAirplaneRight() {
		return R.drawable.airplane_large_right;
	}

	@Override
	public int getMediumAirplaneRight() {
		return R.drawable.airplane_medium_right;
	}

	@Override
	public int getSmallAirplaneRight() {
		return R.drawable.airplane_small_right;
	}

	@Override
	public int getLargeSubmarineLeft() {
		return R.drawable.submarine_large_left;
	}

	@Override
	public int getMediumSubmarineLeft() {
		return R.drawable.submarine_medium_left;
	}

	@Override
	public int getSmallSubmarineLeft() {
		return R.drawable.submarine_small_left;
	}

	@Override
	public int getLargeSubmarineRight() {
		return R.drawable.submarine_large_right;
	}

	@Override
	public int getMediumSubmarineRight() {
		return R.drawable.submarine_medium_right;
	}

	@Override
	public int getSmallSubmarineRight() {
		return R.drawable.submarine_small_right;
	}

	@Override
	public int getGunsmoke() {
		return R.drawable.gunsmoke;
	}

	@Override
	public int getAirplaneExplosion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSubmarineExplosion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDepthCharge() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWater() {
		return R.drawable.water;
	}

	@Override
	public PointF getLeftGunPosition() {
		return new PointF(754,438);
	}

	@Override
	public PointF getRightGunPosition() {
		return new PointF(1189,438);
	}
}
