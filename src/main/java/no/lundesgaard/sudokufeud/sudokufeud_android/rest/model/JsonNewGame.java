package no.lundesgaard.sudokufeud.sudokufeud_android.rest.model;

public class JsonNewGame {
	private String opponent;
	private String difficulty;

	public JsonNewGame(String opponent, String difficulty) {
		this.opponent = opponent;
		this.difficulty = difficulty;
	}

	public String getOpponent() {
		return opponent;
	}

	public String getDifficulty() {
		return difficulty;
	}
}
