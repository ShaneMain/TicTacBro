import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;


public class GameBoard {
    int[][] a = new int[3][3];
    private JButton[][] buttons = new JButton[3][3];
    

    public GameBoard(){
        super("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        add(panel);
        Font font = new Font("Arial", Font.BOLD, 50);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(font);
                panel.add(buttons[i][j]);
            }
        }
    }


    /**
     * Prints out the gameboard
     */
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
            switch (a[i][j]) {
              case 1:
                buttons[i][j].setText("X");
                break;
              case 2:
                buttons[i][j].setText("O");
                break;
              default:
                buttons[i][j].setText("");
                break;
        }
    }

    /**
     * Gets passed a coordinate set and the player setting the coordinate, and updates the gameboard
     * @param column the vertical value to bet set
     * @param row the horizontal value to be set
     * @param player player object
     * @return bool true if someone wins
     */
    public boolean SetValue(int column, int row, Player player){
        a[row][column] = player.character;
        this.printBoard();
        return getWinState(player.character, player.name);
    }

    /**
     * Checks the board to see if the player won
     * @param value int player's space value ie 1
     * @param name str player name
     * @return bool true if the player wins
     */
    public boolean getWinState(int value, String name){
        List<Integer> diagValues1 = Arrays.asList(a[0][0], a[1][1], a[2][2]);
        List<Integer> diagValues2 = Arrays.asList(a[0][2], a[1][1], a[2][0]);
        if(diagChecker(diagValues1, value) || diagChecker(diagValues2, value)){
            System.out.printf("%1$s wins!\n",name);
            return true;
        }


        for (int i = 0; i < a.length; i++) {
            List<Integer> currentRow = new ArrayList<>();
            List<Integer> currentCol = new ArrayList<>();
            for (int j = 0; j < a[i].length; j++) {
                currentRow.add(a[i][j]);
                currentCol.add(a[j][i]);
            }
            //Check row for a win
            int rowCounter = 0;
            int colCounter = 0;


            for (int r = 0; r < currentRow.size(); r++) {
                if (currentRow.get(r) == value) {
                    rowCounter++;
                }
                if (currentCol.get(r) == value) {
                    colCounter++;
                }
                if (rowCounter == a.length || colCounter == a.length) {
                    System.out.printf("%1$s wins!\n",name);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check a given set of coordinates for them all to match the given value
     * @param diag List set of coordinates
     * @param value int value of the current player
     * @return bool true if there is a winning diagonal state
     */
    private Boolean diagChecker(List<Integer> diag, int value){
        int diagCounter = 0;
        for (Integer integer : diag) {
            if (integer == value) {
                diagCounter++;
            }
        }
        return diagCounter == diag.size();
    }

}

