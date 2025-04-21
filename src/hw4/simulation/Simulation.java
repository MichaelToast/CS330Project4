package hw4.simulation;

import hw4.game.Game;
import hw4.maze.Grid;
import hw4.maze.Row;
import hw4.maze.Cell;
import hw4.player.Player;
import hw4.player.Movement;

public class Simulation {

    public static void main(String[] args) {
        // Create a new game with a 5x5 grid
        Game game = new Game(5);
        Grid grid = game.getGrid();

        if (grid == null) {
            System.out.println("Invalid grid generated. Exiting.");
            return;
        }

        // Assume agent starts in bottom-right corner
        Row startRow = grid.getRows().get(4);
        Cell startCell = startRow.getCells().get(4);
        Player player = new Player(startRow, startCell);

        System.out.println("Initial Grid:");
        System.out.println(game);
        System.out.println();

        // Simulate a few moves
        Movement[] moves = {
            Movement.UP,
            Movement.LEFT,
            Movement.UP,
            Movement.LEFT,
            Movement.DOWN
        };

        for (Movement move : moves) {
            boolean success = game.play(move, player);
            System.out.println("Move: " + move + " | Success: " + success);
            System.out.println("Player now at: " + player.getCurrentCell());
            System.out.println();
        }

        System.out.println("Simulation finished.");
    }
}