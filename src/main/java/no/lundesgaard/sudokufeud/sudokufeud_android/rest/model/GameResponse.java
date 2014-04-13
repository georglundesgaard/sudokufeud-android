package no.lundesgaard.sudokufeud.sudokufeud_android.rest.model;

public class GameResponse {

    private String response;

	public GameResponse(String response) {
		this.response = response;
	}

	public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
