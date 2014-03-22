package no.lundesgaard.sudokufeud.sudokufeud_android.rest.model;

import java.util.List;

public class Game {

    private List<Integer> board;

    private List<Integer> availablePieces;

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
}
