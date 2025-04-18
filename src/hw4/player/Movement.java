package hw4.player;

import hw4.maze.Cell;
import hw4.maze.CellComponents;
import hw4.maze.Row;

public class Movement {
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    
    /**
     * Moves player in the specified direction if possible
     * @param player The player to move
     * @param direction The direction to move (UP, RIGHT, DOWN, LEFT)
     * @return true if movement was successful, false otherwise
     */
    public static boolean move(Player player, int direction) {
        Row currentRow = player.getCurrentRow();
        Cell currentCell = player.getCurrentCell();
        
        int rowIndex = -1;
        int cellIndex = -1;
        
        for (int i = 0; i < currentRow.getGrid().getRows().size(); i++) {
            Row row = currentRow.getGrid().getRows().get(i);
            if (row == currentRow) {
                rowIndex = i;
                break;
            }
        }
        
        for (int i = 0; i < currentRow.getCells().size(); i++) {
            Cell cell = currentRow.getCells().get(i);
            if (cell == currentCell) {
                cellIndex = i;
                break;
            }
        }
        
        if (rowIndex == -1 || cellIndex == -1) {
            return false;
        }
        
        switch (direction) {
            case UP:
                if (currentCell.getTop() == CellComponents.APERTURE && rowIndex > 0) {
                    player.setCurrentRow(currentRow.getGrid().getRows().get(rowIndex - 1));
                    player.setCurrentCell(player.getCurrentRow().getCells().get(cellIndex));
                    return true;
                }
                break;
            case RIGHT:
                if (currentCell.getRight() == CellComponents.APERTURE && cellIndex < currentRow.getCells().size() - 1) {
                    player.setCurrentCell(currentRow.getCells().get(cellIndex + 1));
                    return true;
                }
                break;
            case DOWN:
                if (currentCell.getBottom() == CellComponents.APERTURE && rowIndex < currentRow.getGrid().getRows().size() - 1) {
                    player.setCurrentRow(currentRow.getGrid().getRows().get(rowIndex + 1));
                    player.setCurrentCell(player.getCurrentRow().getCells().get(cellIndex));
                    return true;
                }
                break;
            case LEFT:
                if (currentCell.getLeft() == CellComponents.APERTURE && cellIndex > 0) {
                    player.setCurrentCell(currentRow.getCells().get(cellIndex - 1));
                    return true;
                } else if (currentCell.getLeft() == CellComponents.EXIT && cellIndex == 0) {
                    return true;
                }
                break;
        }
        
        return false;
    }
}