package hw4.game;

import java.util.ArrayList;
import java.util.Random;

import hw4.maze.Grid;
import hw4.maze.Row;
import hw4.maze.Cell;
import hw4.maze.CellComponents;
import hw4.player.Player;
import hw4.player.Movement;

public class Game {
    private Grid grid;
    /** 
     * Constructs a game using a randomly generated grid.
     * @param grid: The grid to use for this game.
    */
    public Game(Grid grid) {
        this.grid = grid;
    }
    /**
     * Constructs a game with a randomly generated grid of the given size.
     * If the size is invalid (<3 or >7), the grid will be null.
     *
     * @param size:
     * The size of the grid (must be between 3 and 7 inclusive).
     */
    public Game(int size){
        if (size >= 3 && size <= 7){
            this.grid = createRandomGrid(size);
        }
        else{
            this.grid = null;
        }
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    /**
     * Attempts to move the player in the specified direction.
     *
     * @param movement: The direction to move.
     * @param player:   The player to move.
     * @return true if the move was successful, false otherwise.
     */
    public  boolean play(Movement movement, Player player) {
        if (movement == null || player  == null) {
            return false;
        }
        switch(movement){
            case UP: return moveUp(player);
            case RIGHT: return moveRight(player);
            case DOWN: return moveDown(player);
            case LEFT: return moveLeft(player);
            default: return false;
        }
    }

    //Helper functions for movement
    private boolean moveUp(Player player) {
        int rowIndex = getRowIndex(player);
        int columnIndex = getColumnIndex(player);
        if(rowIndex > 0 && player.getCurrentCell().getUp() == CellComponents.APERTURE){
            updatePosition(player, rowIndex - 1, columnIndex);
            return true;
        }
        return false;
    }

    private boolean moveRight(Player player) {
        int rowIndex = getRowIndex(player);
        int columnIndex = getColumnIndex(player);
        if(columnIndex < grid.getRows().get(0).getCells().size() - 1 && player.getCurrentCell().getRight() == CellComponents.APERTURE){
            updatePosition(player, rowIndex, columnIndex + 1);
            return true;
        }
        return false;
    }

    private boolean moveDown(Player player) {
        int rowIndex = getRowIndex(player);
        int columnIndex = getColumnIndex(player);
        if(rowIndex < grid.getRows().size() - 1 && player.getCurrentCell().getDown() == CellComponents.APERTURE){
            updatePosition(player, rowIndex + 1, columnIndex);
            return true;
        }
        return false;
    }

    private boolean moveLeft(Player player) {
        int rowIndex = getRowIndex(player);
        int columnIndex = getColumnIndex(player);
    
        if (columnIndex > 0 && player.getCurrentCell().getLeft() == CellComponents.APERTURE) {
            updatePosition(player, rowIndex, columnIndex - 1);
            return true;
        }
    
        if (columnIndex == 0 && player.getCurrentCell().getLeft() == CellComponents.EXIT) {
            return true;
        }
    
        return false;
    }
    

    private int getRowIndex(Player player) {
        return  grid.getRows().indexOf(player.getCurrentRow());
    }

    private int getColumnIndex(Player player) {
        return player.getCurrentRow().getCells().indexOf(player.getCurrentCell());        
    }

    private void updatePosition(Player player, int row, int column){
        Row currRow = grid.getRows().get(row);
        player.setCurrentRow(currRow);
        player.setCurrentCell(currRow.getCells().get(column));
    }

    /**
     * Generates a valid random grid of the given size.
     * Ensures exactly one EXIT and valid adjacent components.
     *
     * @param size: The grid size (between 3 and 7 inclusive).
     * @return A valid grid if size is valid, otherwise null.
     */
    public Grid createRandomGrid(int size) {
	    if (size < 3 || size > 7) return null;
	    
	    Cell[][] cells = initializeEmptyCells(size);
	    
	    
	    populateCellComponents(cells);
	    placeExit(cells);
	    //ensureEachCellHasAperture(cells);
	    Grid g = convertToGrid(cells);

	    return g;
}

    /**
     * Initializes a 2D array of Cells with the given size.
     * Each cell is initialized with all null cell components.
     * @param size: The size of the grid
     * @return A 2D array of Cells, initialized with all null components
     */
    private Cell[][] initializeEmptyCells(int size) {
	    Cell[][] cells = new Cell[size][size];
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            cells[i][j] = new Cell(null, null, null, null);
	        }
	    }
	    return cells;
    }
	
	private void placeExit(Cell[][] cells) {
	    Random rand = new Random();
	    int exitRow = rand.nextInt(cells.length);
	    cells[exitRow][0].setLeft(CellComponents.EXIT);
	}
	
	private void populateCellComponents(Cell[][] cells) {
	    Random rand = new Random();
	    int size = cells.length;
	    
	    // Iterate through each cell in the grid and set components
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            Cell current = cells[i][j];

	            
	            // Assign Down component
	            if (i < size - 1) {
	                // If I am not on the last one, make the down component the up of what is under me
	                CellComponents down = randomComponent(rand);
	                current.setDown(down);
	                //cells[i + 1][j].setUp(down);
	            }
	            
	            // Assign Up component
	            if (i > 0) {
	            	// We are not on the first row, so we can go up
	                current.setUp(cells[i - 1][j].getDown());
	            }
	            else {
	            	current.setUp(randomComponent(rand));
	            }
	            

	            if (j < size) {
	            	//changed from size - 1
	                // If we have room to the right, set a random value. 
	                current.setRight(randomComponent(rand));
	            }
	            
	            if (j > 0) {
	            	// Not on the left most column, set my left to my neighboors right
	                current.setLeft(cells[i][j - 1].getRight());
	            }
	            else {
	            	// Are on the left most, give a wall
	            	current.setLeft(CellComponents.WALL);
	            }
	            
	            

	        }
	    }
	}


    
	
	private void ensureEachCellHasAperture(Cell[][] cells) {
	    for (int i = 0; i < cells.length; i++) {
	        for (int j = 0; j < cells[i].length; j++) {
	            Cell cell = cells[i][j];
	            if (!hasAtLeastOneAperture(cell)) {
	                if (j < cells[i].length - 1) {
	                    cell.setRight(CellComponents.APERTURE);
	                } else {
	                    cell.setLeft(CellComponents.APERTURE);
	                }
	            }
	        }
	    }
	}

	private Grid convertToGrid(Cell[][] cells) {
	    ArrayList<Row> rows = new ArrayList<>();
	    for (int i = 0; i < cells.length; i++) {
	        ArrayList<Cell> rowCells = new ArrayList<>();
	        for (int j = 0; j < cells[i].length; j++) {
	            rowCells.add(cells[i][j]);
	        }
	        rows.add(new Row(rowCells));
	    }
	    return new Grid(rows);
	}


	private CellComponents randomComponent(Random rand) {
	    return rand.nextBoolean() ? CellComponents.APERTURE : CellComponents.WALL;
	}
	    
	private boolean hasAtLeastOneAperture(Cell cell) {
	    return cell.getLeft() == CellComponents.APERTURE ||
	            cell.getRight() == CellComponents.APERTURE ||
	            cell.getUp() == CellComponents.APERTURE ||
	            cell.getDown() == CellComponents.APERTURE;
	}
	
	@Override
	public String toString() {
		return "Game [grid=" + grid + "]";
	}
	
	

}