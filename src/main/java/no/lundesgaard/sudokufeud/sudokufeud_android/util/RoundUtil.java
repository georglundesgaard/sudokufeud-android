package no.lundesgaard.sudokufeud.sudokufeud_android.util;

import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Move;

import java.util.ArrayList;
import java.util.List;

public class RoundUtil {

    private List<Move> moves;

    public RoundUtil() {
        moves = new ArrayList<Move>();
    }

    public void makeMove(Move move) {
        moves.add(move);
    }

    public List<Move> getMoves() {
        return moves;
    }
}
