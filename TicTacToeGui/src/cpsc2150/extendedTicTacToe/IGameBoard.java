package cpsc2150.extendedTicTacToe;

/**
 * This object contains all information about the gamBoard, and handles and call about what is on the gameBoard
 *
 * Defines: gameBoard: 2D character array or map in which gameBoard pieces ares stored
 *          gameBoardMem: HashMap containing key character and value List of boardPositions
 *
 * Initialization Ensures: [new gameBoard with number of gameBoard Rows greater than 0, less than or equal to 100 and number of gameBoard Columns greater than 0, less than or equal to 100]
 *                         [Each gameBoard space == ' ']
 *                         [New gameBoard HashMap]
 *
 * Constraints: 0 < Row < 100 and 0 < Column < 100
 *              3 <= numToWin <= Row
 */
public interface IGameBoard
{

    int smallestSpace = 0;
    int maxBoardSize = 100;
    int minWin = 3;
    int maxWin = 10;

    /**
     *This function returns true if board position is empty, return false if full
     *
     *Param: BoardPosition pos is the requested position to check
     *
     *Pre: Pos is 0 <= row < maxBoardSize and 0 <= column < maxBoardSize
     *Post: True iff pos empty or false iff pos full
     */
    default public boolean checkSpace(BoardPosition pos)
    {
        return whatsAtPos(pos) == ' ';
    }

    /**
     * This function marks space as taken by a player
     *
     * Param: BoardPosition marker is marker to be placed in position
     * Param: Player is player marker being placed
     *
     * Pre: checkSpace = true
     * Post: None
     */
    public void placeMarker(BoardPosition marker, char player);

    /**
     * This function will check to see if the last placed marker results in a win. Returns true if win, false otherwise
     *
     * Param: lastPos is the last position marker was placed
     *
     * Pre: None
     * Post: True iff checkHorizontalWin = true OR checkVerticalWin = true OR checkDiagonalWin, return false otherwise
     */
    default public boolean checkForWinner(BoardPosition lastPos)
    {
        char player = whatsAtPos(lastPos);
        return checkHorizontalWin(lastPos, player) || checkVerticalWin(lastPos, player) || checkDiagonalWin(lastPos, player);
    }

    /**
     * This function will check to see if all board positions are full, resulting in a draw
     *
     * Pre: checkHorizontalWin = false and checkVerticalWin = false and checkDiagonalWin = false
     * Post: True iff spaceCount == total spaces, false otherwise
     */
    default public boolean checkForDraw()
    {
        int spaceCount = 0;
        int rowNum = this.getNumRows();
        int colNum = this.getNumColumns();
        int totalSpaces = rowNum*colNum;

        for(int r = 0; r < rowNum; r++)
        {
            for(int c = 0; c < colNum; c++)
            {
                BoardPosition check = new BoardPosition(r, c);
                if(whatsAtPos(check) >= 'A' && whatsAtPos(check) <= 'Z')
                {
                    spaceCount++;
                }
            }
        }
        return spaceCount == totalSpaces;
    }

    /**
     * This function checks if last marker placed results in winNum in a row horizontally
     *
     * Param: lastPos is the last position a marker was placed
     * Param: player is person who last placed marker
     *
     * Pre: board not empty
     * Post: True iff last marker makes same winNum in a row vertically, false otherwise
     */
    default public boolean checkHorizontalWin(BoardPosition lastPos, char player)
    {
        //num count to win. starts at 1 since starting from last pos
        int count = 1;

        //check if key player value contains point above last pos. increment count if yes, updates check position
        BoardPosition checkPos = lastPos;
        for(int i = 1; i <= this.getNumToWin(); i++)
        {
            BoardPosition newPosition = new BoardPosition(checkPos.getRow(), checkPos.getColumn()+1);
            if(newPosition.getColumn() < this.getNumColumns()) {
                char pos = whatsAtPos(newPosition);
                if (pos == player) {
                    count++;
                    checkPos = newPosition;
                } else {
                    break;
                }
            }
        }
        if(count == this.getNumToWin())
        {
            return true;
        }

        //check if key player value contains point below last pos. increment count if yes, updates check position
        checkPos = lastPos;
        for(int i = 1; i <= this.getNumToWin(); i++)
        {
            BoardPosition newPosition = new BoardPosition(checkPos.getRow(), checkPos.getColumn()-1);
            if(newPosition.getColumn() >= smallestSpace) {
                char pos = whatsAtPos(newPosition);
                if (pos == player) {
                    count++;
                    checkPos = newPosition;
                } else {
                    break;
                }
            }
        }
        if(count == this.getNumToWin())
        {
            return true;
        }
        return count == this.getNumToWin();
    }

