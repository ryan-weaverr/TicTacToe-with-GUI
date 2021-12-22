package cpsc2150.extendedTicTacToe;

import java.util.*;

public class GameBoardMem extends AbsGameBoard implements IGameBoard
{
    /**
     * @invariants: 0 < rowNum <= 100 AND 0 < rowNum <= 100 AND 3 <= winNUm <= rowNum
     *
     */

    private final Map<Character,List<BoardPosition>> gameList;

    private final int rowNum;
    private final int colNum;
    public static int winNum;

    /**
     * @param numRows: number of rows on board
     * @param numColumn: number of columns on board
     * @param numForWin: number of markers in a row needed to win
     *
     * @post gameList = new HashMap
     */
    public GameBoardMem(int numRows, int numColumn, int numForWin)
    {
        rowNum = numRows;
        colNum = numColumn;
        winNum = numForWin;
        gameList = new HashMap<>();
    }

    /**
     * @param marker: boardPosition to place palyer
     * @param player: char to place at boardPosition
     *
     * @post add player and marker to gameList
     */
    public void placeMarker(BoardPosition marker, char player)
    {
        List<BoardPosition> newPos;
        if(gameList.containsKey(player))
        {
            newPos = gameList.get(player);
        }
        else
        {
            newPos = new ArrayList<BoardPosition>();
        }
        newPos.add(marker);
        gameList.put(player, newPos);
    }

    /**
     * @param pos: boardPosition to inspect
     * @return char at pos iff gameList contains pos, ' ' otherwise
     */
    public char whatsAtPos(BoardPosition pos)
    {
        for(Map.Entry<Character, List<BoardPosition>> entry : gameList.entrySet())
        {
            if(entry.getValue().contains(pos))
            {
                return entry.getKey();
            }
        }
        return ' ';
    }

    /**
     *
     * @param pos: boardPos to inspect
     * @param player: char to check if boardPosition contains
     * @return True iff gameList contains pos with player
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        List<BoardPosition> check = gameList.get(player);
        return check.contains(pos);
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
