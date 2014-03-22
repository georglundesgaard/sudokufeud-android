package no.lundesgaard.sudokufeud.sudokufeud_android.events;

import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Game;

import java.util.List;

public class GameFetchedEvent extends BaseEvent {

    private Game game;

    public GameFetchedEvent(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
