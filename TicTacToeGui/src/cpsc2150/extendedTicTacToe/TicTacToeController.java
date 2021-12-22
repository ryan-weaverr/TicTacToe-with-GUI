package cpsc2150.extendedTicTacToe;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController {

    //our current game that is being played
    private final IGameBoard curGame;

    //The screen that provides our view
    private final TicTacToeView screen;

    public static final int MAX_PLAYERS = 10;

    //Set during constructor call
    private final int numPlayers;

    //Used to determine if next button click starts a new game
    private int endGame = 0;

    //array to hold players
    private final char[] playerArray;
    private char currentPlayer;

    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @param np    The number of players for the game
     *
     * @post the controller will respond to actions on the view using the model, load playerArray with letters of the alphabet.
     */

    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;
        numPlayers = np;

        playerArray = new char[np+1];
        char Player = 'A';
        for(int i = 1; i <= np; i++)
        {
            playerArray[i] = Player;
            Player++;
        }
        currentPlayer = playerArray[1];
    }

    /**
     * @param row the row of the activated button
     * @param col the column of the activated button
     *
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col)
    {
        if(endGame == 1)
        {
            newGame();
        }
        //create board position with input
        BoardPosition newPos = new BoardPosition(row,col);
        //if space is valid, player marker and set marker on screen
        if(curGame.checkSpace(newPos))
        {
            curGame.placeMarker(newPos, currentPlayer);
            screen.setMarker(row, col, currentPlayer);
        }
        //otherwise, print error message and end function call
        else
        {
            screen.setMessage("That space is unavailable, please pick again");
            return;
        }
        //if last marker results in win, print win message and set endGame
        if(curGame.checkForWinner((newPos)))
        {
            screen.setMessage("Player " + currentPlayer + " wins!");
            endGame = 1;
        }
        //otherwise, check for draw then set endGame
        else if (curGame.checkForDraw())
        {
            screen.setMessage("Game is a draw!");
            endGame = 1;
        }
        //if no win and no draw, set current player to next in playerArray and print current player turn to screen
        else
        {
            for(int i = 1; i <= numPlayers; i++)
            {
                if(currentPlayer == playerArray[i])
                {
                    //loop back to start of playerArray if current player is last in array
                    if(i == numPlayers)
                    {
                        currentPlayer = playerArray[1];
                    }
                    else
                    {
                        currentPlayer = playerArray[i+1];
                    }
                    break;
                }
            }
            screen.setMessage("It is " + currentPlayer + "'s turn.");
        }
    }

    private void newGame() {
        // You do not need to make any changes to this code.
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}