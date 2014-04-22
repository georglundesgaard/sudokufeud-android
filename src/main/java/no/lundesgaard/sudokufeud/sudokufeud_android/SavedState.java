package no.lundesgaard.sudokufeud.sudokufeud_android;

import android.util.Log;
import no.lundesgaard.sudokufeud.sudokufeud_android.model.Board;
import no.lundesgaard.sudokufeud.sudokufeud_android.util.Constants;

import java.io.Serializable;
import java.util.List;

public class SavedState implements Serializable {
	private Board board = null;
	List<Integer> availablePieces = null;
	public String gameId;
	public String opponent;
	public String state;
	public String status;
	public int score;
	public String currentPlayer;
	public String difficulty;
	public String mainPlayerAuth;
	public int widthPixels;

// Todo: Fjern dette før release
	public boolean isOriginalPlayer;
	public String otherPlayerAuth;
	public int opponentScore;


	public SavedState() {
		Log.i(Constants.TAG, "SavedState contructed");
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		Log.i(Constants.TAG, "setBoard called. old=" + this.board + ", new=" + board);
		this.board = board;
	}

	public List<Integer> getAvailablePieces() {
		return availablePieces;
	}

	public void setAvailablePieces(List<Integer> availablePieces) {
		this.availablePieces = availablePieces;
	}

	public boolean isFilled() {
		return board != null;
	}

	@Override
	public String toString() {
		return "SavedState{" +
				"board=" + board +
				", availablePieces=" + availablePieces +
				", opponent='" + opponent + '\'' +
				", state='" + state + '\'' +
				", status='" + status + '\'' +
				", currentPlayer='" + currentPlayer + '\'' +
				", difficulty='" + difficulty + '\'' +
				'}';
	}
}
