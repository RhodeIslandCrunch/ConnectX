package ExtendedConnectX.src.models;

import models.BoardPosition;
import models.GameBoard;
import models.IGameBoard;
import org.junit.Test;

public class TestGameBoard {
    @Test
    public void GameBoardConstructor_Min() {
        //min size board is a 3x3 with 3 to win
        IGameBoard gb = new GameBoard(3, 3, 3);
        Character[][] Expected = new Character[3][3];

        int i;
        int j;
        i = 0;
        j = 0;
        //create empty array
        while (i < 3) {
            while (j < 3) {
                Expected[i][j] = ' ';
                j = j + 1;
            }
            i = i + 1;
            j = 0;
        }
        String expected = StringMaker(Expected, 3, 3);
        String Actual = gb.toString();
        assert (expected.equals(Actual));
    }

    @Test
    public void GameBoardConstructor_Max() {
        //max size is 100 100 25
        IGameBoard gb = new GameBoard(100, 100, 25);
        Character[][] Expected = new Character[100][100];
        int i;
        int j;
        i = 0;
        j = 0;
        //create empty array
        while (i < 100) {
            while (j < 100) {
                Expected[i][j] = ' ';
                j = j + 1;
            }
            i = i + 1;
            j = 0;
        }
        String expected = StringMaker(Expected, 100, 100);
        String Actual = gb.toString();
        assert (expected.equals(Actual));
    }

    @Test
    public void GameBoardConstructor_Random() {
        //max size is 100 100 25
        IGameBoard gb = new GameBoard(5, 5, 4);
        Character[][] Expected = new Character[5][5];
        int i;
        int j;
        i = 0;
        j = 0;
        //create empty array
        while (i < 5) {
            while (j < 5) {
                Expected[i][j] = ' ';
                j = j + 1;
            }
            i = i + 1;
            j = 0;
        }
        String expected = StringMaker(Expected, 5, 5);
        String Actual = gb.toString();
        assert (expected.equals(Actual));

    }

    @Test
    public void GameBoardCheckIfFree_IsFree() {
        IGameBoard gb = new GameBoard(5, 5, 3);
        boolean free;
        free = gb.checkIfFree(0);

        assert (free);
    }

    @Test
    public void GameBoardCheckIfFree_NotFree() {
        IGameBoard gb = new GameBoard(4, 4, 3);
        boolean free;
        int i = 0;
        while (i < 4) {
            gb.placeToken('p', 0);
            i = i + 1;
        }
        free = gb.checkIfFree(0);

        assert (!free);
    }

