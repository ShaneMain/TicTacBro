import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;


public class Player {
    public int character;
    public String name;

    public Player(int i) {
        character = i;
        name = "Player " + i;
    }

    /**
     * Collects user input of the desired turn location, ensures it is valid, and updates the game board
     * @param gameBoard GameBoard the main gameboard
     * @return Dictionary validated coordinates of the desired move
     */
    public Dictionary<String, Integer> takeTurn(GameBoard gameBoard){
        Dictionary<String, Integer> turn = new Hashtable<>();

        int row = promptInput(String.format("%1$s turn. Enter the row of your move",name));
        turn.put("row", row-1);

        int column = promptInput(String.format("%1$s turn. Enter the column of your move",name));
        turn.put("column", column-1);
        if(gameBoard.a[column-1][row-1] != 0) {
            System.out.println("Invalid input. Try again.");
            return takeTurn(gameBoard);
        }
        return turn;
    }


    /**
     * Loop to ensure player inputs a valid
     * @param text str prompt before asking for player input
     * @return int validated input
     */
    private int promptInput(String text){
        Scanner sc= new Scanner(System.in);
        int finalInput = 0;
        boolean validInput = false;

        while(validInput == false){
            System.out.println(text);
            finalInput = sc.nextInt();
            validInput = validateInput(finalInput);
        }
        sc.close();
        return finalInput;
    }

    /**
     * Ensures that the input provided is going to be valid
     * @param input int player inputted int
     * @return bool if input is valid
     */
    private Boolean validateInput(int input){
        if(input < 1 || input > 3){
            System.out.println("Invalid input. Try again.");
            return false;
        }
        return true;
    }
}