    /**
     * This function checks if last marker placed results in winNum in a row vertically
     *
     * Param: lastPos is the last position a marker was placed
     * Param: player is person who last placed marker
     *
     * Pre: board not empty
     * Post: True iff last marker makes same winNum in a row vertically, false otherwise
     */
    default public boolean checkVerticalWin(BoardPosition lastPos, char player)
    {
        //num count to win. starts at 1 since starting from last pos
        int count = 1;

        //check if key player value contains point above last pos. increment count if yes, updates check position
        BoardPosition checkPos = lastPos;
        for(int i = 1; i <= this.getNumToWin(); i++)
        {
            BoardPosition newPosition = new BoardPosition(checkPos.getRow()+1, checkPos.getColumn());
            if(newPosition.getRow() < this.getNumRows()) {
                char pos = whatsAtPos(newPosition);
                if (pos == player) {
                    count++;
                    checkPos = newPosition;
                } else {
                    break;
                }
            }
        }
        if(count == this.getNumToWin())
        {
            return true;
        }

        //check if key player value contains point below last pos. increment count if yes, updates check position
        checkPos = lastPos;
        for(int i = 1; i <= this.getNumToWin(); i++)
        {
            BoardPosition newPosition = new BoardPosition(checkPos.getRow()-1, checkPos.getColumn());
            if(newPosition.getRow() >= smallestSpace) {
                char pos = whatsAtPos(newPosition);
                if (pos == player) {
                    count++;
                    checkPos = newPosition;
                } else {
                    break;
                }
            }
        }
        if(count == this.getNumToWin())
        {
            return true;
        }
        return count == this.getNumToWin();
    }

    /**
     * This function checks if last marker placed results in winNum in a row diagonally
     *
     * Param: lastPos is the last position a marker was placed
     * Param: player is person who last placed marker
     *
     * Pre: board not empty
     * Post: True iff last marker makes same winNum in a row diagonally, false otherwise
     */
    default public boolean checkDiagonalWin(BoardPosition lastPos, char player)
    {
        //num count to win. starts at 1 since starting from last pos
        int count = 1;

        //check if key player value contains point above last pos. increment count if yes, updates check position
        BoardPosition checkPos = lastPos;
        for(int i = 1; i <= this.getNumToWin(); i++)
        {
            BoardPosition newPosition = new BoardPosition(checkPos.getRow()+1, checkPos.getColumn()+1);
            if(newPosition.getRow() < this.getNumRows() && newPosition.getColumn() < this.getNumColumns()) {
                char pos = whatsAtPos(newPosition);
                if (pos == player) {
                    count++;
                    checkPos = newPosition;
                } else {
                    break;
                }
            }
        }
        if(count == this.getNumToWin())
        {
            return true;
        }

        //check if key player value contains point below last pos. increment count if yes, updates check position
        checkPos = lastPos;
        for(int i = 1; i <= this.getNumToWin(); i++)
        {
            BoardPosition newPosition = new BoardPosition(checkPos.getRow()-1, checkPos.getColumn()-1);
            if(newPosition.getRow() >= smallestSpace && newPosition.getColumn() >= smallestSpace) {
                char pos = whatsAtPos(newPosition);
                if (pos == player) {
                    count++;
                    checkPos = newPosition;
                } else {
                    break;
                }
            }
        }
        if(count == this.getNumToWin())
        {
            return true;
        }

        count = 1;
        //check if key player value contains point above last pos. increment count if yes, updates check position
        checkPos = lastPos;
        for(int i = 1; i <= this.getNumToWin(); i++)
        {
            BoardPosition newPosition = new BoardPosition(checkPos.getRow()+1, checkPos.getColumn()-1);
            if(newPosition.getRow() < this.getNumRows() && newPosition.getColumn() >= smallestSpace) {
                char pos = whatsAtPos(newPosition);
                if (pos == player) {
                    count++;
                    checkPos = newPosition;
                } else {
                    break;
                }
            }
        }
        if(count == this.getNumToWin())
        {
            return true;
        }
        //check if key player value contains point below last pos. increment count if yes, updates check position
        checkPos = lastPos;
        for(int i = 1; i <= this.getNumToWin(); i++)
        {
            BoardPosition newPosition = new BoardPosition(checkPos.getRow()-1, checkPos.getColumn()+1);
            if(newPosition.getRow() >= smallestSpace && newPosition.getColumn() < this.getNumColumns()) {
                char pos = whatsAtPos(newPosition);
                if (pos == player) {
                    count++;
                    checkPos = newPosition;
                } else {
                    break;
                }
            }
        }
        if(count == this.getNumToWin())
        {
            return true;
        }
        return count == this.getNumToWin();
    }

    /**
     *The function checks what is at specified space
     *
     * Param: pos is board position to check
     *
     * Pre: Pos is smallestSpace <= row <= maxBoardSize and smallestSpace <= column <= maxBoardSize
     * Post: blank char if empty, player at Pos otherwise
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * This function checks if player is at specified position
     *
     * Param: pos position on game board to check
     * Param: player to check for
     *
     * Pre: Pos is smallestSpace <= row <= maxBoardSize and smallestSpace <= column <= MaxBoardSize
     * Post: True iff whatsAtPosition(pos) == player, false otherwise
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        return whatsAtPos(pos) == player;
    }

    /**
     * This method returns number of rows in gameBoard
     *
     * @Pre: none
     * @Post: none
     * @return: rowNum
     */
    public int getNumRows();

    /**
     * This method returns number of columns in gameBoard
     *
     * @Pre: none
     * @Post: none
     * @return: colNum
     */
    public int getNumColumns();

    /**
     * This method returns number of pieces in row need to win
     *
     * @Pre: none
     * @Post: none
     * @return: winNum
     */
    public int getNumToWin();
}
