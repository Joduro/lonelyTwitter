package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;

// abstract only allows the creation  of objects of its subclasses

public abstract class User implements UserLike{ //extends class Object is implied 

	// no access modifier = can be accessed anywhere within the same package
	protected String username;
	
	protected RelationShips relations;

	public String getUsername() {
		return username;
	}

	// all sub objects have their own versions of this method
	public abstract void setUsername(String username) throws IOException; 
	
	public User(String username) {
		super();
		this.username = username;
	}
	
	public User() {
		super();
		this.username = "anonymous";
	}
	 
}
