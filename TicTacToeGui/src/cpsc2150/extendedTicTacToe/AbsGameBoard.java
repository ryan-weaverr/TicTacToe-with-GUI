package cpsc2150.extendedTicTacToe;

public abstract class AbsGameBoard implements IGameBoard
{
    /**
     * This method overrides toString(). Creates string representation of GameBoard
     *
     * @Pre: none
     * @Post: none
     *
     * @return: string board
     */
    @Override
    public String toString()
    {
        int rowNum = this.getNumRows();
        int colNum = this.getNumColumns();
        String board = "   ";

        for(int col = smallestSpace; col < colNum; col++)
        {
            if(col > 9)
            {
                board += col;
                board += "|";
            }
            else
            {
                board += " ";
                board += col;
                board += "|";
            }
        }
        board += "\n";

        for(int row = smallestSpace; row < rowNum; row++)
        {
            if(row > 9)
            {
                board += row;
                board += "|";
            }
            else
            {
                board += " ";
                board += row;
                board += "|";
            }
            for (int col = smallestSpace; col < colNum; col++)
            {
                BoardPosition check = new BoardPosition(row,col);
                board += whatsAtPos(check);
                board += " |";
            }
            board += "\n";
        }
        return board;
    }
}