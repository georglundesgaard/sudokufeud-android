package no.lundesgaard.sudokufeud.sudokufeud_android.rest.model;

public class NewProfile {

    private String userId;
    private String name;
	private String password;

	public NewProfile(String userId, String name, String password) {
		this.userId = userId;
		this.name = name;
		this.password = password;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
