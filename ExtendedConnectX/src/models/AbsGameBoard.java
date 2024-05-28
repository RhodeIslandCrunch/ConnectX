package models;

public abstract class AbsGameBoard implements IGameBoard{
    /**
     * Function that returns a string containing the state of the game-board
     * @pre self has been initialized
     * @post Gameboard = #Gameboard
     * @return string - string that contains a formatted version of the game-board
     */
    public String toString(){
        String returnedString;

        returnedString = "|";
        int i = 0;
        while(i< getNumColumns()){
            if(i < 10){
                returnedString = returnedString + " ";
                returnedString = returnedString + i;
                returnedString = returnedString + "|";
            }
            else{
               returnedString = returnedString + i;
               returnedString = returnedString + "|";
            }
            i = i + 1;
        }
        returnedString = returnedString + "\n";
        i = getNumRows() - 1;
        int j = 0;
        while(i >= 0){
            returnedString = returnedString + "|";
            while(j < getNumColumns()){
                BoardPosition check = new BoardPosition(j,i);
                returnedString = returnedString + whatsAtPos(check) + " " +"|";
                j = j + 1;
            }
            j = 0;
            i = i - 1;
            returnedString = returnedString + "\n";
        }

        return returnedString;
    }
}
