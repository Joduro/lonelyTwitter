package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;

// allows specifying thins that you want others to have. Like abstract class except everything needs to be abstract. Cannot put code in methods 

public interface UserLike {

	public void setUsername(String username) throws IOException;
	
}
