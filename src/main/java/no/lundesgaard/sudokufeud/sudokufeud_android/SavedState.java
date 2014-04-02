package no.lundesgaard.sudokufeud.sudokufeud_android;

import android.util.Log;
import no.lundesgaard.sudokufeud.sudokufeud_android.model.Board;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.Constants;

import java.io.Serializable;

public class SavedState implements Serializable {
	private Board originalBoard;
	private Board currentBoard;

	public SavedState() {
		Log.i(Constants.TAG, "SavedState contructed");
	}

	public Board getOriginalBoard() {
		Log.i(Constants.TAG, "getOriginalBoard called. originalBoard=" + originalBoard);
		return originalBoard;
	}

	public void setOriginalBoard(Board originalBoard) {
		Log.i(Constants.TAG, "setOriginalBoard called. old=" + this.originalBoard + ", new=" + originalBoard);
		this.originalBoard = originalBoard;
	}

	public Board getCurrentBoard() {
		Log.i(Constants.TAG, "getCurrentBoard called. currentBoard=" + currentBoard);
		return currentBoard;
	}

	public void setCurrentBoard(Board currentBoard) {
		Log.i(Constants.TAG, "setCurrentBoard called. old=" + this.currentBoard + ", new=" + currentBoard);
		this.currentBoard = currentBoard;
	}

	public boolean isFilled() {
		return originalBoard != null;
	}

	@Override
	public String toString() {
		return "State{" +
				"originalBoard=" + originalBoard +
				'}';
	}
}
