import java.util.ArrayList;
import java.util.Scanner;

public class BoardGame implements Game{
    //Implements Interface Game
    //Used to develop board games with the given template

    public BoardGame() {}

    public Board board;

    Scanner sc;

    public void play(){};

    public void afterGame(){};

    public void checkWinner(){};

    public void validatePlayerMove(Board board, Mark mark, Player player) {
        //Validates the input of the player and doesn't let them make the move till they give a valid cell number
        sc = new Scanner(System.in);
        int input;
        System.out.println("Please enter a valid and vacant cell number to place your mark");
        while (true) {
            if (sc.hasNextInt()) {
                input = sc.nextInt();
                if (!validateCellNumber(board, input)) {
                    System.out.println(
                            "Please enter a valid and vacant cell number to place your mark");
                } else
                    break;
            } else {
                sc.next();
                System.out.println("Please enter only integer input");

            }

        }
        player.move(board, input, mark);
    }

    public boolean validateCellNumber(Board board, int cellNumber) {
        //checks the validity of the cell number entered by the player for a move
        //checks against boundary condition and later vacancy of the cell if it is a valid cell number
        int size = board.getSize();
        int upper = size * size;
        if (cellNumber < 1 || cellNumber > upper){
            return false;
        }

        int count = 1, i = 0, j = 0;
        boolean flag = true;
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                if (count == cellNumber) {
                    flag = false;
                    break;
                }
                count++;
            }
            if (!flag)
                break;
        }
        if (board.getBoard()[i][j].getMark() != ' ')
            return false;

        return true;

    }

    public ArrayList<java.lang.Character> checkAvailablePlayerMoves(Player player){
        int row = player.getRow();
        int col = player.getCol();
        ArrayList<java.lang.Character> availableMoves = new ArrayList<>();
        availableMoves.add(('r'));availableMoves.add('l');availableMoves.add('u');availableMoves.add('d');
        if(row==0){
            availableMoves.remove(2);
        }
        if(row==8){
            availableMoves.remove(3);
        }
        if(col == 0){
            availableMoves.remove(1);
        }
        if(col == 8){
            availableMoves.remove(0);
        }
        return availableMoves;
    }

    public void validateMove(QuoridorBoard qBoard, Player player, Player opponent){
        sc = new Scanner(System.in);
        char input;
        int opponentRow = opponent.getRow();
        int opponentCol = opponent.getCol();
        int row = player.getRow();
        int col = player.getCol();
        int futureRow=row;
        int futureCol=col;
        System.out.println("Please enter a valid move");
        while (true) {
            input = sc.nextLine().charAt(0);
            if (!checkAvailablePlayerMoves(player).contains(input)) {
                System.out.println("Please enter a valid move");
                } else{
                if(isWallPresent(qBoard, player, input)){
                    System.out.println("Please enter a valid move! A wall is present and you cannot move in that direction");
                }else{
                    if(input=='r'){
                        futureCol = col+1;
                    } else if (input=='l') {
                        futureCol = col-1;
                    } else if (input=='u') {
                        futureRow = row-1;
                    }else {
                        futureRow = row+1;
                    }
                    if((futureRow==opponentRow&&futureCol==opponentCol)||futureRow>8||futureRow<0||futureCol>8||futureCol<0){
                        System.out.println("Please enter a valid move");
                    }else{
                        break;
                    }
                }

            }
        }
        if(input=='r'){
            player.setCol(col+1);
        } else if (input=='l') {
            player.setCol(col-1);
        } else if (input=='u') {
            player.setRow(row-1);
        }else {
            player.setRow(row+1);
        }
        qBoard.placePlayer(player);
        qBoard.removePreviousMark(row,col);
    }

    public void validateWallMove(QuoridorBoard qBoard, Player player){
        int walls = player.getRemainingWalls();
        int direction =  new Menu().selectWallDirection();
        sc = new Scanner(System.in);
        System.out.println("Enter row and column for wall placement");
        int row = sc.nextInt();
        int col = sc.nextInt();
        while (row < 0 || row > 7 || col < 0 || col > 7 || wallAlreadyPresent(qBoard, row, col, direction)) {
            System.out.println("Invalid move! Enter a valid row and column.");
            row = sc.nextInt();
            col = sc.nextInt();
        }
        if(direction==1){
            qBoard.getCellAt(row, col).getWallList()[3]=true;
            qBoard.getCellAt(row, col+1).getWallList()[3]=true;
            qBoard.getCellAt(row+1, col).getWallList()[2]=true;
            qBoard.getCellAt(row+1, col+1).getWallList()[2]=true;
        }else{
            qBoard.getCellAt(row, col).getWallList()[0]=true;
            qBoard.getCellAt(row+1, col).getWallList()[0]=true;
            qBoard.getCellAt(row, col+1).getWallList()[1]=true;
            qBoard.getCellAt(row+1, col+1).getWallList()[1]=true;
        }
        player.setRemainingWalls(walls-1);
    }
    public boolean wallAlreadyPresent(QuoridorBoard qBoard, int row, int col, int direction){
        if(direction==1){
            if(qBoard.getCellAt(row, col).getWallList()[3]||qBoard.getCellAt(row, col+1).getWallList()[3]){
                return true;
            }else{
                return false;
            }
        }else{
            if(qBoard.getCellAt(row, col).getWallList()[0]||qBoard.getCellAt(row+1, col).getWallList()[0]){
                return true;
            }else{
                return false;
            }
        }
    }

    public boolean isWallPresent(QuoridorBoard qBoard, Player player, char move){
        int row = player.getRow();
        int col = player.getCol();
        if(move=='r'){
            if(!qBoard.getCellAt(row, col).getWallList()[0]){
                return false;
            }else{
                return true;
            }
        } else if (move=='l') {
            if(!qBoard.getCellAt(row, col).getWallList()[1]){
                return false;
            }else{
                return true;
            }

        } else if (move=='u') {
            if(!qBoard.getCellAt(row, col).getWallList()[2]){
                return false;
            }else{
                return true;
            }

        } else{
            if(!qBoard.getCellAt(row, col).getWallList()[3]){
                return false;
            }else{
                return true;
            }

        }
    }




}