    @Test
    public void GameBoardCheckIfFree_LastSpace() {
        IGameBoard gb = new GameBoard(4, 4, 3);
        boolean Actual;

        int i = 0;
        while (i < 3) {
            gb.placeToken('p', 0);
            i = i + 1;
        }

        Actual = gb.checkIfFree(0);

        assert (Actual);
    }
    @Test
    public void GameBoardHorizontalWin_YesNoEdge() {
        //min size board is a 3x3 with 3 to win
        int size = 5;
        IGameBoard gb = new GameBoard(size, size, 3);

        int i;
        Character p = 'p';
        i = 1;

        while(i<3) {
            gb.placeToken(p, i);

            i = i + 1;
        }
        boolean Actual;
        BoardPosition check = new BoardPosition(3,0);
        Actual = gb.checkHorizontalWin(check,p);


        assert (Actual);

    }
    @Test
    public void GameBoardHorizontalWin_YesLeftEdge() {
        //min size board is a 3x3 with 3 to win
        IGameBoard gb = new GameBoard(5, 5, 3);

        int i;
        Character p = 'p';
        i = 0;
        while(i<3) {
            gb.placeToken(p, i);
            i = i + 1;
        }
        boolean Actual;
        BoardPosition check = new BoardPosition(0,0);
        Actual = gb.checkHorizontalWin(check,p);

        assert (Actual);
    }
    @Test
    public void GameBoardHorizontalWin_YesRightEdge() {
        //min size board is a 3x3 with 3 to win
        IGameBoard gb = new GameBoard(5, 5, 3);

        int i;
        Character p = 'p';
        i = 1;
        while(i<4) {
            gb.placeToken(p, i);
            i = i + 1;
        }
        boolean Actual;
        BoardPosition check = new BoardPosition(4,0);
        Actual = gb.checkHorizontalWin(check,p);

        assert (Actual);
    }
    @Test
    public void GameBoardHorizontalWin_No() {
        //min size board is a 3x3 with 3 to win
        IGameBoard gb = new GameBoard(5, 5, 3);


        Character p = 'p';
        boolean Actual;
        BoardPosition check = new BoardPosition(0,0);
        Actual = gb.checkHorizontalWin(check,p);

        assert (!Actual);
    }
    @Test
    public void GameBoardVertWin_No() {
        //min size board is a 3x3 with 3 to win
        IGameBoard gb = new GameBoard(5, 5, 3);
        Character p = 'p';
        boolean Actual;
        BoardPosition check = new BoardPosition(0,0);
        Actual = gb.checkVerticalWin(check,p);

        assert (!Actual);
    }
    @Test
    public void GameBoardVertWin_Yes() {
        //min size board is a 3x3 with 3 to win
        IGameBoard gb = new GameBoard(5, 5, 3);


        int i;

        Character p = 'p';

        i = 1;
        gb.placeToken('a',3);
        while(i<4) {
            gb.placeToken(p, 3);

            i = i + 1;
        }
        System.out.println(gb.toString());
        boolean Actual;
        BoardPosition check = new BoardPosition(3,3);
        System.out.println(gb.whatsAtPos(check));
        Actual = gb.checkVerticalWin(check,p);

        assert (Actual);
    }
    @Test
    public void GameBoardVertWin_Top() {
        //min size board is a 3x3 with 3 to win
        IGameBoard gb = new GameBoard(5, 5, 3);


        int i;

        Character p = 'p';

        i = 1;
        gb.placeToken('a',3);
        while(i<5) {
            gb.placeToken(p, 3);

            i = i + 1;
        }
        System.out.println(gb.toString());
        boolean Actual;
        BoardPosition check = new BoardPosition(3,4);
        System.out.println(gb.whatsAtPos(check));
        Actual = gb.checkVerticalWin(check,p);

        assert (Actual);
    }
    @Test
    public void GameBoardVertWin_Bottom() {
        //min size board is a 3x3 with 3 to win
        IGameBoard gb = new GameBoard(5, 5, 3);


        int i;

        Character p = 'p';

        i = 1;
        while(i<4) {
            gb.placeToken(p, 3);
            i = i + 1;
        }
        System.out.println(gb.toString());
        boolean Actual;
        BoardPosition check = new BoardPosition(3,0);
        System.out.println(gb.whatsAtPos(check));
        Actual = gb.checkVerticalWin(check,p);

        assert (Actual);
    }

