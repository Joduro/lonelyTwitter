package ca.ualberta.cs.lonelytwitter;

public class Friends extends RelationShips {
	
	@Override
	protected void addRelation(String username){
		ppl.add(username + " is a friend");
	}
}
