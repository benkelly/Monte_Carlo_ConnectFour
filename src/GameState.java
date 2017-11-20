import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ben on 14/11/2017.
 */
public class GameState extends ArrayList<Player> implements Serializable {

	boolean gameOver;

	public GameState() {

	}
	private void getPlayers(){
		this.add(new Player("ben", true));
		this.add(new AI_Player("nessaBOT", false, true));
	}

	private void gameLoop(){
		while (!gameOver){
			for(Player curPlayer : this) {
				Cell c = null;
				while (c==null){
					c = curPlayer.getMove();
				}
				if(checkBoard(curPlayer, c)) {
					System.out.println("\n\n"+curPlayer.playerName+": YOU WIN!!!");
					System.out.println(Board.getInstance().printBoard()+"\n\n");
					gameOver=true;
					break;
				}
				if(isBoardFull()) {
					System.out.println("BOARD IS FULL GAME OVER!");
					gameOver=true;
					break;
				}
				System.out.println(Board.getInstance().printBoard());
			}
		}
	}

	public static boolean isBoardFull() {
		return isBoardFull(Board.getInstance());
	}

	public static boolean isBoardFull(Board board) {
		for(List<Cell> col : board) {
			for (Cell c : col) {
				if(!c.isCellOccupied()) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean checkBoard(Player player, Cell c) {
		return  checkBoard( Board.getInstance(), player, c);
	}


	public static boolean checkBoard(Board board, Player player, Cell c) {
		int column = 0;
		int height = 0;

		for (int i = 0; i < board.size(); i++) {
			if (board.get(i).indexOf(c) != -1) {
				//System.out.print("col: " + i + " h: " + board.get(i).indexOf(c));
				column = i;
				height = board.get(i).indexOf(c);
			}
		}
		//check vertical
		for (int j = 0; j < board.get(column).size() - 3; j++) {
			if (board.get(column).get(j).getCellOwner() == player
					&& board.get(column).get(j + 1).getCellOwner() == player
					&& board.get(column).get(j + 2).getCellOwner() == player
					&& board.get(column).get(j + 3).getCellOwner() == player) {
				return true;
			}
		}
		//check harazontal
		for (int i = 0; i < board.size() - 3; i++) {
			if (board.get(i).size() > height
					&& board.get(i + 1).size() > height
					&& board.get(i + 2).size() > height
					&& board.get(i + 3).size() > height) {
				if (board.get(i).get(height).getCellOwner() == player
						&& board.get(i + 1).get(height).getCellOwner() == player
						&& board.get(i + 2).get(height).getCellOwner() == player
						&& board.get(i + 3).get(height).getCellOwner() == player) {
					return true;
				}
			}
		}
		//check daignoals -->
		if (column > 0) {
			if (board.get(column).size() < board.get(column - 1).size()) {
				if (player == board.get(column - 1).get(height + 1).getCellOwner()) {
					if (column > 1) {
						if (board.get(column - 1).size() < board.get(column - 2).size()) {
							if (player == board.get(column - 2).get(height + 2).getCellOwner()) {
								if (column > 2) {
									if (board.get(column - 2).size()
											< board.get(column - 3).size()) {
										if (player == board.get(column-3).get(height+3).getCellOwner()) {
											return true;
										}
									}
									if (height-1 < board.get(column + 1).size()) {
										if (height - 1 >= 0) {
											if (player == board.get(column + 1).get(height - 1).getCellOwner()) {
												return true;
											}
										}
									}
								}
							}
						}
					}
					if (column+2 < board.size()) {
						if (height-1>0 && board.get(column+1).size()>height-1) {
							if (player == board.get(column+1).get(height-1).getCellOwner()){

								if (board.get(column + 1).size() >= board.get(column + 2).size()) {
									if (height - 2 >= 0 && board.get(column+2).size()>height-2) {
										if (player == board.get(column + 2).get(height - 2).getCellOwner()) {
											return true;
										}
									}
								}

								if (column > 0) {
									if (board.get(column).size()
											< board.get(column - 1).size()) {
										if (player == board.get(column - 1).get(height + 1).getCellOwner()) {
											if (column - 1 > 0) {
												if (board.get(column - 1).size()
														< board.get(column - 2).size()) {
													if (player == board.get(column - 2).get
															(height + 2).getCellOwner()) {
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
				}
			}
		}
		if (column+1 < board.size()) {
			if (height <= board.get(column+1).size()) {
				if (height> 0) {
					if (player == board.get(column+1).get(height-1).getCellOwner()) {
						if (column+2 < board.size()) {
							if (height-1 <= board.get(column+2).size()) {
								if (height - 2 >= 0) {
									if (player == board.get(column + 2).get(height - 2).getCellOwner()) {
										if (column+3 < board.size()) {
											if (height-2 <= board.get(column + 3).size()) {
												if (height - 3 >= 0) {
													if (player
															== board.get(column + 3).get
															(height - 3)
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
				}
			}
		}


		//check daignoals <--
		if (column < board.size()-1) {
			if (height < board.get(column + 1).size() - 1) {
				if (player == board.get(column + 1).get(height + 1).getCellOwner()) {
					if (column - 1 < board.size()) {
						if (height + 1 < board.get(column + 2).size() - 1) {
							if (player == board.get(column + 2).get(height + 2).getCellOwner()) {

								if (column - 2 < board.size()) {
									if (height+2 < board.get(column+3).size() - 1) {
										if (player==board.get(column+3).get(height+3).getCellOwner()) {
											return true;
										}
									}
									if (column > 0 && height - 1 >= 0) {
										if (player == board.get(column - 1).get
												(height - 1)
												.getCellOwner()) {
											return true;
										}
									}
								}
							}
						}
					}
					if (column > 0 && height - 1 > 0) {
						if (height - 1 <= board.get(column - 1).size() - 1) {
							if (player == board.get(column - 1).get(height - 1).getCellOwner()) {
								if (column-1 > 0 && height-2>= 0) {
									if (height-2 <= board.get(column-2).size()) {
										if (player == board.get(column - 2).get
												(height-2).getCellOwner()) {
										}
										return true;
									}
								}
							}
						}
					}
					if (column > 0 && height - 1 >= 0) {
						if (player
								== board.get(column - 1).get(height - 1)
								.getCellOwner()) {
							if (column - 1 > 0 && height - 2 >= 0) {
								if (player
										== board.get(column - 2).get(height - 2)
										.getCellOwner()) {
									if (column - 2 > 0 && height - 3 >= 0) {
										if (player
												== board.get(column - 2).get(height - 2)
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

			if (column < board.size() && column > 0) {
				if (board.get(column).size() - 1 == board.get(column - 1).size
						()) {
					if (height - 1 >= 0) {

						if (player == board.get(column - 1).get(height - 1).getCellOwner()) {
							if (column - 1 > 0) {
								if (board.get(column - 1).size() - 1 == board
										.get(column - 2).size()) {
									if (height - 2 >= 0) {
										if (player
												== board.get(column - 2).get(height - 2)
												.getCellOwner()) {
										}
										if (column - 2 > 0) {
											if (board.get(column - 2).size() - 1 == Board
													.getInstance().get(column - 3).size()) {
												if (height - 3 >= 0) {
													if (player
															== board.get(column - 3).get
															(height - 3)
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
				}
			}
		}
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
