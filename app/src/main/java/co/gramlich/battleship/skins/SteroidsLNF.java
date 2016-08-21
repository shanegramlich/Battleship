package co.gramlich.battleship.skins;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;

import co.gramlich.battleship.R;

public class SteroidsLNF implements LookAndFeel {

	public SteroidsLNF() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getTextColor() {
		return Color.RED;
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
		return R.drawable.battleship_green;
	}

	@Override
	public int getBigAirplaneLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMediumAirplaneLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSmallAirplaneLeft() {
		return R.drawable.airplane_small_left_green;
	}

	@Override
	public int getBigAirplaneRight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMediumAirplaneRight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSmallAirplaneRight() {
		return R.drawable.airplane_small_right_purple;
	}

	@Override
	public int getBigSubmarineLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMediumSubmarineLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSmallSubmarineLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBigSubmarineRight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMediumSubmarineRight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSmallSubmarineRight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGunsmoke() {
		// TODO Auto-generated method stub
		return 0;
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
	public PointF getLeftGunPosition() {
		// TODO Auto-generated method stub
		PointF pos = new PointF();
		pos.x = 0.125683f;
		pos.y = 1f;
		return pos;
	}

	@Override
	public PointF getRightGunPosition() {
		// TODO Auto-generated method stub
		PointF pos = new PointF();
		pos.x = 0.918033f;
		pos.y = 1f;
		return pos;
	}

}
