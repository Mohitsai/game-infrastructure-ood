public class QuoridorBoard {

    private int size = 9;

    Mark emptyMark;

    Cell[][] quoridorBoard;

    public QuoridorBoard() {
        setQuoridorBoard();
    }

    public void setQuoridorBoard() {
        //set up an empty board (all cells with empty marks) at the start of a game.

        this.quoridorBoard = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                quoridorBoard[i][j] = new Cell(new Mark(' '));
                for(int k=0; k<4; k++){
                    quoridorBoard[i][j].getWallList()[k] = false;
                }
            }
        }
    }

    public Cell getCellAt(int row, int col){
        return quoridorBoard[row][col];
    }

    public void placePlayer(Player player){
        int row = player.getRow();
        int col = player.getCol();
        Mark playerMark;
        if(player.playerId==1){
            playerMark = new Mark('1');
        }else{
            playerMark = new Mark('2');
        }

        quoridorBoard[row][col].setMark(playerMark);
    }

    public void removePreviousMark(int row, int col){
        Mark emptyMark = new Mark(' ');
        quoridorBoard[row][col].setMark(emptyMark);
    }

    public void printBoard() {
        // Print a board that looks like the one in the assignment requirements file
        // Prints board as per the size desired by the player

        for (int j = 0; j < size; j++) {
            System.out.print("+----");
        }
        System.out.println("+");

        for (int i = 0; i < size; i++) {

            printRow(i);
            System.out.printf("\n");
        }

        for (int j = 0; j < size; j++) {
            System.out.print("+----");
        }
        System.out.println("+");
    }

    public void printRow(int row){
        //prints a row of the board
        System.out.print("| ");
        for (int j = 0; j < size; j++) {
            Cell cell = quoridorBoard[row][j];
            System.out.print(" " + cell.getMark() + " |");
            if(cell.getWallList()[0]==true){
                System.out.print("|");
            }else{
                System.out.print(" ");
            }
        }
        System.out.printf("\n");
        for (int j = 0; j < size; j++) {
            Cell cell = quoridorBoard[row][j];
            if(cell.getWallList()[3]==true){
                System.out.print(" ----");
            }else{
                System.out.print("     ");
            }
        }
    }
}
