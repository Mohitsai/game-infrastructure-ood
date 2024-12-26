import java.util.Collections;
import java.util.Scanner;

public class SuperBoard extends Board {

    Board[][] superBoard; //A super board is considered to be a 2-dimensional array of boards

    int[][] superRows; //an array to store the mark status of players in rows of the superBoard

    int[][] superCols; //an array to store the mark status of players in cols of the superBoard

    int[][] superDiagonals; //an array to store the mark status of players in diagonals of the superBoard

    private int size;

    private Scanner sc;

    public void setSuperBoard(int size){
        //set up an empty board (all cells with empty marks) at the start of a game.
        this.superBoard = new Board[size][size];
        for (int i=0; i < size; i++) {
            for(int j=0; j<size; j++){
                superBoard[i][j] = new Board(size);
            }
        }
        this.superRows = new int[3][size];
        this.superCols = new int[3][size];
        this.superDiagonals = new int[3][2];
    }

    public SuperBoard(int size){
        this.size = size;
        setSuperBoard(size);
    }

    public void printSuperBoard() {
        // Print a board that looks like the one in the assignment requirements file
        // Prints board as per the size desired by the player

        StringBuffer subBoardBorder = new StringBuffer();
        StringBuffer superBoardBorder = new StringBuffer();
        subBoardBorder.append(String.join("", Collections.nCopies(size, "+---")));
        subBoardBorder.append("+");
        superBoardBorder.append(String.join("  ", Collections.nCopies(size, subBoardBorder)));
        for(int i=0; i<size; i++){
            System.out.println(superBoardBorder);
            for(int j=0; j<size; j++){
                for(int k=0; k<size; k++) {
                    superBoard[i][k].printRow(j);
                    System.out.print("  ");
                }System.out.println("");
                System.out.println(superBoardBorder);
            }System.out.println(" ");
        }
    }


    public boolean validSuperCell(int boardNumber){
        int upper = size * size;
        if (boardNumber < 1 || boardNumber > upper){
            return false;
        }
        int count = 1, i = 0, j = 0;
        boolean flag = true;
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                if (count == boardNumber) {
                    flag = false;
                    break;
                }
                count++;
            }
            if (!flag)
                break;
        }

        return !superBoard[i][j].isFull();
    }

    public int[] superCellIndex(){
        //Takes user input to choose the super cell and doesn't let them make a move till they choose a valid super cell.
        sc = new Scanner(System.in);
        int input;
        System.out.println("Please enter a valid and vacant superCell number to place your mark");
        while (true) {
            if (sc.hasNextInt()) {
                input = sc.nextInt();
                if (!validSuperCell(input)) {
                    System.out.println(
                            "Please enter a valid and vacant superCell number to place your mark that has not been won yet");
                } else
                    break;
            } else {
                sc.next();
                System.out.println("Please enter only integer input");

            }
        }
        return getSuperCellIndices(input);
    }

    public int[] getSuperCellIndices(int boardNumber){
        //returns the index of the superCell when we enter the number of the board as it appears in the game
        int[] superIndex = new int[2];
        int count = 1, i = 0, j = 0;
        int flag = 0;
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                if (count == boardNumber) {
                    flag = 1;
                    break;
                }
                count++;
            }
            if(flag==1){
                break;
            }
        }
        superIndex[0] = i;
        superIndex[1] = j;
        return superIndex;
    }

    public Board getSubBoard(int i, int j){
        return superBoard[i][j];
    }

    @Override
    public int checkWinner(int row, int col, int playerID) {
        if (++superRows[playerID][row] == size) {
            return playerID;
        }
        if (++superCols[playerID][col] == size) {
            return playerID;
        }
        if (row == col && ++superDiagonals[playerID][0] == size) {
            return playerID;
        }
        if ((row + col == size - 1) && ++superDiagonals[playerID][1] == size) {
            return playerID;
        }

        return -1;
    }

//    public static void main(String[] args){
//        new SuperBoard(3).printSuperBoard();
//    }



}
