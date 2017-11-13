import java.util.ArrayList;
import java.util.List;

/**
 * Created by ben on 13/11/2017.
 */
public class Board extends ArrayList<List<Cell>> {

	public

	int ogColumns = 7;
	int ogHeight =  6;

	int maxColumns =  6;
	int maxHeight =  6;


	public Board() {

	}

	public void createBoard(Integer[] columnHieght) {
		if(columnHieght.length> maxColumns) maxColumns = columnHieght.length;

		for (int i = 0; i < columnHieght.length; i++) {
			if(columnHieght[i]>maxHeight) maxHeight = columnHieght[i];
			ArrayList<Cell> curColumn = new ArrayList<Cell>();
				for (int j = 0; j < columnHieght[i]; j++) {
					curColumn.add(new Cell());
				}
			this.add(curColumn);
		}
		if(this.size()<ogHeight){
			int remainderCol = ogHeight - this.size();
			for (int i = 0; i < remainderCol; i++) {
				ArrayList<Cell> curColumn = new ArrayList<Cell>();
				for (int j = 0; j < ogHeight; j++) {
					curColumn.add(new Cell());
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
		return str;
	}

	// class test
	public static void main(String[] args) {
		System.out.println("Board!");


		Board board = new Board();
		Integer[] hieghts = {8, 6, 5, 4, 7};

		board.createBoard(hieghts);

		//System.out.println(board);
		//System.out.println(board.get(0).get(7));
		System.out.println(board.printBoard());

	}


}
