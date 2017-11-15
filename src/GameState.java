import java.util.ArrayList;

/**
 * Created by ben on 14/11/2017.
 */
public class GameState extends ArrayList<Player>{

	boolean gameOver;

	public GameState() {

	}
	private void getPlayers(){
		this.add(new Player("ben", true));
		this.add(new Player("nessa", false));
	}

	private void gameLoop(){
		while (!gameOver){
			System.out.println("in while loop!");
			for(Player curPlayer : this) {
				Cell c = null;
				while (c==null){
					c = curPlayer.getMove();
				}
				if(checkBoard(curPlayer, c)) {
					System.out.println(curPlayer.playerName+": YOU WIN!!!");
					gameOver=true;
					break;
				}
				System.out.println(Board.getInstance().printBoard());
			}
		}
	}


	private boolean checkBoard(Player player, Cell c) {
		int column = 0;
		int height = 0;

		for (int i = 0; i < Board.getInstance().size(); i++) {
			if (Board.getInstance().get(i).indexOf(c) != -1) {
				System.out.print("col: " + i + " h: " + Board.getInstance().get(i).indexOf(c));
				column = i;
				height = Board.getInstance().get(i).indexOf(c);
			}
		}
		//check vertical
		for (int j = 0; j < Board.getInstance().get(column).size() - 3; j++) {
			if (Board.getInstance().get(column).get(j).getCellOwner() == player
					&& Board.getInstance().get(column).get(j + 1).getCellOwner() == player
					&& Board.getInstance().get(column).get(j + 2).getCellOwner() == player
					&& Board.getInstance().get(column).get(j + 3).getCellOwner() == player) {
				return true;
			}
		}
		//check harazontal
		for (int i = 0; i < Board.getInstance().size() - 3; i++) {
			if (Board.getInstance().get(i).size() > height
					&& Board.getInstance().get(i + 1).size() > height
					&& Board.getInstance().get(i + 2).size() > height
					&& Board.getInstance().get(i + 3).size() > height) {
				if (Board.getInstance().get(i).get(height).getCellOwner() == player
						&& Board.getInstance().get(i + 1).get(height).getCellOwner() == player
						&& Board.getInstance().get(i + 2).get(height).getCellOwner() == player
						&& Board.getInstance().get(i + 3).get(height).getCellOwner() == player) {
					return true;
				}
			}
		}
		//check daignoals
		if(column > 0) {
			if (Board.getInstance().get(column).size() < Board.getInstance().get(column - 1).size()) {
				if (player == Board.getInstance().get(column - 1).get(height + 1).getCellOwner()) {

					if(column > 1) {
						if (Board.getInstance().get(column - 1).size() < Board.getInstance().get(column - 2).size()) {
							if (player == Board.getInstance().get(column - 2).get(height + 2).getCellOwner()) {
								if(column > 2) {
									if (Board.getInstance().get(column - 2).size()
											< Board.getInstance().get(column - 3).size()) {
										if (player == Board.getInstance().get(column - 2).get(height + 2).getCellOwner()) {
											return true;
										}
									}
									if (Board.getInstance().get(column).size()
											>= Board.getInstance().get(column + 1).size()) {
										if (height - 1 >= 0) {
											if (player == Board.getInstance().get(column + 1).get(height - 1)
													.getCellOwner()) {
												return true;
											}
										}
									}
								}
							}
						}
					}
					if (Board.getInstance().get(column).size()
							>= Board.getInstance().get(column + 1).size()) {
						if (height - 1 >= 0) {
							if (player
									== Board.getInstance().get(column + 1).get(height - 1).getCellOwner()) {
								if (Board.getInstance().get(column + 1).size()
										>= Board.getInstance().get(column + 2).size()) {
									if (height - 2 >= 0) {
										if (player
												== Board.getInstance().get(column + 2).get(height - 2)
												.getCellOwner()) {
											return true;
										}
									}
								}

							}
						}

					}
				}
			}
		}
		if (Board.getInstance().get(column).size()
				>= Board.getInstance().get(column+1).size()) {
			if(height-1>=0) {
				if (player
						== Board.getInstance().get(column+1).get(height-1).getCellOwner()) {
					if (Board.getInstance().get(column+1).size()
							>= Board.getInstance().get(column+2).size()) {
						if(height-2>=0) {
							if (player
									== Board.getInstance().get(column+2).get(height-2)
									.getCellOwner()) {
							}
							if (Board.getInstance().get(column+2).size()
									>= Board.getInstance().get(column+3).size()) {
								if(height-3>=0) {
									if (player
											== Board.getInstance().get(column+3).get
											(height-3)
											.getCellOwner()) {
										return true;
									}
								}
							}
						}
					}
				}

			}
		}

/*		for (int i=3; i<Board.getInstance().size(); i++){
			*//*if(Board.getInstance().get(i).size() > height
					&& Board.getInstance().get(i-1).size() > height
					&& Board.getInstance().get(i-2).size() > height
					&& Board.getInstance().get(i-3).size() > height) {*//*
				for (int j=0; j<Board.getInstance().get(i).size()-3; j++){

					if (Board.getInstance().get(i).get(j).getCellOwner() == player && Board
							.getInstance().get(i-1).get(j+1).getCellOwner() == player && Board
							.getInstance().get(i-2).get(j+2).getCellOwner() == player && Board
							.getInstance().get(i-3).get(j+3).getCellOwner() == player){
						return true;
					}
				//}
			}
		}
		for (int i=3; i<Board.getInstance().size(); i++){
			*//*if(Board.getInstance().get(i).size() > height
					&& Board.getInstance().get(i-1).size() > height
					&& Board.getInstance().get(i-2).size() > height
					&& Board.getInstance().get(i-3).size() > height) {*//*
				for (int j=3; j<Board.getInstance().get(i).size()-3; j++){
					if (Board.getInstance().get(i).get(j).getCellOwner() == player && Board
							.getInstance().get(i-1).get(j-1).getCellOwner() == player && Board
							.getInstance().get(i-2).get(j-2).getCellOwner() == player && Board
							.getInstance().get(i-3).get(j-3).getCellOwner() == player){
						return true;
					}
				//}
			}
		}*/
		return false;
	}


	public static void main(String[] args) {
		System.out.println("Board!");

		//Board board = new Board();
		//Integer[] hieghts = {8, 6, 5, 4, 7};
		Integer[] hieghts = {8, 6, 5, 4, 7, 6, 5, 3, 2, 2, 1, 24, 3};

		Board.getInstance().createBoard(hieghts);
		System.out.println("Board.getInstance().createBoard!");

		GameState gameState = new GameState();
		gameState.getPlayers();
		System.out.println("gameState.getPlayers!");


		gameState.gameLoop();
		System.out.println("gameState.gameLoop!");


	}



}
