package hw4.maze;
public class Cell {
    private CellComponents left, right, up, down;

    public Cell(CellComponents left, CellComponents right, CellComponents up, CellComponents down) {
        this.left = (left != null) ? left : CellComponents.WALL;
        this.right = (right != null) ? right : CellComponents.WALL;
        this.up = (up != null) ? up : CellComponents.WALL;
        this.down = (down != null) ? down : CellComponents.WALL;
    }

    public CellComponents getLeft() {
        return left;
    }
    
    public CellComponents getRight() {
        return right;
    }
    
    public CellComponents getUp() {
        return up;
    }
    
    public CellComponents getDown() {
        return down;
    }

    public void setLeft(CellComponents left) {
        if (left != null) {
            this.left = left;
            return; 
        }
        this.left = CellComponents.WALL;
    }
    
    public void setRight(CellComponents right) {
        if (right != null) {
            this.right = right;
            return; 
        }
        this.right = CellComponents.WALL;
    }

    public void setUp(CellComponents up) {
        if (up != null) {
            this.up = up;
            return; 
        }
        this.up = CellComponents.WALL;
    }
    
    public void setDown(CellComponents down) {
        if (down != null) {
            this.down = down;
            return; 
        }
        this.down = CellComponents.WALL;
    }

    /**
     * Returns a string representation of this cell, in the form of
     * "Cell [left=<left>, right=<right>, up=<up>, down=<down>]".
     * 
     * @return a string representation of this cell
     */
    public String toString() {
        return "Cell [left=" + left + ", right=" + right + ", up=" + up + ", down=" + down + "]";
    }
}
