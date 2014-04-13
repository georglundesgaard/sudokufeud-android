package no.lundesgaard.sudokufeud.sudokufeud_android.rest.model;

import java.util.Date;
import java.util.List;

public class Game {

    private List<Integer> board;
    private List<Integer> availablePieces;
	private String id;
	private int score;
	private String opponentUserId;
	private int opponentScore;
	private String state;
	private String status;
	private String currentPlayer;
	private String difficulty;
//	private Date created;

	public List<Integer> getBoard() {
        return board;
    }

    public void setBoard(List<Integer> board) {
        this.board = board;
    }

    public List<Integer> getAvailablePieces() {
        return availablePieces;
    }

    public void setAvailablePieces(List<Integer> availablePieces) {
        this.availablePieces = availablePieces;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getOpponentUserId() {
		return opponentUserId;
	}

	public void setOpponentUserId(String opponentUserId) {
		this.opponentUserId = opponentUserId;
	}

	public int getOpponentScore() {
		return opponentScore;
	}

	public void setOpponentScore(int opponentScore) {
		this.opponentScore = opponentScore;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

/*	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
*/
}
