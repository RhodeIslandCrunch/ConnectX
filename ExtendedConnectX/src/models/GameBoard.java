package models;

public class GameBoard extends AbsGameBoard implements IGameBoard{

    int NUMROWS;
    int NUMCOLUMNS ;
    int NUMTOWIN;
    private char[][] gameBoard;
    // Create the player icons
    private char blankSpace = ' ';
    /**
     * Invariants
     * playerInput > 0 AND
     * playerInput <= MaxX
     * There may be no blank space between 2 game tokens
     */
    /**
     * Constructor for the Gameboard object
     * @pre rows <= 100
     * columns <= 100
     * winAmt <= 25
     * winAmt >= 3
     * winAmt <= rows
     * winAmt <= columns
     * @post There should be a blank gameBoard containing " " in each character position
     */
    public GameBoard(int rows, int columns, int winAmt){
        int i = 0;
        int j = 0;
        NUMROWS = rows;
        NUMCOLUMNS = columns;
        NUMTOWIN = winAmt;
        gameBoard = new char[NUMCOLUMNS][NUMROWS];

        // make board empty;
        while(i < NUMCOLUMNS){
            while(j < NUMROWS){
                // the blank Character to set each cell to
                gameBoard[i][j] = blankSpace;
                j = j + 1;
            }
            j = 0;
            i = i + 1;
        }
    }

    /**
     * Returns true if the column can accept another token false otherwise
     * @pre playerIn >= 0 AND
     *      PlayerIn <= MaxX
     * @post returns true if Column[maxY] = " " else false
     * @param c - The column to check for avaliable space
     * @return Boolean - Boolean containg if the column is free or not
     */
    public boolean checkIfFree(int c){
        int temp;
        temp = NUMROWS - 1;
        BoardPosition topOfColumn = new BoardPosition(c,temp);
        char SpaceVal;
        SpaceVal = this.whatsAtPos(topOfColumn);
        if(SpaceVal == blankSpace){
            return true;
        }
        return false;
    }

    /**
     * Places a token P into Column C
     * @pre c <= MaxX
     *      c <= 0
     *      Column[c, OccupiedY] < Column[c, MaxY]
     *      checkIfFree(c) == true
     * @post Marker Character P will be placed in the lowest row of c
     * @param p - token to place
     * @param c = column to place it in
     */
    public void placeToken(Character p, int c){
        // find the first empty space in the column
        int firstEmptySpotY;
        firstEmptySpotY = 0;

        while(gameBoard[c][firstEmptySpotY] != blankSpace){
            firstEmptySpotY = firstEmptySpotY + 1;
        }
        gameBoard[c][firstEmptySpotY] = p;
    }

    /**
     * Checks for a win from the last inputted Column
     * @pre c <= MaxX
     *      c <= 0
     *      c = lastPos [ the last position played on the board]
     * @post if there is a set of 5 consecutive pieces either vertically, horizontally or diagonally return true, else false
     * @param c - the column to check from, c = lastPos [ this is the last played position]
     * @return Bool - holding if a win has happened or not
     */
    public boolean checkForWin (int c, char Player){
        boolean WinCon;
        char lastToken;
        int lastY;
        int firstEmptySpotY = 0;

        if(gameBoard[c][NUMROWS - 1] != blankSpace){
            lastY = NUMROWS - 1;
        }
        else{
            while (gameBoard[c][firstEmptySpotY] != blankSpace) {
                firstEmptySpotY = firstEmptySpotY + 1;
            }
            lastY = firstEmptySpotY - 1;
        }

        BoardPosition LastLoc = new BoardPosition(c,lastY);
        lastToken = whatsAtPos(LastLoc);

        WinCon = checkDiagonalWin(LastLoc, lastToken);
        if(WinCon){
            return true;
        }

        WinCon = checkHorizontalWin(LastLoc, lastToken);
        if(WinCon){
            return true;
        }

        WinCon = checkVerticalWin(LastLoc, lastToken);
        if(WinCon){
            return true;
        }
        return false;
    }

