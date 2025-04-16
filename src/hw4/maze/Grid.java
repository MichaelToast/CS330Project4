package hw4.maze;

import java.util.ArrayList;

public class Grid {
	ArrayList<Row> rows;

	public Grid(ArrayList<Row> rowList) {
		this.rows = rowList;
	}
	
	public ArrayList<Row> getRows() {
		return rows;
	}

	public void setRows(ArrayList<Row> rows) {
		this.rows = rows;
	}

	public String toString() {
		return "Grid [rows=" + rows + "]";
	}
	

}
