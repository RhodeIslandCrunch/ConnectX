package models;

public interface IGameBoard{

    /**
     * This is a models.GameBoard that can hold the data to play a connectX game
     * A game-board in this context is defined as a 2d structure containing characters
     * This game-board is bound by NumRows and NumColumns
     */

    /**
     * Invariants:
     * There may be no blank space between 2 game tokens (Tokens fall to the lowest available y value)
     */

    /**
     * Initialization ensures:
     *      A Game-board is created of numRows and numColumns that is blank that can contain numRows * numColumns Characters
     * Defines:
     *     NumRows : int
     *     NumColumns: int
     *     NumToWin: int
     * Constraints:
     *     The game board may not have blank spaces vertically through characters
     *     The models.GameBoard can hold NumRows * numColumns Characters
     *
     */


    /**
     * Returns true if the column can accept another token false otherwise
     * @pre playerIn >= 0 AND
     *      PlayerIn <= NUMCOLUMNS
     * @post returns true if Column[maxY] = " " else false
     * @param c - The column to check for avaliable space
     * @return Boolean - Boolean containg if the column is free or not
     */
    public boolean checkIfFree(int c);

    /**
     * Places a token P into Column C
     * @pre c <= NUMCOLUMNS
     *      c <= 0
     *      checkIfFree(c) == true
     * @post Marker Character P will be placed in the lowest row of c
     * @param p - token to place
     * @param c = column to place it in
     */
     void placeToken(Character p, int c);

    /**
     * Checks for a win from the last inputted Colum
     * @pre c <= NUMCOLUMNS
     *      c <= 0
     *      c = lastPos [ the last position played on the board]
     * @post if there is a set of 5 consecutive pieces either vertically, horizontally or diagonally return true, else false
     * @param c - the column to check from, c = lastPos [ this is the last played position]
     * @return Bool - holding if a win has happened or not
     */
    boolean checkForWin(int c, char p);

    /**
     * Checks for that if a tied state has occured
     * @pre The game is not won
     * @post Returns true if there are no spaces left on the board, else false
     *       Board = #Board
     * @return Boolean - Returns if a tie has occured
     */
    boolean checkTie();

    /**
     * Checks if a player has won the game based off of the newest played piece and player token
     * Checks horizontally for a win across the X axis
     * @pre Location.xVal < NUMCOLUMNS AND
     *      Location.xVal >= 0 AND
     *      Location.yVal < NUMROWS AND
     *      Location.yVal >= 0
     *      Location = Last played Position
     *      Player token is a valid player
     * @post if the board contains a line of the same characters that are numToWin through the Location's value left or right return true, else false
     * @param location - the most recent position
     * @param player - the token of the most recent player
     * @return boolean - checks if a win has occurred
     */
    boolean checkHorizontalWin(BoardPosition location, Character player);
    /**
     * Checks if a player has won the game based off of the newest played piece and player token
     * Checks Vertically for a win across the Y axis
     * @pre Location.xVal < NUMCOLUMNS  AND
     *      Location.xVal >= 0 AND
     *      Location.yVal < NUMROWS  AND
     *      Location.yVal >= 0
     *      Location = Last played Position
     *      Player token is a valid player
     * @post if the board contains a line of the same characters that are numToWin through the Location's value up or down return true, else false
     * @param location - the most recent position that was played
     * @param player - the token of the most recent player
     * @return boolean - checks if a win has occurred
     */
    boolean checkVerticalWin(BoardPosition location, Character player);
    /**
     * Checks if a player has won the game based off of the newest played piece and player token
     * Checks Diagonlally for a win from top left to bottom right and top right to bottom left
     * @pre Location.xVal < NUMCOLUMNS  AND
     *      Location.xVal >= 0 AND
     *      Location.yVal < NUMROWS  AND
     *      Location.yVal >= 0
     *      Location = Last played Position
     *      Player token is a valid player
     * @post If there is a Diagonal line crossing the Location that are numToWin long then return true, else false
     * @param location - the most recent position
     * @param player - the token of the most recent player
     * @return boolean - checks if a win has occurred
     */
    public boolean checkDiagonalWin(BoardPosition location, Character player);

    /**
     * Function that returns the character at location
     * @pre Location.x < NUMCOLUMNS AND
     *      Location.x >= 0 AND
     *      Location.y < NUMCOLUMNS AND
     *      Location.y >= 0
     * @post Character = #Board[Location]
     * @param location - X,y Coordinate to check
     * @return Character - the Character at Board Position [x,y]
     */
    public Character whatsAtPos(BoardPosition location);

    /**
     * Function that checks a position on the board for the requested player
     * @pre Location.x <= NUMCOLUMNS AND
     *      Location.y <= NUMROWS AND
     *      Location.x <= 0 AND
     *      Location.y <= 0
     * @post If Board[x,y] == player then true else false
     * @param location - Board position x,y
     * @param player - the player that it is checking for
     * @return true if player = location.value, else false
     */
    public boolean isPlayerAtPos(BoardPosition location, Character player);

    /**
     * Function that returns a string containing the stat of the gameboard
     * @post string - Gameboard's state
     * @return string - string that contains a formatted version of gameboard
     */
    public String toString();

    // Project 2
    /**
     * function that returns the number of rows in the board
     * @post getNumRows() = #NUMROWS
     * @return int - int containing number of rows on the board
     */
    public int getNumRows();
    /**
     * function that returns the number of Columns in the board
     * @post getNumColumns() = #NUMCOLUMNS
     * @return int - int containing number of Columns on the board
     */

    public int getNumColumns();
    /**
     * function that returns the number of in a row you need to win
     * @post getNumtoWin() = #NUMTOWIN
     * @return int - int containing number you need to get in a row to win
     */
    public int getNumToWin();
}
