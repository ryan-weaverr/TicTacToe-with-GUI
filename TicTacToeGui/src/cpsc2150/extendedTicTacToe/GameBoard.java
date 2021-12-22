package cpsc2150.extendedTicTacToe;

public class GameBoard extends AbsGameBoard implements IGameBoard
{
    /**
     * @invariants: 0 < rowNum <= 100 AND 0 < rowNum <= 100 AND 3 <= winNUm <= rowNum
     *
     */

    private final char[][] gameBoard;
    private final int rowNum;
    private final int colNum;
    private final int winNum;

    /**
     * @param numRows: number of rows in gameBoard
     * @param numColumn: number of columns in gameBoard
     * @param numForWin: number of markers in a row needed to win
     *
     * @post gameBoard[rowNum][colNum], each position in gameBoard = ' '
     */
    public GameBoard(int numRows, int numColumn, int numForWin)
    {
        rowNum = numRows;
        colNum = numColumn;
        winNum = numForWin;
        gameBoard = new char[rowNum][colNum];
        for(int row = smallestSpace; row < rowNum; row++)
        {
            for(int col = smallestSpace; col < colNum; col++)
            {
                gameBoard[row][col] = ' ';
            }
        }
    }

    /**
     * @param marker: boardPosition to place player
     * @param player: char to place at boardPosition
     *
     * @post gameBoard[marker.row][marker.col] = player
     */
    public void placeMarker(BoardPosition marker, char player)
    {

        int row = marker.getRow();
        int col = marker.getColumn();

        gameBoard[row][col] = player;
    }

    /**
     * @param pos: boardPosition to inspect
     * @return char at boardPosition
     */
    public char whatsAtPos(BoardPosition pos)
    {
        int row = pos.getRow();
        int col = pos.getColumn();

        return gameBoard[row][col];
    }

    /**
     * @return number of Rows
     */
    public int getNumRows()
    {
        return rowNum;
    }

    /**
     * @return number of columns
     */
    public int getNumColumns()
    {
        return colNum;
    }

    /**
     * @return number of markers in a row needed to win
     */
    public int getNumToWin()
    {
        return winNum;
    }

}
