import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ben on 13/11/2017.
 */
public class Cell implements Serializable {

	public Cell deepClone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Cell) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}





	boolean isNonExistent = false;
	boolean isYellow = false;
	boolean isRed = false;

	Player cellOwner;

	private static String[] strMarkers = {" ", "#", "Y", "R"};

	public Cell() {

	}

	public void setNonExistent(boolean bool) {
		isNonExistent = bool;
	}

	public boolean isCellOccupied() {
		if(isNonExistent) return true;
		if(isYellow) return true;
		if(isRed) return true;
		return false;
	}

	public String FancytoString() {
		String occupied;
		if(isNonExistent) occupied = strMarkers[1];
		else if(isYellow) occupied = strMarkers[2];
		else if(isRed) occupied = strMarkers[3];
		else occupied = strMarkers[0];

		String str = "";
		str +=" -------\n|\t \t|\n|\t"+occupied+"\t|\n|\t \t|\n -------";

		return str;
	}

	public String toString() {
		String occupied;
		if(isNonExistent) occupied = strMarkers[1];
		else if(isYellow) occupied = strMarkers[2];
		else if(isRed) occupied = strMarkers[3];
		else occupied = strMarkers[0];

		String str = "";
		str +="["+occupied+"]";

		return str;
	}

	public void setCellOwner(Player p) {
		cellOwner = p;
	}

	public Player getCellOwner() {
		return cellOwner;
	}

	// class test
	public static void main(String[] args) {
		System.out.println("Board!");
		Cell cell = new Cell();

		System.out.println(cell);
		cell.isNonExistent = true;
		System.out.println(cell);
		cell.isNonExistent = false;


		cell.isYellow = true;
		System.out.println(cell);
		cell.isYellow = false;

		cell.isRed = true;
		System.out.println(cell);

	}

}
