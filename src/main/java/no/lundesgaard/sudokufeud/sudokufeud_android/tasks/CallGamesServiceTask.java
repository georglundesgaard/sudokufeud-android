package no.lundesgaard.sudokufeud.sudokufeud_android.tasks;

import no.lundesgaard.sudokufeud.sudokufeud_android.events.BoardFetchedEvent;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.GamesServiceClient;
import no.lundesgaard.sudokufeud.sudokufeud_android.rest.model.Game;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;

@EBean
public class CallGamesServiceTask extends BaseTask {

    @Background
    public void initializeGame() {
        GamesServiceClient gamesServiceClient = new GamesServiceClient();

        Game game = gamesServiceClient.getGame();

        updateUi(new BoardFetchedEvent(game.getBoard()));
    }
}
