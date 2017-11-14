import java.util.ArrayList;

/**
 * Created by ben on 14/11/2017.
 */
public class GameState extends ArrayList<Player>{

	boolean gameOver;

	public GameState() {

	}
	private void getPlayers(){
		this.add(new Player("ben", 0));
		this.add(new Player("nessa", 1));
	}

	private void gameLoop(){
		while (!gameOver){

			for(Player curPlayer : this) {


			}


		}
	}


	private void






}
