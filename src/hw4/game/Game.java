package hw4.game;

import hw4.maze.Grid;
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

    public boolean play(Movement movement) {

    }

    
}
