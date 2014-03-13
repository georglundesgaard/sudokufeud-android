package no.lundesgaard.sudokufeud.sudokufeud_android.events;

import java.util.List;

public class BoardFetchedEvent extends BaseEvent {

    private List<Integer> board;

    public BoardFetchedEvent(List<Integer> board) {
        this.board = board;
    }

    public List<Integer> getBoard() {
        return board;
    }

}
