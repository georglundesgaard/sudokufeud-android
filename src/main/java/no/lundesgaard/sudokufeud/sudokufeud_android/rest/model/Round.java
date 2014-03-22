package no.lundesgaard.sudokufeud.sudokufeud_android.rest.model;

import java.util.List;

public class Round {

    private List<Move> moves;

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
}
