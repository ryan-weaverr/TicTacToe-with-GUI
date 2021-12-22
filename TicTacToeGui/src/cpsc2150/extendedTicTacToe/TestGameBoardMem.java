package cpsc2150.extendedTicTacToe;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class TestGameBoardMem
{
    //constructor call method for GameBoardMem
    private IGameBoard makeGameBoard(int r, int c, int w)
    {
        return new GameBoardMem(r, c, w);
    }

    //private method to create char array
    private char[][] makeArray(int r, int c)
    {
        char [][] gb = new char[r][c];
        for(int row = 0; row < r; row++)
        {
            for(int col = 0; col < c; col++)
            {
                gb[row][col] = ' ';
            }
        }
        return gb;
    }

    //private method to create GameBoard string representation from array
    private String gbString(char[][] a)
    {
        int smallestSpace = 0;
        int rowNum = a.length;
        int colNum = a[0].length;
        String board = "   ";

        for(int col = smallestSpace; col < colNum; col++)
        {
            if (col <= 9)
            {
                board += " ";
            }
            board += col;
            board += "|";
        }
        board += "\n";

        for(int row = smallestSpace; row < rowNum; row++)
        {
            if (row <= 9)
            {
                board += " ";
            }
            board += row;
            board += "|";
            for (int col = smallestSpace; col < colNum; col++)
            {
                board += a[row][col];
                board += " |";
            }
            board += "\n";
        }
        return board;
    }

    //3 constructor test cases
    //Test 1: Lower bound for board size and num to win
    @Test
    public void test_constructor_minSpaces_minWin()
    {
        char [][]testArray = makeArray(3, 3);
        IGameBoard gb = makeGameBoard(3, 3, 3);

        assertEquals(gbString(testArray), gb.toString());
    }
    //Test 2: Upper bound for board size and num to win
    @Test
    public void test_constructor_maxSpaces_maxWin()
    {
        char [][]testArray = makeArray(100, 100);
        IGameBoard gb = makeGameBoard(100, 100, 25);

        assertEquals(gbString(testArray), gb.toString());
    }
    //Test 3: Routine board size and num win
    @Test
    public void test_constructor_routineSpaces_routineWin()
    {
        char [][]testArray = makeArray(5, 7);
        IGameBoard gb = makeGameBoard(5, 7, 4);

        assertEquals(gbString(testArray), gb.toString());
    }

    //3 checkSpace test cases
    //Test 1: check empty at edge of board
    @Test
    public void test_checkSpace_emptySpace_cornerPosition()
    {
        char[][] expectedOutPut = makeArray(6,6);
        IGameBoard gb = makeGameBoard(6, 6, 3);
        BoardPosition newPosition = new BoardPosition(0, 0);


        assertTrue(gb.checkSpace(newPosition));
        assertEquals(gbString(expectedOutPut), gb.toString());
    }
    //Test 2: check full in middle of board
    @Test
    public void test_checkSpace_fullSpaceX_standardPosition()
    {
        char[][] input = makeArray(6,6);
        input[1][4] = 'X';
        IGameBoard gb = makeGameBoard(6, 6, 3);
        BoardPosition newPosition = new BoardPosition(1, 4);
        gb.placeMarker(newPosition,'X');

        assertEquals(gbString(input), gb.toString());
        assertFalse(gb.checkSpace(newPosition));
    }
    //Test 3: check full at edge of board
    @Test
    public void test_checkSpace_fullSpaceY_cornerPosition()
    {
        char[][] input = makeArray(6,6);
        input[5][5] = 'Y';
        IGameBoard gb = makeGameBoard(6, 6, 3);
        BoardPosition newPosition = new BoardPosition(5, 5);
        gb.placeMarker(newPosition,'Y');

        assertEquals(gbString(input), gb.toString());
        assertFalse(gb.checkSpace(newPosition));
    }

    //4 checkHorizontalWin test cases
    //check no win, top row win X, bottom row win Y, middle standard no win different marker in between check
    @Test
    public void test_horizontalWin_noWin()
    {
        char[][] input = makeArray(6,6);
        input[0][0] = 'X';
        IGameBoard gb = makeGameBoard(6, 6, 3);
        BoardPosition newPosition = new BoardPosition(0, 0);
        gb.placeMarker(newPosition,'X');

        assertEquals(gbString(input), gb.toString());
        assertFalse(gb.checkHorizontalWin(newPosition, 'X'));
    }
    @Test
    public void test_horizontalWin_topRowWin_topLeftCorner_3toWin()
    {
        char[][] input = makeArray(6,6);
        IGameBoard gb = makeGameBoard(6, 6, 3);
        for(int c = 0; c < 2; c++)
        {
            input[0][c] = 'X';
            BoardPosition newPosition = new BoardPosition(0, c);
            gb.placeMarker(newPosition, 'X');
        }
        input[0][2] = 'X';
        BoardPosition newPosition = new BoardPosition(0, 2);
        gb.placeMarker(newPosition, 'X');

        assertEquals(gbString(input), gb.toString());
        assertTrue(gb.checkHorizontalWin(newPosition, 'X'));
    }
    @Test
    public void test_horizontalWin_bottomRowWin_bottomRightCorner_4toWin()
    {
        char[][] input = makeArray(6,6);
        IGameBoard gb = makeGameBoard(6, 6, 4);
        for(int c = 2; c < 5; c++)
        {
            input[0][c] = 'Y';
            BoardPosition newPosition = new BoardPosition(0, c);
            gb.placeMarker(newPosition, 'Y');
        }
        input[0][5] = 'Y';
        BoardPosition newPosition = new BoardPosition(0, 5);
        gb.placeMarker(newPosition, 'Y');

        assertEquals(gbString(input), gb.toString());
        assertTrue(gb.checkHorizontalWin(newPosition, 'Y'));
    }
    @Test
    public void test_horizontalWin_middleNoWin_markerBetween()
    {
        char[][] input = makeArray(6,6);
        input[2][1] = 'X';
        input[2][2] = 'Y';
        input[2][3] = 'X';
        IGameBoard gb = makeGameBoard(6, 6, 3);
        BoardPosition newPosition = new BoardPosition(2, 1);
        gb.placeMarker(newPosition, 'X');
        BoardPosition newPosition1 = new BoardPosition(2, 2);
        gb.placeMarker(newPosition1, 'Y');
        BoardPosition newPosition2 = new BoardPosition(2, 3);
        gb.placeMarker(newPosition2, 'X');

        assertEquals(gbString(input), gb.toString());
        assertFalse(gb.checkHorizontalWin(newPosition2, 'X'));
    }

    //4 checkVerticalWin test cases
    //check no win, left column win, right column win, middle column no win marker in between
    @Test
    public void test_verticalWin_noWin()
    {
        char[][] input = makeArray(6,6);
        input[0][0] = 'Y';
        IGameBoard gb = makeGameBoard(6, 6, 3);
        BoardPosition newPosition = new BoardPosition(0, 0);
        gb.placeMarker(newPosition,'Y');

        assertEquals(gbString(input), gb.toString());
        assertFalse(gb.checkVerticalWin(newPosition, 'Y'));
    }
    @Test
    public void test_verticalWin_leftColumn_topLeftCorner()
    {
        char[][] input = makeArray(6,6);
        IGameBoard gb = makeGameBoard(6, 6, 4);
        for(int r = 0; r < 3; r++)
        {
            input[r][0]='Y';
            BoardPosition newPosition = new BoardPosition(r, 0);
            gb.placeMarker(newPosition, 'Y');
        }
        input[3][0]='Y';
        BoardPosition newPosition = new BoardPosition(3, 0);
        gb.placeMarker(newPosition, 'Y');

        assertEquals(gbString(input), gb.toString());
        assertTrue(gb.checkVerticalWin(newPosition, 'Y'));
    }
    @Test
    public void test_verticalWin_rightColumn_bottomRightCorner()
    {
        char[][] input = makeArray(5,5);
        IGameBoard gb = makeGameBoard(5, 5, 3);
        for(int r = 2; r < 4; r++)
        {
            input[r][4]='X';
            BoardPosition newPosition = new BoardPosition(r, 4);
            gb.placeMarker(newPosition, 'X');
        }
        input[4][4]='X';
        BoardPosition newPosition = new BoardPosition(4, 4);
        gb.placeMarker(newPosition, 'X');

        assertTrue(gb.checkVerticalWin(newPosition, 'X'));
        assertEquals(gbString(input), gb.toString());
    }
    @Test
    public void test_verticalWin_middle_noWin_MarkerInBetween()
    {
        char[][] input = makeArray(7,5);
        input[2][2] = 'X';
        input[3][2] = 'Y';
        input[4][2] = 'X';
        IGameBoard gb = makeGameBoard(7, 5, 3);
        BoardPosition newPosition = new BoardPosition(2, 2);
        gb.placeMarker(newPosition, 'X');
        BoardPosition newPosition1 = new BoardPosition(3, 2);
        gb.placeMarker(newPosition1, 'Y');
        BoardPosition newPosition2 = new BoardPosition(4, 2);
        gb.placeMarker(newPosition2, 'X');

        assertFalse(gb.checkVerticalWin(newPosition2, 'X'));
        assertEquals(gbString(input), gb.toString());
    }

    //7 checkDiagonalWin test cases
    //Test 1: No left diagonal win
    @Test
    public void test_diagonalWin_noWin_leftDiagonal()
    {
        char[][] input = makeArray(5,5);
        input[0][0] = 'Y';
        input[1][1] = 'Y';
        input[2][2] = 'Y';
        IGameBoard gb = makeGameBoard(5, 5, 4);
        BoardPosition newPosition = new BoardPosition(0, 0);
        gb.placeMarker(newPosition,'Y');
        BoardPosition newPosition1 = new BoardPosition(1, 1);
        gb.placeMarker(newPosition1,'Y');
        BoardPosition newPosition2 = new BoardPosition(2, 2);
        gb.placeMarker(newPosition2,'Y');

        assertFalse(gb.checkDiagonalWin(newPosition2, 'Y'));
        assertEquals(gbString(input), gb.toString());
    }
    //Test 2: No right diagonal win
    @Test
    public void test_diagonalWin_noWin_rightDiagonal()
    {
        char[][] input = makeArray(5,5);
        input[4][0] = 'Y';
        input[3][1] = 'Y';
        input[2][2] = 'Y';
        IGameBoard gb = makeGameBoard(5, 5, 4);
        BoardPosition newPosition = new BoardPosition(4, 0);
        gb.placeMarker(newPosition,'Y');
        BoardPosition newPosition1 = new BoardPosition(3, 1);
        gb.placeMarker(newPosition1,'Y');
        BoardPosition newPosition2 = new BoardPosition(2, 2);
        gb.placeMarker(newPosition2,'Y');

        assertFalse(gb.checkDiagonalWin(newPosition2, 'Y'));
        assertEquals(gbString(input), gb.toString());
    }
    //Test 3: No left diagonal win, different marker in middle
    @Test
    public void test_diagonalWin_noWin_leftDiagonal_differentMarkerInMiddle()
    {
        char[][] input = makeArray(5,5);
        input[0][0] = 'Y';
        input[1][1] = 'Y';
        input[2][2] = 'Y';
        input[3][3] = 'X';
        input[4][4] = 'Y';
        IGameBoard gb = makeGameBoard(5, 5, 4);
        BoardPosition newPosition = new BoardPosition(0, 0);
        gb.placeMarker(newPosition,'Y');
        BoardPosition newPosition1 = new BoardPosition(1, 1);
        gb.placeMarker(newPosition1,'Y');
        BoardPosition newPosition2 = new BoardPosition(2, 2);
        gb.placeMarker(newPosition2,'Y');
        BoardPosition newPosition3 = new BoardPosition(3, 3);
        gb.placeMarker(newPosition3,'X');
        BoardPosition newPosition4 = new BoardPosition(4, 4);
        gb.placeMarker(newPosition4,'Y');

        assertFalse(gb.checkDiagonalWin(newPosition2, 'Y'));
        assertEquals(gbString(input), gb.toString());
    }
    //Test 4: No right diagonal win, different marker in middle
    @Test
    public void test_diagonalWin_noWin_rightDiagonal_differentMarkerInMiddle()
    {
        char[][] input = makeArray(5,5);
        input[4][0] = 'Y';
        input[3][1] = 'Y';
        input[2][2] = 'Y';
        input[1][3] = 'X';
        input[0][4] = 'Y';
        IGameBoard gb = makeGameBoard(5, 5, 4);
        BoardPosition newPosition = new BoardPosition(4, 0);
        gb.placeMarker(newPosition,'Y');
        BoardPosition newPosition1 = new BoardPosition(3, 1);
        gb.placeMarker(newPosition1,'Y');
        BoardPosition newPosition2 = new BoardPosition(2, 2);
        gb.placeMarker(newPosition2,'Y');
        BoardPosition newPosition3 = new BoardPosition(1, 3);
        gb.placeMarker(newPosition3,'X');
        BoardPosition newPosition4 = new BoardPosition(0, 4);
        gb.placeMarker(newPosition4,'Y');

        assertFalse(gb.checkDiagonalWin(newPosition2, 'Y'));
        assertEquals(gbString(input), gb.toString());
    }
    //Test 5: Left diagonal win
    @Test
    public void test_diagonalWin_Win_leftDiagonal()
    {
        char[][] input = makeArray(5,5);
        input[1][1] = 'Y';
        input[2][2] = 'Y';
        input[3][3] = 'Y';
        input[4][4] = 'Y';
        IGameBoard gb = makeGameBoard(5, 5, 4);
        BoardPosition newPosition = new BoardPosition(1, 1);
        gb.placeMarker(newPosition,'Y');
        BoardPosition newPosition1 = new BoardPosition(2, 2);
        gb.placeMarker(newPosition1,'Y');
        BoardPosition newPosition2 = new BoardPosition(3, 3);
        gb.placeMarker(newPosition2,'Y');
        BoardPosition newPosition3 = new BoardPosition(4, 4);
        gb.placeMarker(newPosition3,'Y');

        assertTrue(gb.checkDiagonalWin(newPosition2, 'Y'));
        assertEquals(gbString(input), gb.toString());
    }
    //Test 6: Right diagonal win
    @Test
    public void test_diagonalWin_Win_rightDiagonal()
    {
        char[][] input = makeArray(5,5);
        input[3][1] = 'X';
        input[2][2] = 'X';
        input[1][3] = 'X';
        input[0][4] = 'X';
        IGameBoard gb = makeGameBoard(5, 5, 4);
        BoardPosition newPosition = new BoardPosition(3, 1);
        gb.placeMarker(newPosition,'X');
        BoardPosition newPosition1 = new BoardPosition(2, 2);
        gb.placeMarker(newPosition1,'X');
        BoardPosition newPosition2 = new BoardPosition(1, 3);
        gb.placeMarker(newPosition2,'X');
        BoardPosition newPosition3 = new BoardPosition(0, 4);
        gb.placeMarker(newPosition3,'X');

        assertTrue(gb.checkDiagonalWin(newPosition2, 'X'));
        assertEquals(gbString(input), gb.toString());
    }
    //Test 7: Win with different character on each end
    @Test
    public void test_diagonalWin_Win_rightDiagonal_middle_diffCharEnd()
    {
        char[][] input = makeArray(7,7);
        input[5][0] = 'Y';
        input[4][1] = 'X';
        input[3][2] = 'X';
        input[2][3] = 'X';
        input[1][4] = 'Y';
        IGameBoard gb = makeGameBoard(7, 7, 3);
        BoardPosition newPosition5 = new BoardPosition(5, 0);
        gb.placeMarker(newPosition5,'Y');
        BoardPosition newPosition = new BoardPosition(4, 1);
        gb.placeMarker(newPosition,'X');
        BoardPosition newPosition1 = new BoardPosition(3, 2);
        gb.placeMarker(newPosition1,'X');
        BoardPosition newPosition2 = new BoardPosition(2, 3);
        gb.placeMarker(newPosition2,'X');
        BoardPosition newPosition3 = new BoardPosition(1, 4);
        gb.placeMarker(newPosition3,'Y');

        assertTrue(gb.checkDiagonalWin(newPosition2, 'X'));
        assertEquals(gbString(input), gb.toString());
    }

    //4 checkForDraw test cases
    //Test 1: Board not full, checkVertical, horizontal, and diagonal all false
    @Test
    public void test_draw_noDraw_noWin_boardNotEmpty()
    {
        char[][] input = makeArray(5,5);
        input[2][2] = 'X';
        input[3][2] = 'Y';
        input[0][3] = 'X';
        IGameBoard gb = makeGameBoard(5, 5, 3);

        BoardPosition newPosition = new BoardPosition(2, 2);
        gb.placeMarker(newPosition, 'X');
        BoardPosition newPosition1 = new BoardPosition(3, 2);
        gb.placeMarker(newPosition1, 'Y');
        BoardPosition newPosition2 = new BoardPosition(0, 3);
        gb.placeMarker(newPosition2, 'X');

        assertFalse(gb.checkForDraw());
        assertEquals(gbString(input), gb.toString());
    }
    //Test 2: Board full, no win
    @Test
    public void test_draw_Draw_noWin_fullBoard()
    {
        char[][] input = makeArray(3,3);
        input[0][0] = 'X';
        input[0][1] = 'Y';
        input[0][2] = 'X';
        input[1][0] = 'Y';
        input[1][1] = 'Y';
        input[1][2] = 'X';
        input[2][0] = 'X';
        input[2][1] = 'X';
        input[2][2] = 'Y';
        IGameBoard gb = makeGameBoard(3, 3, 3);

        BoardPosition newPosition = new BoardPosition(0, 0);
        gb.placeMarker(newPosition, 'X');
        BoardPosition newPosition1 = new BoardPosition(0, 1);
        gb.placeMarker(newPosition1, 'Y');
        BoardPosition newPosition2 = new BoardPosition(0, 2);
        gb.placeMarker(newPosition2, 'X');

        BoardPosition newPosition3 = new BoardPosition(1, 0);
        gb.placeMarker(newPosition3, 'Y');
        BoardPosition newPosition4 = new BoardPosition(1, 1);
        gb.placeMarker(newPosition4, 'Y');
        BoardPosition newPosition5 = new BoardPosition(1, 2);
        gb.placeMarker(newPosition5, 'X');

        BoardPosition newPosition6 = new BoardPosition(2, 0);
        gb.placeMarker(newPosition6, 'X');
        BoardPosition newPosition7 = new BoardPosition(2, 1);
        gb.placeMarker(newPosition7, 'X');
        BoardPosition newPosition8 = new BoardPosition(2, 2);
        gb.placeMarker(newPosition8, 'Y');

        assertTrue(gb.checkForDraw());
        assertEquals(gbString(input), gb.toString());
    }
    //Test 3: Routine no win no draw
    @Test
    public void test_draw_noDraw_noWin_routine()
    {
        char[][] input = makeArray(3,12);
        input[1][8] = 'X';
        input[0][2] = 'X';
        input[2][11] = 'X';
        IGameBoard gb = makeGameBoard(3, 12, 3);

        BoardPosition newPosition = new BoardPosition(1, 8);
        gb.placeMarker(newPosition, 'X');
        BoardPosition newPosition1 = new BoardPosition(0, 2);
        gb.placeMarker(newPosition1, 'X');
        BoardPosition newPosition2 = new BoardPosition(2, 11);
        gb.placeMarker(newPosition2, 'X');

        assertFalse(gb.checkForDraw());
        assertEquals(gbString(input), gb.toString());
    }
    //Test 4: Routine no win, big board
    @Test
    public void test_draw_noDraw_noWin_routine_bigBoard()
    {
        char[][] input = makeArray(98,97);
        input[12][22] = 'X';
        input[75][2] = 'Y';
        input[2][11] = 'Z';
        IGameBoard gb = makeGameBoard(98, 97, 14);

        BoardPosition newPosition = new BoardPosition(12, 22);
        gb.placeMarker(newPosition, 'X');
        BoardPosition newPosition1 = new BoardPosition(75, 2);
        gb.placeMarker(newPosition1, 'Y');
        BoardPosition newPosition2 = new BoardPosition(2, 11);
        gb.placeMarker(newPosition2, 'Z');

        assertFalse(gb.checkForDraw());
        assertEquals(gbString(input), gb.toString());
    }

    //5 whatsAtPos test cases
    //Test 1: Test position with no player
    @Test
    public void test_whatsAtPos_emptyPos()
    {
        char[][] input = makeArray(5,5);
        IGameBoard gb = makeGameBoard(5, 5, 3);
        BoardPosition newPosition = new BoardPosition(2, 2);
        char check = gb.whatsAtPos(newPosition);

        assertEquals(gbString(input), gb.toString());
        assertEquals(gb.whatsAtPos(newPosition), ' ');
        assertEquals(check, ' ');
    }
    //Test 2: Test position with player X
    @Test
    public void test_whatsAtPos_playerX_StandardInput()
    {
        char[][] input = makeArray(5,5);
        input[2][2] = 'X';
        IGameBoard gb = makeGameBoard(5, 5, 3);
        BoardPosition newPosition = new BoardPosition(2, 2);
        gb.placeMarker(newPosition, 'X');
        char check = gb.whatsAtPos(newPosition);

        assertEquals(gbString(input), gb.toString());
        assertEquals(gb.whatsAtPos(newPosition), 'X');
        assertEquals(check, 'X');
    }
    //Test 3: Test position with wrong player check
    @Test
    public void test_whatsAtPos_wrongPlayer()
    {
        char[][] input = makeArray(5,5);
        input[0][0] = 'Y';
        IGameBoard gb = makeGameBoard(5, 5, 3);
        BoardPosition newPosition = new BoardPosition(0, 0);
        gb.placeMarker(newPosition, 'Y');
        char check = gb.whatsAtPos(newPosition);

        assertEquals(gbString(input), gb.toString());
        assertEquals(gb.whatsAtPos(newPosition), 'Y');
        assertNotEquals('X', gb.whatsAtPos(newPosition));
        assertNotEquals(check, 'Z');
    }
    //Test 4: Test position with other player surrounding
    @Test
    public void test_whatsAtPos_otherPlayersAround()
    {
        char[][] input = makeArray(5,5);
        input[0][0] = 'Y';
        input[0][1] = 'X';
        input[0][2] = 'Y';
        IGameBoard gb = makeGameBoard(5, 5, 3);
        BoardPosition newPosition = new BoardPosition(0, 0);
        gb.placeMarker(newPosition, 'Y');
        BoardPosition newPosition1 = new BoardPosition(0, 1);
        gb.placeMarker(newPosition1, 'X');
        BoardPosition newPosition2 = new BoardPosition(0, 2);
        gb.placeMarker(newPosition2, 'Y');
        char check = gb.whatsAtPos(newPosition1);

        assertEquals(gbString(input), gb.toString());
        assertEquals(gb.whatsAtPos(newPosition1), 'X');
        assertNotEquals('Y', gb.whatsAtPos(newPosition1));
        assertNotEquals(check, 'Y');
    }
    //Test 5: standard test
    @Test
    public void test_whatsAtPos_standard()
    {
        char[][] input = makeArray(7,2);
        IGameBoard gb = makeGameBoard(7, 2, 4);
        input[6][1] = 'Z';
        BoardPosition newPosition = new BoardPosition(6, 1);
        gb.placeMarker(newPosition,'Z');


        assertEquals(gbString(input), gb.toString());
        assertEquals(gb.whatsAtPos(newPosition), 'Z');
    }

    //5 isPlayerAtPos test cases
    //Test 1: Player not at requested position
    @Test
    public void test_isPlayerAtPos_wrongPlayer()
    {
        char[][] input = makeArray(5,5);
        input[0][0] = 'Y';
        input[1][2] = 'X';
        IGameBoard gb = makeGameBoard(5, 5, 3);
        BoardPosition newPosition = new BoardPosition(0, 0);
        gb.placeMarker(newPosition, 'Y');
        BoardPosition newPosition1 = new BoardPosition(1, 2);
        gb.placeMarker(newPosition1, 'X');

        assertFalse(gb.isPlayerAtPos(newPosition,'X'));
        assertEquals(gbString(input), gb.toString());
    }
    //Test 2: Player is at position
    @Test
    public void test_isPlayerAtPos_rightPlayer()
    {
        char[][] input = makeArray(5,5);
        input[0][0] = 'X';
        IGameBoard gb = makeGameBoard(5, 5, 3);
        BoardPosition newPosition = new BoardPosition(0, 0);
        gb.placeMarker(newPosition, 'X');

        assertTrue(gb.isPlayerAtPos(newPosition,'X'));
        assertEquals(gbString(input), gb.toString());
    }
    //Test 3: Check for player at empty space
    @Test
    public void test_isPlayerAtPos_emptySpace()
    {
        char[][] input = makeArray(5,5);
        input[1][0] = 'X';
        IGameBoard gb = makeGameBoard(5, 5, 3);
        BoardPosition newPosition = new BoardPosition(0, 0);
        BoardPosition newPosition1 = new BoardPosition(1, 0);
        gb.placeMarker(newPosition1, 'X');

        assertFalse(gb.isPlayerAtPos(newPosition,'X'));
        assertEquals(gbString(input), gb.toString());
    }
    //Test 4: Check for player at board boundary
    @Test
    public void test_isPlayerAtPos_correct_edgeBoard()
    {
        char[][] input = makeArray(3,3);
        input[2][2] = 'W';
        IGameBoard gb = makeGameBoard(3, 3, 3);
        BoardPosition newPosition = new BoardPosition(2, 2);
        gb.placeMarker(newPosition, 'W');

        assertTrue(gb.isPlayerAtPos(newPosition,'W'));
        assertEquals(gbString(input), gb.toString());
    }
    //Test 5: Check player with multiple people on board
    @Test
    public void test_isPlayerAtPos_correct_middleBoard_multiOnBoard()
    {
        char[][] input = makeArray(3,3);
        input[2][2] = 'X';
        input[1][0] = 'Y';
        input[0][2] = 'Z';
        IGameBoard gb = makeGameBoard(3, 3, 3);
        BoardPosition newPosition = new BoardPosition(2, 2);
        gb.placeMarker(newPosition, 'X');
        BoardPosition newPosition1 = new BoardPosition(1, 0);
        gb.placeMarker(newPosition1, 'Y');
        BoardPosition newPosition2 = new BoardPosition(0, 2);
        gb.placeMarker(newPosition2, 'Z');

        assertTrue(gb.isPlayerAtPos(newPosition1,'Y'));
        assertEquals(gbString(input), gb.toString());
    }

    //5 placeMarker test cases
    //test 1: placing marker on empty board
    @Test
    public void test_placeMarker_emptyBoard()
    {
        IGameBoard gb = makeGameBoard(5, 5, 3);
        char[][] input = makeArray(5,5);
        input[2][2] = 'X';

        BoardPosition newPosition = new BoardPosition(2, 2);
        gb.placeMarker(newPosition, 'X');

        assertEquals(gbString(input), gb.toString());
    }
    //Test 2: placing maker on board containing one marker of same player
    @Test
    public void test_placeMarker_onePlayer_onBoard()
    {
        IGameBoard gb = makeGameBoard(5, 5, 3);
        char[][] input = makeArray(5,5);
        input[0][0] = 'X';
        input[1][2] = 'X';

        BoardPosition newPosition = new BoardPosition(0, 0);
        gb.placeMarker(newPosition, 'X');
        BoardPosition newPosition1 = new BoardPosition(1, 2);
        gb.placeMarker(newPosition1, 'X');

        assertEquals(gbString(input), gb.toString());
    }
    //Test 3: place marker of character not yet on board
    @Test
    public void test_placeMarker_newPlayer()
    {
        IGameBoard gb = makeGameBoard(6, 5, 3);
        char[][] input = makeArray(6,5);
        input[0][0] = 'X';
        input[1][2] = 'X';
        input[4][3] = 'Y';

        BoardPosition newPosition = new BoardPosition(0, 0);
        gb.placeMarker(newPosition, 'X');
        BoardPosition newPosition1 = new BoardPosition(1, 2);
        gb.placeMarker(newPosition1, 'X');
        BoardPosition newPosition2 = new BoardPosition(4, 3);
        gb.placeMarker(newPosition2, 'Y');

        assertEquals(gbString(input), gb.toString());
    }
    //Test 4: place maker in each corner of board
    @Test
    public void test_placeMarker_eachCorner()
    {
        IGameBoard gb = makeGameBoard(4, 4, 3);
        char[][] input = makeArray(4,4);
        input[0][0] = 'X';
        input[0][3] = 'X';
        input[3][0] = 'Y';
        input[3][3] = 'Y';

        BoardPosition newPosition = new BoardPosition(0, 0);
        gb.placeMarker(newPosition, 'X');
        BoardPosition newPosition1 = new BoardPosition(0, 3);
        gb.placeMarker(newPosition1, 'X');
        BoardPosition newPosition2 = new BoardPosition(3, 3);
        gb.placeMarker(newPosition2, 'Y');
        BoardPosition newPosition3 = new BoardPosition(3, 0);
        gb.placeMarker(newPosition3, 'Y');

        assertEquals(gbString(input), gb.toString());
    }
    //Test 5: standard place marker with others on the board
    @Test
    public void test_placeMarker_standardPlace_threePlayers()
    {
        IGameBoard gb = makeGameBoard(6, 5, 3);
        char[][] input = makeArray(6,5);
        input[0][3] = 'X';
        input[2][3] = 'X';
        input[1][1] = 'A';
        input[5][4] = 'Y';

        BoardPosition newPosition = new BoardPosition(0, 3);
        gb.placeMarker(newPosition, 'X');
        BoardPosition newPosition1 = new BoardPosition(2, 3);
        gb.placeMarker(newPosition1, 'X');
        BoardPosition newPosition2 = new BoardPosition(1, 1);
        gb.placeMarker(newPosition2, 'A');
        BoardPosition newPosition3 = new BoardPosition(5, 4);
        gb.placeMarker(newPosition3, 'Y');

        assertEquals(gbString(input), gb.toString());
    }

}
