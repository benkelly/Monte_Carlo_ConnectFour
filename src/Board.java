import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ben on 13/11/2017.
 */
public class Board extends ArrayList<List<Cell>> implements Serializable {
	private static Board instance;

	public Board deepClone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Board) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}



	public static synchronized Board getInstance() {
		if (instance == null) { instance = new Board();}
		return instance;
	}



	static  int MAX_COSTOM_COL = 11;
	static  int DO_NOT_COUNT_CELLS = 2;

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

		boolean ifDuplicates = false;
		while (!ifDuplicates) {
			int duplicateDiff = list.size();
			Set<Integer> hs = new HashSet<>();
			hs.addAll(list);
			list.clear();
			list.addAll(hs);
			duplicateDiff = duplicateDiff - list.size();
			if (duplicateDiff > 0) {
				for (int i = 0; i < duplicateDiff; i++) {
					list.add(0 + (int) (Math.random() * tottal));
				}
			} else {
				ifDuplicates = true;
			}
		}
		return  list;
	}


	public void createBoard(Integer[] columnHieght) {
		ArrayList<Integer> NonExistentList = getRandomCells(columnHieght, DO_NOT_COUNT_CELLS);
		//ArrayList<Integer> NonExistentList = getRandomCells(columnHieght, 0);

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
