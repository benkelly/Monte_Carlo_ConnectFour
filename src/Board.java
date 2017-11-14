import java.util.ArrayList;
import java.util.List;

/**
 * Created by ben on 13/11/2017.
 */
public class Board extends ArrayList<List<Cell>> {

	static  int MAX_COSTOM_COL = 11;

	int ogColumns = 7;
	int ogHeight =  6;

	int maxColumns =  6;
	int maxHeight =  6;

	int cellCount = 0;


	public Board() {

	}

	private ArrayList<Integer> getRandomCells(Integer[] columnHieght, int amount) {
		int tottal = 0;
		int curCols = columnHieght.length;
		if(columnHieght.length> MAX_COSTOM_COL) {
			curCols = MAX_COSTOM_COL;
		}

		for (int i = 0; i < curCols; i++) {
			tottal+=columnHieght[i];
		}
		if(columnHieght.length < ogColumns) {
			int diffCols = ogColumns - columnHieght.length;
			for (int i = 0; i < diffCols; i++) {
				tottal+=6;
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < amount; i++) {
			list.add(0 + (int)(Math.random() * tottal));
		}
		return  list;
	}


	public void createBoard(Integer[] columnHieght) {
		ArrayList<Integer> NonExistentList = getRandomCells(columnHieght, 2);

		if(columnHieght.length > maxColumns) {
			if(columnHieght.length > MAX_COSTOM_COL) {
				maxColumns = MAX_COSTOM_COL;
			}
			else
				maxColumns = columnHieght.length;
		}

		for (int i = 0; i < maxColumns; i++) {
			if(columnHieght[i]>maxHeight) maxHeight = columnHieght[i];
			ArrayList<Cell> curColumn = new ArrayList<Cell>();
				for (int j = 0; j < columnHieght[i]; j++) {
					if(NonExistentList.contains(cellCount)) {
						Cell cell = new Cell();
						cell.setNonExistent(true);
						curColumn.add(cell);
					}
					else
						curColumn.add(new Cell());
					cellCount++;
				}
			this.add(curColumn);
		}
		if(this.size()<ogHeight){
			int remainderCol = ogHeight - this.size();
			for (int i = 0; i < remainderCol; i++) {
				ArrayList<Cell> curColumn = new ArrayList<Cell>();
				for (int j = 0; j < ogHeight; j++) {
					if(NonExistentList.contains(cellCount)) {
						Cell cell = new Cell();
						cell.setNonExistent(true);
						curColumn.add(cell);
					}
					else {
						curColumn.add(new Cell());
					}
					cellCount++;
				}
				this.add(curColumn);
			}
		}
	}

	public String OGprintBoard() {
		String str = "";
		for(List<Cell> cell : this) {
			str += cell.toString()+"\n";
		}
		return str;
	}

	public String printBoard() {
		String str = "";

		for (int i = maxHeight-1; i >= 0; --i) {
			str += (i+1)+":\t";
			for (int j = 0; j < this.size(); j++) {
				//if(this.get(j).get(i) != null){
				if(this.get(j).size() > i ) {
					str += this.get(j).get(i).toString()+" ";
				}
				else {
					str += "\t";
				}
			}
			str += "\n";
		}
		str += "\t ";
		for (int i = 0; i <maxColumns; i++) {
			str += getCharForNumber(i+1)+" \t ";
		}
		return str;
	}
	private String getCharForNumber(int i) {
		return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
	}


	// class test
	public static void main(String[] args) {
		System.out.println("Board!");


		Board board = new Board();
		//Integer[] hieghts = {8, 6, 5, 4, 7};
		Integer[] hieghts = {8, 6, 5, 4, 7,6, 5,3,2,2,1,24, 3};

		board.createBoard(hieghts);

		//System.out.println(board);
		//System.out.println(board.get(0).get(7));
		System.out.println(board.printBoard());

	}



	}
