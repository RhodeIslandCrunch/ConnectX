package models;

import java.util.Scanner;

public class GameScreen {
    public static void main(String[] args) {
        String CurrentPlayers = "";
        // variable to hold max players for reference
        int MAXPLAYERS = 10;
        int MINPLAYERS = 2;
        int MAXLENGTH = 10;
        int MINLENGTH = 3;
        int numColumns;
        int numRows;
        int numWin;
        int numPlayers = 0;
        int userInput = 999;
        String stringInput = new String();
        int PlayerVal = 0;
        IGameBoard CurrentGame = new GameBoard(5,5,5);
        int turnCount = 0;
        boolean replayValue = true;
        boolean validation;
        boolean winStatus;
        boolean promptRestart = false;
        boolean validRestartInput;
        boolean newGame = true;
        int currentPlayer = 0;
        int i = 0;
        int j = 0;
        int lowestSize;
        char charInput = 'z';


        //Scanner
        Scanner UserInputScanner = new Scanner(System.in);

        while (replayValue){
            if(newGame){
                turnCount = 0;

                validation = false;
                // Number of Players
                while(!validation) {
                    System.out.println("How Many Players?");
                    userInput = UserInputScanner.nextInt();

                    if(userInput <= MAXPLAYERS && userInput >= MINPLAYERS){
                        validation = true;
                    }
                    if(userInput > MAXPLAYERS){
                        System.out.println("Must be " + MAXPLAYERS + "or fewer");
                    }
                    if(userInput < MINPLAYERS){
                        System.out.println("Must be at least" + MINPLAYERS + "players");
                    }
                }
                numPlayers = userInput;
                // Player Characters
                while(i < numPlayers){
                    validation = false;
                    while(!validation){
                        System.out.print("Enter the character to represent player ");
                        System.out.println(i + 1);
                        // Obtain Input
                        stringInput = UserInputScanner.next(".");
                        charInput = stringInput.charAt(0);
                        // Validate it as unused
                        j = 0;
                        validation = true;
                        while (j < i) {
                            if (CurrentPlayers.charAt(j) == charInput) {
                                validation = false;
                            }
                            j = j + 1;
                        }
                        if(!validation){
                            System.out.print(charInput);
                            System.out.println(" is already taken as a player token!");
                        }
                    }
                    CurrentPlayers = CurrentPlayers + charInput;
                    i = i + 1;
                }
                // How many rows
                validation = false;
                while(!validation) {
                    System.out.println("How many rows should be on the board?");
                    userInput = UserInputScanner.nextInt();

                    if(userInput <= MAXLENGTH && userInput >= MINLENGTH){
                        validation = true;
                    }
                    if(userInput > MAXLENGTH){
                        System.out.println(userInput);
                        System.out.println(" is too large of a length for this, must be under " + MAXLENGTH);
                    }
                    if(userInput < MINLENGTH){
                        System.out.println(userInput);
                        System.out.println(" is too small of a length for this, must be over "+ MINLENGTH);
                    }
                }
                numRows = userInput;

                // columns
                validation = false;
                while(!validation) {
                    System.out.println("How many columns should be on the board?");
                    userInput = UserInputScanner.nextInt();

                    if(userInput <= MAXLENGTH && userInput >= MINLENGTH){
                        validation = true;
                    }
                    if(userInput > MAXLENGTH){
                        System.out.println(userInput);
                        System.out.println(" is too large of a length for this, must be under " + MAXLENGTH);
                    }
                    if(userInput < MINLENGTH){
                        System.out.println(userInput);
                        System.out.println(" is too small of a length for this, must be over "+ MINLENGTH);
                    }
                }
                numColumns = userInput;
                // number to win
                validation = false;
                while(!validation) {
                    System.out.println("How many in a row to win?");
                    userInput = UserInputScanner.nextInt();
                    lowestSize = numColumns;
                    if(lowestSize > numRows){
                        lowestSize = numRows;
                    }
                    if(userInput <= MAXLENGTH && userInput >= MINLENGTH && userInput <= lowestSize){
                        validation = true;
                    }
                    if(userInput > lowestSize){
                        System.out.println(userInput);
                        System.out.println(" is too large of a length for this, must be under " + lowestSize);
                    }
                    if(userInput < MINLENGTH){
                        System.out.println(userInput);
                        System.out.println(" is too small of a length for this, must be over or equal to "+ MINLENGTH);
                    }
                }
                numWin = userInput;
                //construct new game
                validation = false;
                while(!validation) {
                    System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)? ");
                    stringInput = UserInputScanner.next();
                    charInput = stringInput.charAt(0);
                    if(charInput == 'F' || charInput == 'f'){
                        CurrentGame = new GameBoard(numRows, numColumns, numWin);
                        validation = true;
                    }
                    if(charInput == 'M' || charInput == 'm'){
                        CurrentGame = new GameBoardMem(numRows, numColumns, numWin);
                        validation = true;
                    }
                    if(!validation){
                        System.out.println("Invalid input");
                    }
                }
                newGame = false;
                turnCount = 0;
            }
            // Print the state
            String State = CurrentGame.toString();
            System.out.println(State);
            //set it to false for each user input check
            validation = false;
            while (!validation) {
                currentPlayer = currentPlayer + 1;
                //Print the players value (This sets it to the string but plus one to get the non computer value
                System.out.print("Player " + ((turnCount % numPlayers) + 1));

                System.out.println(", what column do you want to place your marker in?");
                // Use scanner to look for next int
                userInput = UserInputScanner.nextInt();
                // check if input is valid
                validation = true;
                if (userInput < 0) {
                    System.out.println("Column cannot be less than 0");
                    validation = false;
                }
                if (userInput > CurrentGame.getNumColumns() - 1) {
                    System.out.println("Column cannot be greater then " + CurrentGame.getNumColumns());
                    validation = false;
                }
                boolean freed;
                if(validation) {
                    freed = CurrentGame.checkIfFree(userInput);
                    if (!freed) {
                        System.out.println("Column is full");
                        validation = false;
                    }
                }
            }
            // should now have a validated input variable
            // place the token

            CurrentGame.placeToken(CurrentPlayers.charAt(turnCount % numPlayers), userInput);
            // Check for a win
            winStatus = CurrentGame.checkForWin(userInput, CurrentPlayers.charAt(turnCount % numPlayers));
            if (winStatus){
                System.out.print("Player " + CurrentPlayers.charAt(turnCount % numPlayers));
                System.out.println(" Won!");
                promptRestart = true;
            } else if (CurrentGame.checkTie()) {
                System.out.println("You have drawn the game");
                promptRestart = true;
            }
            if (promptRestart) {
                //set false to force a validated input
                validRestartInput = false;
                while (!validRestartInput) {
                    System.out.println("Would you like to play again? Y/N");
                    stringInput = UserInputScanner.next();
                    charInput = stringInput.charAt(0);
                    if (charInput == 'n' || charInput == 'N' || charInput == 'y' || charInput == 'Y') {
                        validRestartInput = true;
                    }
                }
                if (stringInput.charAt(0) == 'n' || stringInput.charAt(0) == 'N') {
                    replayValue = false;
                }
                if (stringInput.charAt(0) == 'y' || stringInput.charAt(0) == 'Y') {
                    promptRestart = false;
                    newGame = true;
                }
            }
            //Change the player currently Playing
            if (PlayerVal == numPlayers - 1){
                PlayerVal = 0;
                currentPlayer = 0;
            } else {
                PlayerVal = PlayerVal + 1;
                currentPlayer = currentPlayer + 1;
            }
            turnCount = turnCount + 1;
        }
    }
}
