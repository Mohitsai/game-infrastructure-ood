import java.util.Collections;

public class Board {
    //This class is responsible for all the properties and methods of a board in a board game.

    private int size;

    int[][] rows; //an array to store the mark status of players in rows of the board
    int[][] cols; //an array to store the mark status of players in columns of the board
    int[][] diagonals; //an array to store the mark status of players in diagonals of the board

    private boolean won = false; //a variable to set the win status of a board after checking for winner

    Cell[][] board; //Board in a board game is nothing but a two-dimensional array of cells

    public int getSize() {
        return size;
    }

    public Cell[][] getBoard() {
        return this.board;
    }

    public void setBoard(int size) {
        //set up an empty board (all cells with empty marks) at the start of a game.

        this.board = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell(new Mark(' '));
            }
        }
    }

    public void setWinnerMark(Mark mark){
        //Set all the cells in the board to the mark of the winner of that board
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j].setMark(mark);
            }
        }
    }

    public Board(int size) {
        this.size = size;
        setBoard(size);
        this.rows = new int[3][size];
        this.cols = new int[3][size];
        this.diagonals = new int[3][2];
    }

    public Board() {
    }

    public boolean isFull(){
        //check if a board is full
        boolean flag = false;
        int count = 0;
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(board[i][j].getMark()==' '){
                    count++;
                }
            }
        }
        if(count==0){
            flag = true;
        }
        return flag;
    }

    public boolean hasWon(){
        return won;
    }

    public void setWin(){
        this.won = true;
    }

    public Cell getCell(int i, int j){
        return board[i][j];
    }

    public void printBoard() {
        // Print a board that looks like the one in the assignment requirements file
        // Prints board as per the size desired by the player

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("+---");
            }
            System.out.println("+");

            printRow(i);
            System.out.printf("\n");
        }

        for (int j = 0; j < size; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

    public int checkWinner(int row, int col, int playerID) {
        if (++rows[playerID][row] == size) {
            return playerID;
        }
        if (++cols[playerID][col] == size) {
            return playerID;
        }
        if (row == col && ++diagonals[playerID][0] == size) {
            return playerID;
        }
        if ((row + col == size - 1) && ++diagonals[playerID][1] == size) {
            return playerID;
        }

        return -1;
    }

    public void printRow(int row){
        //prints a row of the board
        for (int j = 0; j < size; j++) {
            Cell cell = board[row][j];
            System.out.print("| " + cell.getMark() + " ");
        }
        System.out.print("|");
    }
}