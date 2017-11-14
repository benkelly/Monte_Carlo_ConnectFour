/**
 * Created by ben on 14/11/2017.
 */
public class Player {

	String playerName = "";
	boolean isYellow;
	boolean isRed;
	int moveCount = 0;


	public Player(String name, boolean ifYellow) {
		playerName = name;
		isYellow = ifYellow;
		if(!ifYellow) isRed = true;
	}

	public String getColour() {
		if(isYellow) return "Yellow";
		if(isRed) return  "Red";
		return null;
	}

	public String toString() {
		return playerName+": colour: "+getColour()+", moves: "+moveCount;
	}




}
