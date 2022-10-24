import java.util.Dictionary;

public class TicTacGame {
    Player player1 = new Player(1);
    Player player2 = new Player(2);
    public void start(){
        gameLoop();
    }

    /**
     * Main game logic
     */
    private void gameLoop() {
        GameBoard gameBoard = new GameBoard();
        boolean winstate = false;
        gameBoard.printBoard();
        while (!winstate) {
            Dictionary player1Turn = player1.takeTurn(gameBoard);
            winstate = gameBoard.SetValue((Integer) player1Turn.get("row"), (Integer) player1Turn.get("column"), player1);
            if(winstate){break;}
            Dictionary player2Turn = player2.takeTurn(gameBoard);
            winstate = gameBoard.SetValue((Integer) player2Turn.get("row"), (Integer) player2Turn.get("column"), player2);
        }
        gameLoop();
    }
}
