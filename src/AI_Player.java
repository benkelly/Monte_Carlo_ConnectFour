import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ben on 14/11/2017.
 */
public class AI_Player extends Player {
	Random rand = new Random();


	public AI_Player(String name, boolean ifYellow, boolean isBot) {
		super(name, ifYellow);
	}

	public Cell getMove() {
		int column = suggestMonteCarlo();

		if (column >= 0 & column < Board.getInstance().maxColumns) {
			int columnLevel = getColumnLevel(Board.getInstance().get(column));
			if (columnLevel < Board.getInstance().get(column).size()) {
				if (isYellow) {
					Board.getInstance().get(column).get(columnLevel).isYellow = true;
					Board.getInstance().get(column).get(columnLevel).setCellOwner(this);
				}
				if (isRed) {
					Board.getInstance().get(column).get(columnLevel).isRed = true;
					Board.getInstance().get(column).get(columnLevel).setCellOwner(this);
				}
				return Board.getInstance().get(column).get(columnLevel);
			}
		}
		return null;
	}

	public int suggestMonteCarlo() {
		int bestColumn = -1;
		double bestRatio = 0;
		//int TRAINING_GAMES = 10000;
		int TRAINING_GAMES = 1000;

		System.out.println(playerName+": TRAINING....");
		for (int move = 0; move < Board.getInstance().size(); move++) {

			if (getColumnLevel(Board.getInstance().get(move))>=Board.getInstance().get(move).size())
			{
				System.out.println("Column: "+move+" is Full!");
				//System.out.println(getColumnLevel(Board.getInstance().get(move)));
				continue; // if column is full skip training.
			}
			int won = 0, lost = 0;
			for (int i = 0; i < TRAINING_GAMES; i++) {
				// copies current board state for training.
				Board curBoard = Board.getInstance().deepClone();

				int columnLevel = getColumnLevel(curBoard.get(move));
				if (columnLevel < curBoard.get(move).size()) {
					if (isYellow) {
						curBoard.get(move).get(columnLevel).isYellow = true;
						curBoard.get(move).get(columnLevel).setCellOwner(this);
					}
					if (isRed) {
						curBoard.get(move).get(columnLevel).isRed = true;
						curBoard.get(move).get(columnLevel).setCellOwner(this);
					}
					// should play the move that wins immediately if there is one.
					if (GameState.checkBoard(curBoard, this, curBoard.get(move).get(columnLevel))) {
						System.out.println("\t\t\tWINNING MOVE RETURN");
						return move;
					}

					if (simulateGame(curBoard)) {
						won++;
					} else {
						lost++;
					}
				}
			}

			double ratio = (double) won / (lost + 1);
			System.out.println("Column: " + move + " ratio: " + ratio);

			if (ratio > bestRatio || bestColumn == -1) {
				bestRatio = ratio;
				if(ratio==0.0) {
					// if unable to suggest move choose random. to avoid
					// a stalemate looping situation.
					int ranMove = rand.nextInt(Board.getInstance().size());
					int colLevel = getColumnLevel(Board.getInstance().get(ranMove));
					if (colLevel < Board.getInstance().get(ranMove).size()) {
						bestColumn = ranMove;
					}
				}
				else {
					bestColumn = move;
				}
			}
		}
		System.out.println("\t\t\t\t\t\t\t\tBEST COLUMN: "+bestColumn);
		return bestColumn;
	}

	private boolean simulateGame(Board b) {
		AI_Player rBot = null;
		AI_Player yBot = null;
		boolean gameLoop = false;
		boolean isRorY = false;
		if (this.isRed) {
			isRorY = false;
			yBot = new AI_Player("Vulcan 3",true, true);
			rBot = this;
		}
		if (this.isYellow) {
			isRorY = true;
			rBot = new AI_Player("Vulcan 3",false, true);
			yBot = this;
		}

		while (!gameLoop) {
			int move = rand.nextInt(b.size());
			//System.out.println("MOVE: "+move);
			int columnLevel = getColumnLevel(b.get(move));
			//System.out.println("### columnLevel " + columnLevel);
			if (columnLevel < Board.getInstance().get(move).size()) {
				if (!isRorY) {
					b.get(move).get(columnLevel).isYellow = true;
					b.get(move).get(columnLevel).setCellOwner(yBot);
					isRorY = true;
					//System.out.println(b.printBoard()+"\nCOLUMN: "+move+", HEIGHT: "+columnLevel);
					if (GameState.checkBoard(b, yBot, b.get(move).get(columnLevel))) {
						//System.out.println("\n\n" + this.playerName + ": YOU WIN!!!");
						if(yBot == this) return true;
						else return false;
					}
				}else if (isRorY) {
					b.get(move).get(columnLevel).isRed = true;
					b.get(move).get(columnLevel).setCellOwner(rBot);
					isRorY = false;
					//System.out.println(b.printBoard()+"\nCOLUMN: "+move+", HEIGHT: "+columnLevel);
					if (GameState.checkBoard(b, rBot, b.get(move).get(columnLevel))) {
						//System.out.println("\n\n" + this.playerName + ": YOU WIN!!!");
						if(rBot == this) return true;
						else return false;
					}
				}
			}
			if (GameState.isBoardFull(b)) {
				//System.out.println("BOARD IS FULL GAME OVER!");
				break;
			}
		}
		return false;
	}


	// test class
	public static void main(String[] args) {
		System.out.println("AI_PLAYER!");
		//Board board = new Board();
		//Integer[] hieghts = {8, 6, 5, 4, 7};
		Integer[] hieghts = {8, 6, 5, 4, 7, 6, 5, 3, 2, 2, 1, 24, 3};

		Board.getInstance().createBoard(hieghts);
		System.out.println("Board.getInstance().createBoard!");

		AI_Player player = new AI_Player("Melcin",true,true);

		System.out.println(player.suggestMonteCarlo());

		System.out.println("OG BOARD\n\n"+Board.getInstance().printBoard());

	}

}