    @Test
    public void GameBoardDiagWin_No() {
        //min size board is a 3x3 with 3 to win
        IGameBoard gb = new GameBoard(5, 5, 3);

        Character p = 'p';
        boolean Actual;
        BoardPosition check = new BoardPosition(0,0);
        Actual = gb.checkDiagonalWin(check,p);

        assert (!Actual);
    }
    @Test
    // Upper left to bottom right
    public void GameBoardDiagWin_Yes_ULDR() {
        IGameBoard gb = new GameBoard(5, 5, 3);

        Character p = 'p';

        gb.placeToken(p,1);
        gb.placeToken(p,2);
        gb.placeToken(p,2);
        gb.placeToken(p,3);
        gb.placeToken(p,3);
        gb.placeToken(p,3);

        boolean Actual;
        BoardPosition check = new BoardPosition(1,0);

        System.out.println(gb.whatsAtPos(check));
        System.out.println(gb.toString());

        Actual = gb.checkDiagonalWin(check,p);

        assert (Actual);
    }
    @Test
    // Diagonal Win upper right to bottom left
    public void GameBoardDiagWin_Yes_URBL() {
        IGameBoard gb = new GameBoard(5, 5, 3);

        Character p = 'p';
        //create empty array

        gb.placeToken(p,3);
        gb.placeToken(p,2);
        gb.placeToken(p,2);
        gb.placeToken(p,1);
        gb.placeToken(p,1);
        gb.placeToken(p,1);

        boolean Actual;
        BoardPosition check = new BoardPosition(3,0);

        System.out.println(gb.whatsAtPos(check));
        System.out.println(gb.toString());

        Actual = gb.checkDiagonalWin(check,p);

        assert (Actual);
    }
    @Test
    public void GameBoardDiagWin_YesTopLeft_ULDR() {
        IGameBoard gb = new GameBoard(5, 5, 3);


        Character p = 'p';

        gb.placeToken(p,0);
        gb.placeToken(p,0);
        gb.placeToken(p,0);
        gb.placeToken(p,0);
        gb.placeToken(p,0);
        gb.placeToken(p,1);
        gb.placeToken(p,1);
        gb.placeToken(p,1);
        gb.placeToken(p,1);
        gb.placeToken(p,2);
        gb.placeToken(p,2);
        gb.placeToken(p,2);

        boolean Actual;
        BoardPosition check = new BoardPosition(0,4);

        System.out.println(gb.whatsAtPos(check));
        System.out.println(gb.toString());

        Actual = gb.checkDiagonalWin(check,p);

        assert (Actual);
    }
    @Test
    // Diagonal Win upper right to bottom left
    public void GameBoardDiagWin_YesTopRight_URBL() {
        IGameBoard gb = new GameBoard(5, 5, 3);

        Character p = 'p';

        gb.placeToken(p,4);
        gb.placeToken(p,4);
        gb.placeToken(p,4);
        gb.placeToken(p,4);
        gb.placeToken(p,4);
        gb.placeToken(p,3);
        gb.placeToken(p,3);
        gb.placeToken(p,3);
        gb.placeToken(p,3);
        gb.placeToken(p,2);
        gb.placeToken(p,2);
        gb.placeToken(p,2);

        boolean Actual;
        BoardPosition check = new BoardPosition(4,4);

        System.out.println(gb.whatsAtPos(check));
        System.out.println(gb.toString());

        Actual = gb.checkDiagonalWin(check,p);

        assert (Actual);
    }

    @Test
    public void     GameBoardDiagWin_YesBottomLeft_ULDR() {
        IGameBoard gb = new GameBoard(5, 5, 3);

        Character p = 'p';

        gb.placeToken(p,0);
        gb.placeToken(p,1);
        gb.placeToken(p,1);
        gb.placeToken(p,2);
        gb.placeToken(p,2);
        gb.placeToken(p,2);

        boolean Actual;
        BoardPosition check = new BoardPosition(0,0);

        System.out.println(gb.whatsAtPos(check));
        System.out.println(gb.toString());

        Actual = gb.checkDiagonalWin(check,p);

        assert (Actual);
    }
    @Test
    // Diagonal Win upper right to bottom left
    public void GameBoardDiagWin_YesBottomRight_URBL() {
        IGameBoard gb = new GameBoard(5, 5, 3);

        Character p = 'p';

        gb.placeToken(p,4);
        gb.placeToken(p,3);
        gb.placeToken(p,3);
        gb.placeToken(p,2);
        gb.placeToken(p,2);
        gb.placeToken(p,2);

        boolean Actual;
        BoardPosition check = new BoardPosition(4,0);

        System.out.println(gb.whatsAtPos(check));
        System.out.println(gb.toString());

        Actual = gb.checkDiagonalWin(check,p);

        assert (Actual);
    }

    @Test
    // Diagonal Win upper right to bottom left
    public void GameBoardTie_No_0Tokens() {
        IGameBoard gb = new GameBoard(3, 3, 3);


        //create empty array

        boolean Actual;

        System.out.println(gb.toString());

        Actual = gb.checkTie();

        assert (!Actual);
    }
    @Test
    public void GameBoardTie_No_SomeTokens() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character p = 'p';


        boolean Actual;

        gb.placeToken(p,0);

        System.out.println(gb.toString());

        Actual = gb.checkTie();

