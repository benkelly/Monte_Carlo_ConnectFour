import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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


	public Cell getMove() {
		int temp =0;
		boolean inPutLoop = false;
		while (!inPutLoop) {
			System.out.println(playerName + ": Your move! Please enter a column letter (e.g. A, B or C)");
			String inputStr = getConsoleInput();
			if(!inputStr.equals("")) {
				char[] ch = inputStr.toLowerCase().toCharArray();
				temp = (int) ch[0];
			}
			int temp_integer = 96; //for lower case ascii
			int curColumn = (temp - temp_integer) - 1;

			if (curColumn >= 0 & curColumn < Board.getInstance().maxColumns) {
				int columnLevel = getColumnLevel(Board.getInstance().get(curColumn));
				System.out.println("### columnLevel " + columnLevel);
				if (columnLevel < Board.getInstance().get(curColumn).size()) {
					if (isYellow) {
						Board.getInstance().get(curColumn).get(columnLevel).isYellow = true;
						Board.getInstance().get(curColumn).get(columnLevel).setCellOwner(this);
					}
					if (isRed) {
						Board.getInstance().get(curColumn).get(columnLevel).isRed = true;
						Board.getInstance().get(curColumn).get(columnLevel).setCellOwner(this);
					}
					return Board.getInstance().get(curColumn).get(columnLevel);
				} else
					System.out.println(playerName + "Column full! please select another one.");
				return null;
			}
			System.out.println(playerName + "Invalid Column!");
		}
		return null;
	}


	public int getColumnLevel(List<Cell> column) {
		int level = 0;
		for (Cell c : column){
			if(c.isCellOccupied()){
				level++;
			}
			else{
				break;
			}
		}
		return level;
	}


	public String getColour() {
		if(isYellow) return "Yellow";
		if(isRed) return  "Red";
		return null;
	}

	public String toString() {
		return playerName+": colour: "+getColour()+", moves: "+moveCount;
	}

	/*get keyboard input
	* */
	private String getConsoleInput() {
		String str= "";
		try{
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			str = bufferRead.readLine();
			System.out.println(str);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return str;
	}




}
