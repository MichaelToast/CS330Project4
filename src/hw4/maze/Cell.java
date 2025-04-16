package hw4.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.provider.Arguments;

public class Cell {
	CellComponents left;
	CellComponents right;
	CellComponents up;
	CellComponents down;
	

	// Constructor needs to follow: CellComponents.WALL, CellComponents.EXIT, CellComponents.APERTURE, CellComponents.WALL
	public Cell(CellComponents left, CellComponents right, CellComponents up, CellComponents down) {
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
	}


	public CellComponents getLeft() {
		return left;
	}


	public void setLeft(CellComponents left) {
		this.left = left;
	}


	public CellComponents getRight() {
		return right;
	}


	public void setRight(CellComponents right) {
		this.right = right;
	}


	public CellComponents getUp() {
		return up;
	}


	public void setUp(CellComponents up) {
		this.up = up;
	}


	public CellComponents getDown() {
		return down;
	}


	public void setDown(CellComponents down) {
		this.down = down;
	}


	@Override
	public String toString() {
		return "Cell [left=" + left + ", right=" + right + ", up=" + up + ", down=" + down + "]";
	}
	
}
