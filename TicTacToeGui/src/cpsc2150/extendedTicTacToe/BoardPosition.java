package cpsc2150.extendedTicTacToe;

public class BoardPosition {

    /**
     * invariant: BoardPosition iff GameBoard created
     *          0 <= row <= 4 and 0 <= column <= 7
     */

    private final int row;
    private final int column;

    /**
     * This constructor creates the first board position when the game starts
     *
     * @param r: Row to place move
     * @param c: Column to place move
     *
     * @pre NONE
     * @post row = r and column = c
     */
    public BoardPosition(int r, int c)
    {
        row = r;
        column = c;
    }

    /**
     * This method returns the column position
     *
     * @Pre: None
     * @Post: return column
     */
    public int getColumn()
    {
        return column;
    }

    /**
     * This method returns the row position
     *
     * param: NONE
     *
     * @pre None
     * @post return row
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Override equals method
     *
     * param: obj to compare
     *
     * @Pre: None
     * @Post: True iff obj == this.obj
     */
    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof BoardPosition)) return false;

        BoardPosition boardPos = (BoardPosition) obj;

        if(this.getRow() != boardPos.getRow()) return false;
        if(this.getColumn() != boardPos.getColumn()) return false;
        else return true;
    }

    /**
     * Override toString method. Returns string of row + column
     *
     * @Pre: None
     * @Post: string <row>,<column>
     */
    @Override
    public String toString()
    {
        return row + "," + column;
    }
}


