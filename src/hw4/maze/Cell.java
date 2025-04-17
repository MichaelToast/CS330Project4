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
		
		if (left == null) {
			this.left = CellComponents.WALL; 
		}
	}


	public CellComponents getRight() {
		return right;
	}


	public void setRight(CellComponents right) {
		this.right = right;
		
		if (right == null) {
			this.right = CellComponents.WALL; 
		}
	}


	public CellComponents getUp() {
		return up;
	}


	public void setUp(CellComponents up) {
		this.up = up;
		
		if (up == null) {
			this.up = CellComponents.WALL; 
		}
	}


	public CellComponents getDown() {
		return down;
	}


	public void setDown(CellComponents down) {
		this.down = down;
		
		if (down == null) {
			this.down = CellComponents.WALL; 
		}
	}


	@Override
	public String toString() {
		return "Cell [left=" + left + ", right=" + right + ", up=" + up + ", down=" + down + "]";
	}
	
}
