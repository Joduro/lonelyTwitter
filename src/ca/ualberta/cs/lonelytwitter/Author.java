package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;

public class Author extends User {
	
	@Override //think you are overriding something
	public void setUsername(String username) throws IOException{
		if (username.length() > 8) {
			throw new IOException("Name too long!");
		}
		this.username = username;
	}
	
	
}
