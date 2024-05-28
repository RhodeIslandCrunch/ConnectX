package models;

public class BoardPosition {

    /**
     * Invariants:
     * xVal >= 0 AND xVal <= xMax AND
     * yVal >= 0 AND yVal <= yMax
     */
    private int xVal;
    private int yVal;

    /**
     * Constructor for a models.BoardPosition
     * @pre xVal >= 0 AND xVal <= xMax AND yVal >= 0 AND yVal <= yMax
     * @post yVal = yValue AND
     *       xVal = xValue
     * @param xValue - x coordinate for the board position
     * @param yValue - y coordinate for the board position
     */
    public BoardPosition(int xValue, int yValue){
        xVal = xValue;
        yVal = yValue;
    }

    /**
     * returns the y value of the Board Position
     * @post return = #yVal
     * @return int - x value of the board position
     */
    public int getRow(){
        return yVal;
    }

    /**
     * Returns the x value of the Board Position
     * @post return = #yVal
     * @return int - y value of the board position
     */
    public int getColumn(){
        return xVal;
    }

    /**
     * The updated equals method for board position
     * @pre compared != null
     * @post bool = true iff (this.xVal == compared.xVal) AND (this.yVal == compared.yVal)
     * @param compared - The board position for comparison
     * @return boolean - true i
     */
    public boolean equals(BoardPosition compared){
        int cY;
        int cX;
        cY = compared.getRow();
        cX = compared.getColumn();
        int sY;
        int sX;
        sX = getColumn();
        sY = getRow();
        int truth;
        truth = 0;
        if (sX == cX){
            truth = truth + 1;
        }
        if(sY == cY){
            truth = truth + 1;
        }
        if(truth == 2){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * override toString
     * @post string = "#xVal,#yVal"
     * @return string - string with the x,y coordinates
     */
    public String toString(){
        String returnedString;
        returnedString = ""+ getColumn() +"" + getRow();
        return returnedString;
    }
}
