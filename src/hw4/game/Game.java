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

    public Grid createRandomGrid(int size) {
        if(size < 3 || size > 7){
            return null;
        }
        
        Random rand = new Random();

        Cell[][] cells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell(null, null, null, null);
            }
        }
        
        int exitRow = rand.nextInt(size);
        cells[exitRow][0].setLeft(CellComponents.EXIT);

        for(int i = 0; i < size; i++) {
            for(int j = 1; j < size; j++) {
                Cell currCell = cells[i][j];

                if(i > 0) {
                    Cell above = cells[i - 1][j];
                    currCell.setUp(above.getDown());
                }
                else{
                    currCell.setUp(randomComponent(rand));
                }

                if(j > 0){
                    Cell left = cells[i][j - 1];
                    currCell.setLeft(left.getRight());
                }
                else if(i != exitRow){
                    currCell.setLeft(randomComponent(rand));
                }

                if(j < size - 1){
                    currCell.setRight(randomComponent(rand));
                }

                if(i < size - 1){
                    currCell.setDown(randomComponent(rand));
                }

                if(!hasAtLeastOneAperture(currCell)){
                    currCell.setRight(CellComponents.APERTURE);
                }
            }
        }

        ArrayList<Row> rows = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ArrayList<Cell> cellsInRow = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                cellsInRow.add(cells[i][j]);
            }
            rows.add(new Row(cellsInRow));
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
