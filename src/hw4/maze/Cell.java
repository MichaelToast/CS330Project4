package hw4.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.provider.Arguments;

public class Cell {
	CellComponents exit;
	CellComponents aperture;
	CellComponents wall;
	CellComponents wall2;
	

	// Constructor needs to follow: CellComponents.WALL, CellComponents.EXIT, CellComponents.APERTURE, CellComponents.WALL
	public Cell(CellComponents wall, CellComponents exit, CellComponents aperture, CellComponents wall2) {
		this.exit = exit;
		this.aperture = aperture;
		this.wall = wall;
		this.wall2 = wall2;
	}
	
	// Need methods of: getLeft(), getRight(), getUp(), getDown()
	
	// To string needs to result in: assertEquals("Cell [left=" + cell.getLeft() + ", right=" + cell.getRight() + ", up=" + cell.getUp() + ", down=" + cell.getDown() + "]", cell.toString());

	
}
