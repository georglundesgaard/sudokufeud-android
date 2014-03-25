package no.lundesgaard.sudokufeud.sudokufeud_android.model;

import android.util.Log;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.Constants;

import java.util.List;

public class Board {

	private List<Integer> board;

	public Board(List<Integer> board) {
		this.board = board;
	}

	public Integer getNumber(int x, int y) {
		if (x < 0 || x >= Constants.BOARD_WIDTH ||
				y < 0 || y >= Constants.BOARD_HEIGHT)
			throw new IllegalArgumentException("ulovelig posisjon (" + x + "," + y + ")");

		return board.get(x + y * Constants.BOARD_WIDTH);
	}

	public void storeNumber(int x, int y, Integer value) {
		if (x < 0 || x >= Constants.BOARD_WIDTH ||
				y < 0 || y >= Constants.BOARD_HEIGHT)
			throw new IllegalArgumentException("ulovelig posisjon (" + x + "," + y + ")");

		board.set(x + y * Constants.BOARD_WIDTH, value);
	}

	public Integer getFieldNumber (int square, int position) {
		Log.e(Constants.TAG, "getFieldNumber (" + square + "," + position + ") called");
		int x = (square % 3) * 3  + (position % 3);
		int y = (square / 3) * 3 + (position / 3);
		return getNumber(x,y);
	}

	public void storeFieldNumber(int square, int position, Integer value) {
		int x = (square % 3) * 3  + (position % 3);
		int y = (square / 3) * 3 + (position / 3);
		storeNumber(x,y, value);
	}
}