    /**
     * Checks for that if a tied state has occured
     * @pre The game is not won
     * @post Returns true if there are no spaces left on the board, else false
     *       Board = #Board
     * @return Boolean - Returns if a tie has occured
     */
    public boolean checkTie(){
        int fullColumns;
        fullColumns = 0;
        int traveling;
        traveling = 0;
        while(traveling < NUMCOLUMNS) {
            if (gameBoard[traveling][NUMROWS - 1] != blankSpace) {
                fullColumns = fullColumns + 1;
            }
            traveling = traveling + 1;
        }
        if(fullColumns == NUMCOLUMNS) {
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * Checks if a player has won the game based off of the newest played piece and player token
     * Checks horizontally for a win across the X axis
     * @pre Location.xVal < < MaxX  AND
     *      Location.xVal >= 0 AND
     *      Location.yVal < MaxY AND
     *      Location.yVal >= 0
     *      Location = Last played Position
     *      Player token is a valid player
     * @post if the board contains a line of the same characters that are numToWin through the Location's value left or right return true, else false
     * @param location - the most recent position
     * @param player - the token of the most recent player
     * @return boolean - checks if a win has occurred
     */
    public boolean checkHorizontalWin(BoardPosition location, Character player){
        int OriginX;
        int OriginY;
        OriginX = location.getColumn();
        OriginY = location.getRow();
        int numInARow;
        // This starts at one as the origin is included in the row
        numInARow = 1;
        // Check to the left of the origin
        int travelingX;
        travelingX = OriginX - 1;
        //This checks left and adds a number for each succesful loop
        while(travelingX >= 0 && gameBoard[travelingX][OriginY] == player){
            // check as it will check again on a loop before breaking
            if(gameBoard[travelingX][OriginY] == player){
                numInARow = numInARow + 1;
            }
            travelingX = travelingX - 1;
        }
        // reset travelingX to the original X
        travelingX = OriginX + 1;
        // Check to the right of the origin
        while(travelingX < NUMCOLUMNS && gameBoard[travelingX][OriginY] == player){
            if(gameBoard[travelingX][OriginY] == player){
                numInARow = numInARow + 1;
                travelingX = travelingX + 1;
            }
        }
        // Check if a win has occurred horizontally
        if(numInARow >= getNumToWin()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Checks if a player has won the game based off of the newest played piece and player token
     * Checks Vertically for a win across the Y axis
     * @pre Location.xVal < G< MaxX  AND
     *      Location.xVal >= 0 AND
     *      Location.yVal < cation - the most recent position that was played
     *      * @param player - the token of the most recent player
     *      * @return boolean - checks if a win has occurred
     *      */


    public boolean checkVerticalWin(BoardPosition location, Character player) {
     int OriginX;
     int OriginY;
     OriginX = location.getColumn();
     OriginY = location.getRow();
     int numInARow = 1;

        int travelingY;
        travelingY = OriginY - 1;
        //This checks left and adds a number for each succesful loop
        while(travelingY >= 0 && gameBoard[OriginX][travelingY] == player ){
            // check as it will check again on a loop before breaking
            if(gameBoard[OriginX][travelingY] == player){
                numInARow = numInARow + 1;
            }
            travelingY = travelingY - 1;
        }
        // reset travelingX to the original X
        travelingY = OriginY + 1;
        // Check to the right of the origin
        while(travelingY < NUMROWS && gameBoard[OriginX][travelingY] == player){
            // check as it will check again on a loop before breaking
            if(gameBoard[OriginX][travelingY] == player){
                numInARow = numInARow + 1;
            }
            travelingY = travelingY + 1;
        }
        // Check if a win has occurred horizontally
        if(numInARow >= getNumToWin()){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Checks if a player has won the game based off of the newest played piece and player token
     * Checks Diagonlally for a win from top left to bottom right and top right to bottom left
     * @pre Location.xVal < MaxX  AND
     *      Location.xVal >= 0 AND
     *      Location.yVal < MaxY  AND
     *      Location.yVal >= 0
     *      Location = Last played Position
     *      Player token is a valid player
     * @post If there is a Diagonal line crossing the Location that are numToWin long then return true, else false
     * @param location - the most recent position
     * @param player - the token of the most recent player
     * @return boolean - checks if a win has occurred
     */
    public boolean checkDiagonalWin(BoardPosition location, Character player){
        int OriginX;
        int OriginY;
        OriginX = location.getColumn();
        OriginY = location.getRow();
        int numInARow;
        // This starts at one as the origin is included in the row
        numInARow = 1;
        // Check to the left under of the origin
        int travelingY;
        int travelingX;
        travelingX = OriginX - 1;
        travelingY = OriginY - 1;
        //This checks left and up adds a number for each succesful loop
        while(travelingY >= 0 && travelingX >= 0 && gameBoard[travelingX][travelingY] == player){
                // check as it will check again on a loop before breaking
                if (gameBoard[travelingX][travelingY] == player){
                    numInARow = numInARow + 1;
                }
                travelingY = travelingY - 1;
                travelingX = travelingX - 1;
        }
        // reset travelingX to the original X
        travelingY = OriginY + 1;
        travelingX = OriginX + 1;
        // Check to the right and up of the origin1
        while(travelingY < NUMROWS && travelingX <NUMCOLUMNS && gameBoard[travelingX][travelingY] == player){
            if(gameBoard[travelingX][travelingY] == player) {
                // check as it will check again on a loop before breaking
                if (gameBoard[travelingX][travelingY] == player) {
                    numInARow = numInARow + 1;
                }
                travelingY = travelingY + 1;
                travelingX = travelingX + 1;
            }
        }
        // Check if a win has occurred from Bottom Left to top right
        if(numInARow >= getNumToWin()){
            return true;
        }
        numInARow = 1;
        // going to the left and down
        travelingX = OriginX - 1;
        travelingY = OriginY + 1;

        while(travelingY < NUMROWS && travelingX > 0 && gameBoard[travelingX][travelingY] == player){
            // check as it will check again on a loop before breaking
            if (gameBoard[travelingX][travelingY] == player){
                numInARow = numInARow + 1;
            }
            travelingY = travelingY + 1;
            travelingX = travelingX - 1;
        }
        // reset travelingX to the original X
        travelingY = OriginY - 1;
        travelingX = OriginX + 1;
        // Check to the right and up of the origin1
        while(travelingY >= 0 && travelingX < NUMCOLUMNS && gameBoard[travelingX][travelingY] == player){
            if(gameBoard[travelingX][travelingY] == player) {
                // check as it will check again on a loop before breaking
                if (gameBoard[travelingX][travelingY] == player) {
                    numInARow = numInARow + 1;
                }
                travelingY = travelingY - 1;
                travelingX = travelingX + 1;
            }
        }


        // Check if a win has occurred Diagonally
        if(numInARow >= getNumToWin()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Function that returns the character at location
     * @pre Location.x < MaxX AND
     *      Location.x >= 0 AND
     *      Location.y < MaxX AND
     *      Location.y >= 0
     * @post Character = #Board[Location]
     * @param location - X,y Coordinate to check
     * @return Character - the Character at Board Position [x,y]
     */
    public Character whatsAtPos(BoardPosition location){
        int x;
        int y;
        x = location.getColumn();
        y = location.getRow();
        return gameBoard[x][y];
    }

    /**
     * Function that checks a position on the board for the requested player
     * @pre Location.x <= MaxX AND
     *      Location.y <= MaxY AND
     *      Location.x <= 0 AND
     *      Location.y <= 0
     * @post If Board[x,y] == player then true else false
     * @param location - Board position x,y
     * @param player - the player that it is checking for
     * @return true if player = location.value, else false
     */
    public boolean isPlayerAtPos(BoardPosition location, Character player){
        Character LocationVal;
        LocationVal = whatsAtPos(location);
        if(player == LocationVal){
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * function that returns the number of rows in the board
     * @post getNumRows() = #NUMROWS
     * @return int - int containing number of rows on the board
     */
    public int getNumRows(){
        return NUMROWS;
    }
    /**
     * function that returns the number of Columns in the board
     * @post getNumColumns() = #NUMCOLUMNS
     * @return int - int containing number of Columns on the board
     */
    public int getNumColumns(){
        return NUMCOLUMNS;
    }
    /**
     * function that returns the number of in a row you need to win
     * @post getNumtoWin() = #NUMTOWIN
     * @return int - int containing number you need to get in a row to win
     */
    public int getNumToWin(){
        return NUMTOWIN;
    }
}
