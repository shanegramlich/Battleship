package co.gramlich.battleship.scoreboard;

import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import co.gramlich.battleship.ScoreBoardActivity;

public class HighScore implements Comparable<HighScore> {

	private String name;
	private Integer score;

	public HighScore(String n, int s) {
		name = n;
		score = s;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public TableRow createTableRow(final ScoreBoardActivity c) {
		final TextView left, right;
		if (name == null) {
			left = new EditText(c);
			left.setHint("Type your name here");
			left.setRawInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
			left.setOnKeyListener(new OnKeyListener() {
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					// If the event is a key-down event on the "enter" button
					if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
							(keyCode == KeyEvent.KEYCODE_ENTER)) {
						// Save name on key press
						Log.d("CS203", left.getText().toString());
						name = left.getText().toString();
						InputMethodManager imm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(left.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
						c.updateScores();
						return true;
					}
					return false;
				}
			});
		} else {
			left = new TextView(c);
			left.setText(name + "   ");
		}
		right = new TextView(c);
		right.setText(""+score);
		TableRow row = new TableRow(c);
		row.addView(left);
		row.addView(right);
		return row;
	}

	@Override
	public int compareTo(HighScore another) {
		return -score.compareTo(another.score);
	}

	public void saveState(XmlSerializer xs) throws IllegalArgumentException, IllegalStateException, IOException {
		xs.startTag("", "highscore");
		xs.attribute("", "name", name);
		xs.attribute("", "score", ""+score);
		xs.endTag("", "highscore");
		Log.d("CS203", "saving " + name + " and " + score);
	}


}