        assert (!Actual);
    }
    @Test
    public void GameBoardTie_No_ColumnsFull() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character p = 'p';


        boolean Actual;

        gb.placeToken(p,0);
        gb.placeToken(p,0);
        gb.placeToken(p,0);
        gb.placeToken(p,1);
        gb.placeToken(p,1);
        gb.placeToken(p,1);
        gb.placeToken(p,2);
        gb.placeToken(p,2);

        System.out.println(gb.toString());

        Actual = gb.checkTie();

        assert (!Actual);
    }
    @Test
    public void GameBoardTie_Yes() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character p = 'p';

        boolean Actual;

        gb.placeToken(p,0);
        gb.placeToken(p,0);
        gb.placeToken(p,0);
        gb.placeToken(p,1);
        gb.placeToken(p,1);
        gb.placeToken(p,1);
        gb.placeToken(p,2);
        gb.placeToken(p,2);
        gb.placeToken(p,2);

        System.out.println(gb.toString());

        Actual = gb.checkTie();

        assert (Actual);
    }

    @Test
    public void GameBoardIsPlayerAtPos_No_Nothing() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character p = 'p';


        boolean Actual;

        BoardPosition check = new BoardPosition(0,0);
        Actual = gb.isPlayerAtPos(check,p);

        System.out.println(gb.toString());

        assert (!Actual);
    }
    @Test
    public void GameBoardIsPlayerAtPos_No_Something() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character p = 'p';

        boolean Actual;

        BoardPosition check = new BoardPosition(0,0);
        gb.placeToken('x',0);
        Actual = gb.isPlayerAtPos(check,p);

        System.out.println(gb.toString());

        assert (!Actual);
    }
    @Test
    public void GameBoardIsPlayerAtPos_Yes_() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character p = 'p';

        boolean Actual;

        BoardPosition check = new BoardPosition(1,0);
        gb.placeToken(p,1);
        Actual = gb.isPlayerAtPos(check,p);

        System.out.println(gb.toString());

        assert (Actual);
    }
    @Test
    public void GameBoardIsPlayerAtPos_Yes_BottomLeft() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character p = 'p';
        boolean Actual;

        BoardPosition check = new BoardPosition(0,0);
        gb.placeToken(p,0);
        Actual = gb.isPlayerAtPos(check,p);

        System.out.println(gb.toString());

        assert (Actual);
    }
    @Test
    public void GameBoardIsPlayerAtPos_Yes_TopRight() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character p = 'p';

        boolean Actual;

        BoardPosition check = new BoardPosition(2,2);
        gb.placeToken(p,2);
        gb.placeToken(p,2);
        gb.placeToken(p,2);

        Actual = gb.isPlayerAtPos(check,p);

        System.out.println(gb.toString());

        assert (Actual);
    }
    @Test
    public void GameBoardIsWhatsAtPos_Nothing() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character p = 'p';

        boolean Actual = false;

        BoardPosition check = new BoardPosition(1,1);

        Character Expected;
        Expected = ' ';
        Character value;
        value = gb.whatsAtPos(check);
        if(Expected.equals(value)){
            Actual = true;
        }

        System.out.println(gb.toString());

        assert (Actual);
    }
    @Test
    public void GameBoardIsWhatsAtPos_Something() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character p = 'p';

        boolean Actual = false;

        BoardPosition check = new BoardPosition(1,1);

        Character Expected;
        Expected = p;

        gb.placeToken(p,1);
        gb.placeToken(p,1);

        Character value;
        value = gb.whatsAtPos(check);
        if(Expected.equals(value)){
            Actual = true;
        }

        System.out.println(gb.toString());

        assert (Actual);
    }
    @Test
    public void GameBoardIsWhatsAtPos_Origin() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character p = 'p';

        boolean Actual = false;

        BoardPosition check = new BoardPosition(0,0);

        Character Expected;
        Expected = p;

        gb.placeToken(p,0);

        Character value;
        value = gb.whatsAtPos(check);
        if(Expected.equals(value)){
            Actual = true;
        }

        System.out.println(gb.toString());

        assert (Actual);
    }
    @Test
    public void GameBoardIsWhatsAtPos_Top() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character p = 'p';

        boolean Actual = false;

        BoardPosition check = new BoardPosition(1,2);

        Character Expected;
        Expected = p;

        gb.placeToken(p,1);
        gb.placeToken(p,1);
        gb.placeToken(p,1);

        Character value;
        value = gb.whatsAtPos(check);
        if(Expected.equals(value)){
            Actual = true;
        }

        System.out.println(gb.toString());

        assert (Actual);
    }
    @Test
    public void GameBoardIsWhatsAtPos_Right() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character p = 'p';

        boolean Actual = false;

        BoardPosition check = new BoardPosition(2,0);

        Character Expected;
        Expected = p;

        gb.placeToken(p,2);

        Character value;
        value = gb.whatsAtPos(check);
        if(Expected.equals(value)){
            Actual = true;
        }

        System.out.println(gb.toString());

        assert (Actual);
    }

    @Test
    public void GameBoardPlaceToken_Top() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character[][] Expected = new Character[3][3];
        Character p = 'p';

        int i;
        int j;
        i = 0;
        j = 0;
        //create empty array
        while (i < 3) {
            while (j < 3) {
                Expected[i][j] = ' ';
                j = j + 1;
            }
            i = i + 1;
            j = 0;
        }

        i = 0;
        while(i<3){
            Expected[i][1] = p;
            gb.placeToken(p,1);
            i = i + 1;
        }

        String expected = StringMaker(Expected, 3, 3);
        String Actual = gb.toString();


        System.out.println(gb.toString());
        System.out.println(expected);

        assert (expected.equals(Actual));
    }
    @Test
    public void GameBoardPlaceToken_allFull() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character[][] Expected = new Character[3][3];
        Character p = 'p';

        int i;
        int j;
        i = 0;
        j = 0;
        //create empty array
        while (i < 3) {
            while (j < 3) {
                Expected[i][j] = ' ';
                j = j + 1;
            }
            i = i + 1;
            j = 0;
        }

        i = 0;
        while(i<3){
            Expected[i][1] = p;
            gb.placeToken(p,1);
            i = i + 1;
        }

        String expected = StringMaker(Expected, 3, 3);
        String Actual = gb.toString();


        System.out.println(gb.toString());
        System.out.println(expected);

        assert (expected.equals(Actual));
    }
    @Test
    public void GameBoardPlaceToken_bottom() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character[][] Expected = new Character[3][3];
        Character p = 'p';

        int i;
        int j;
        i = 0;
        j = 0;
        //create empty array
        while (i < 3) {
            while (j < 3) {
                Expected[i][j] = ' ';
                j = j + 1;
            }
            i = i + 1;
            j = 0;
        }

        i = 0;
        Expected[i][1] = p;
        gb.placeToken(p,1);


        String expected = StringMaker(Expected, 3, 3);
        String Actual = gb.toString();


        System.out.println(gb.toString());
        System.out.println(expected);

        assert (expected.equals(Actual));
    }
    @Test
    public void GameBoardPlaceToken_Multiple() {
        IGameBoard gb = new GameBoard(3, 3, 3);

        Character[][] Expected = new Character[3][3];
        Character p = 'p';

        int i;
        int j;
        i = 0;
        j = 0;
        //create empty array
        while (i < 3) {
            while (j < 3) {
                Expected[i][j] = ' ';
                j = j + 1;
            }
            i = i + 1;
            j = 0;
        }

        i = 0;
        while(i<2){
            Expected[i][1] = p;
            gb.placeToken(p,1);
            i = i + 1;
        }

        String expected = StringMaker(Expected, 3, 3);
        String Actual = gb.toString();


        System.out.println(gb.toString());
        System.out.println(expected);

        assert (expected.equals(Actual));
    }



    //String Function
    private String StringMaker(Character[][] Input, int rows, int columns){
        String returnedString;

        returnedString = "|";
        int i = 0;
        while(i < columns){
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
        i = rows - 1;
        int j = 0;
        while(i >= 0){
            returnedString = returnedString + "|";
            while(j < columns){
                returnedString = returnedString + Input[i][j] + " " +"|";
                j = j + 1;
            }
            j = 0;
            i = i - 1;
            returnedString = returnedString + "\n";
        }

        return returnedString;
    }
}
