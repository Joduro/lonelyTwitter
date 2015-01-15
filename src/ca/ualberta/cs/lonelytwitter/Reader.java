package ca.ualberta.cs.lonelytwitter;

public class Reader extends User {

	@Override //think you are overriding something
	public void setUsername(String username) {
		if (username.length() <= 8) {
			throw new RuntimeException("Name too short!");
		}
		this.username = username;
	}
}
