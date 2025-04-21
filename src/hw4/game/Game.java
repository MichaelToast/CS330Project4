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

    public Game(Grid grid) {
        this.grid = grid;
    }
    
    public Game(int size){
        if (size > 3 && size <= 7){
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
///////////////////////////////////////////////////
    public  boolean play(Player player, Movement movement) {
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

    private boolean moveUp(Player player) {
        int rowIndex = getRowIndex(player);
        int columnIndex = getColumnIndex(player);
        if(rowIndex > 0 && player..getCurrCell().getUp() == CellComponents.APERTURE){
            updatePosition(player, rowIndex - 1, columnIndex);
        }
    }

    private boolean moveRight(Player player) {
        int rowIndex = getRowIndex(player);
        int columnIndex = getColumnIndex(player);
        if(columnIndex < grid.getRows().get(0).getCells().size() - 1 && player..getCurrCell().getRight() == CellComponents.APERTURE){
            updatePosition(player, rowIndex, columnIndex + 1);
        }
    }

    private boolean moveDown(Player player) {
        int rowIndex = getRowIndex(player);
        int columnIndex = getColumnIndex(player);
        if(rowIndex < grid.getRows().size() - 1 && player..getCurrCell().getDown() == CellComponents.APERTURE){
            updatePosition(player, rowIndex + 1, columnIndex);
        }
    }

    private boolean moveLeft(Player player) {
        int rowIndex = getRowIndex(player);
        int columnIndex = getColumnIndex(player);
        if(columnIndex > 0 && player..getCurrCell().getLeft() == CellComponents.APERTURE){
            updatePosition(player, rowIndex, columnIndex - 1);
        }
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
///////////////////////////////////////////////////
public Grid createRandomGrid(int size) {
    if (size < 3 || size > 7) return null;

    Cell[][] cells = initializeEmptyCells(size);
    placeExit(cells);
    populateCellComponents(cells);
    ensureEachCellHasAperture(cells);

    return convertToGrid(cells);
}

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

    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            Cell current = cells[i][j];

            // UP
            if (i > 0) {
                current.setUp(cells[i - 1][j].getDown());
            } else {
                current.setUp(randomComponent(rand));
            }

            // LEFT
            if (j > 0) {
                current.setLeft(cells[i][j - 1].getRight());
            } else if (current.getLeft() != CellComponents.EXIT) {
                current.setLeft(randomComponent(rand));
            }

            // RIGHT
            if (j < size - 1) {
                current.setRight(randomComponent(rand));
            }

            // DOWN
            if (i < size - 1) {
                current.setDown(randomComponent(rand));
            }
        }
    }
}

private void ensureEachCellHasAperture(Cell[][] cells) {
    for (int i = 0; i < cells.length; i++) {
        for (int j = 0; j < cells[i].length; j++) {
            Cell cell = cells[i][j];
            if (!hasAtLeastOneAperture(cell)) {
                // Preferably adjust RIGHT if possible
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

}