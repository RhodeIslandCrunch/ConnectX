package models;

import java.util.*;

public class GameBoardMem extends AbsGameBoard implements IGameBoard{
    int NUMROWS;
    int NUMCOLUMNS;
    int NUMTOWIN;
    HashMap<Character,ArrayList<BoardPosition>> gameBoardMapMem;

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
    public GameBoardMem(int rows, int columns, int winAmt){
        NUMROWS = rows;
        NUMCOLUMNS = columns;
        NUMTOWIN = winAmt;
        gameBoardMapMem = new HashMap<Character, ArrayList<BoardPosition>>();
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
        boolean free = true;

        BoardPosition topof = new BoardPosition(c,getNumRows());
        List<BoardPosition> CurrentKey;

        for(Character KeyValue : gameBoardMapMem.keySet()){
            CurrentKey = gameBoardMapMem.get(KeyValue);
            if(CurrentKey.contains(topof)){
                free = false;
            }
        }
        return free;
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
        int i = 0;
        BoardPosition FreePosition = new BoardPosition(c,firstEmptySpotY);
        boolean keyContained;
        boolean positionContained;
        ArrayList<BoardPosition> ListExtraction;

        // Iterate each key to get the list
        // then check the list for its highest value in that column
        for (Map.Entry<Character, ArrayList<BoardPosition>> hashEntry: gameBoardMapMem.entrySet()){
            ListExtraction = hashEntry.getValue();
            //Iterate each possible row within column
            while(i < NUMROWS){
                //create position for each possible column value
                FreePosition = new BoardPosition(c,i);
                //check if the list contains it
                positionContained = ListExtraction.contains(FreePosition);
                // if so then check if its the highest known space
                if(positionContained){
                    if(firstEmptySpotY < i) {
                        firstEmptySpotY = i;
                    }
                }
                i = i + 1;
            }
            i = 0;
        }
        //then make the space one above that
        firstEmptySpotY = firstEmptySpotY + 1;
        FreePosition = new BoardPosition(c, firstEmptySpotY);
        //then put the first one not found in the map
        ArrayList<BoardPosition> PosList;
        keyContained = gameBoardMapMem.containsKey(p);
        // Key is in map
        if(keyContained) {
            PosList = gameBoardMapMem.get(p);
            PosList.add(FreePosition);
            PosList.forEach(System.out::println);
        }
        // Key is not in map
        if(!keyContained) {
            PosList = new ArrayList<BoardPosition>();
            PosList.add(FreePosition);
            gameBoardMapMem.put(p,PosList);
            PosList.forEach(System.out::println);
        }
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
        int firstEmptySpotY = 0;
/*
        // check the bottom of the map
        BoardPosition LastPosition = new BoardPosition(c, 0);

        while(gameBoardMapMem.containsValue(LastPosition)){
            // Then create a new board position one above it
            firstEmptySpotY = firstEmptySpotY + 1;
            LastPosition = new BoardPosition(c, firstEmptySpotY);
        }

        BoardPosition LastLoc = new BoardPosition(c, firstEmptySpotY - 1);

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
*/
        WinCon = false;
        return WinCon;
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
        int i = 0;
        List<BoardPosition> CurrentKey;
        BoardPosition topof = new BoardPosition(0,getNumRows());

        while(i < getNumColumns()) {
            for(Character KeyValue : gameBoardMapMem.keySet()){
                CurrentKey = gameBoardMapMem.get(KeyValue);
                if (CurrentKey.contains(topof)) {
                    fullColumns = fullColumns + 1;
                }

            }
            i = i + 1;
            topof = new BoardPosition(i,getNumRows());
        }
        return fullColumns == NUMCOLUMNS;
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
        BoardPosition TravelPos;
        int numInARow;
        // This starts at 0 since we start at itself
        numInARow = 0;
        int travelingX;
        travelingX = OriginX;
        boolean contained = true;
        // Get the list
        List<BoardPosition> CurrentKey = gameBoardMapMem.get(player);
        //if so continue to check
        while(travelingX >= 0 && contained) {
            TravelPos = new BoardPosition(travelingX, OriginY);
            //first check self then
            //Check that there is one to left
            contained = CurrentKey.contains(TravelPos);
            travelingX = travelingX - 1;
            // if true add one
            if (contained) {
                numInARow = numInARow + 1;
            }
        }
        //fix it so we dont double count origin
        numInARow = numInARow - 1;
        // reset traveling x;
        travelingX = OriginX;
        while(travelingX <= NUMCOLUMNS && contained) {
            TravelPos = new BoardPosition(travelingX, OriginY);
            //first check self then
            //Check that there is one to left
            contained = CurrentKey.contains(TravelPos);
            travelingX = travelingX + 1;
            // if true add one
            if (contained) {
                numInARow = numInARow + 1;
            }
        }
        return numInARow >= getNumToWin();
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
        BoardPosition TravelPos;
        int numInARow;
        // This starts at 0 since we start at itself
        numInARow = 0;
        int travelingY;
        travelingY = OriginY;
        boolean contained = true;
        // Get the list
        List<BoardPosition> CurrentKey = gameBoardMapMem.get(player);
        //if so continue to check
        while(travelingY >= 0 && contained) {
            TravelPos = new BoardPosition(OriginX, travelingY);
            //first check self then
            //Check that there is one to left
            contained = CurrentKey.contains(TravelPos);
            travelingY = travelingY - 1;
            // if true add one
            if (contained) {
                numInARow = numInARow + 1;
            }
        }
        //fix it so we dont double count origin
        numInARow = numInARow - 1;
        // reset traveling x;
        travelingY = OriginY;
        while(travelingY <= NUMROWS && contained) {
            TravelPos = new BoardPosition(OriginX, travelingY);
            //first check self then
            //Check that there is one to left
            contained = CurrentKey.contains(TravelPos);
            travelingY = travelingY + 1;
            // if true add one
            if (contained) {
                numInARow = numInARow + 1;
            }
        }
        return numInARow >= getNumToWin();
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
        // This starts at 0 since we start at itself
        numInARow = 0;
        int travelingY;
        int travelingX;
        travelingX = OriginX;
        travelingY = OriginY;
        boolean contained = true;
        // Get the list
        List<BoardPosition> CurrentKey = gameBoardMapMem.get(player);
        BoardPosition TravelPos;

        while(travelingY >= 0 && travelingX >= 0 && contained){
            TravelPos = new BoardPosition(travelingX,travelingY);
            contained = CurrentKey.contains(TravelPos);
            travelingY = travelingY - 1;
            travelingX = travelingX - 1;
            // if true add one
            if (contained) {
                numInARow = numInARow + 1;
            }
        }
        numInARow = numInARow - 1;
        while(travelingY <= NUMROWS && travelingX <= NUMCOLUMNS && contained){
            TravelPos = new BoardPosition(travelingX,travelingY);
            contained = CurrentKey.contains(TravelPos);
            travelingY = travelingY + 1;
            travelingX = travelingX + 1;
            // if true add one
            if (contained){
                numInARow = numInARow + 1;
            }
        }
        if(numInARow >= NUMTOWIN){
            return true;
        }
        numInARow = 0;

        while(travelingY >= 0 && travelingX >= 0 && contained){
            TravelPos = new BoardPosition(travelingX,travelingY);
            contained = CurrentKey.contains(TravelPos);
            travelingY = travelingY + 1;
            travelingX = travelingX - 1;
            // if true add one
            if (contained) {
                numInARow = numInARow + 1;
            }
        }
        numInARow = numInARow - 1;
        while(travelingY <= NUMROWS && travelingX <= NUMCOLUMNS && contained){
            TravelPos = new BoardPosition(travelingX,travelingY);
            contained = CurrentKey.contains(TravelPos);
            travelingY = travelingY - 1;
            travelingX = travelingX + 1;
            // if true add one
            if (contained){
                numInARow = numInARow + 1;
            }
        }
        return numInARow >= NUMTOWIN;
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
        ArrayList<BoardPosition> CurrentKey;
        Character Owner = ' ';
        x = location.getColumn();
        y = location.getRow();
        BoardPosition Position = new BoardPosition(x,y);
        for(Character KeyValue : gameBoardMapMem.keySet()){
            CurrentKey = gameBoardMapMem.get(KeyValue);
            if (CurrentKey.contains(Position)) {
                Owner = KeyValue;
            }
        }
        return Owner;
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
        return player == LocationVal;

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
