import java.util.Scanner;

public class SuperTicTacToe extends TicTacToe {

    SuperBoard superBoard;

    int playerOneSubWins;

    int playerTwoSubWins;

    int superRowPosition;

    int superColPosition;

    int move;

    public SuperTicTacToe() {
        super(false);
        this.sc = new Scanner(System.in);
        int confirmation = new Menu().confirm();
        if(confirmation == 1){
            System.out.println("Let's Play Super-Tic-Tac-Toe!!");
        }else if (confirmation==2) {
            System.out.println("Sorry to see you leave");
            System.exit(0);
        }

        String[] playerNames = new Menu().getPlayerNames();
        playerOneName = playerNames[0];
        playerTwoName = playerNames[1];

        size = new Menu().getBoardSize();
        first = false;
        playerOneWins = 0;
        playerTwoWins = 0;
        playerOneSubWins = 0;
        playerTwoSubWins = 0;
        totalTurns = 0;
        playerOne = new Player(3, playerOneName, 1);
        playerTwo = new Player(3, playerTwoName, 2);
        this.superBoard = new SuperBoard(size);
    }


    public void play(int mode){
        int playerId = 0, winner = 0;
        int superCell = 0;
        int[] turnResult;
        first = true;
        superBoard.printSuperBoard();
        while(true){
            if(mode == 1){
                if (first) {
                    playerId = (Math.random() <= 0.5) ? 1 : 2;
                    first = false;
                }
                if (playerId == 1) {
                    System.out.println(playerOne.playerName + ", it is your turn");

                } else if (playerId == 2) {
                    System.out.println(playerTwo.playerName + ", it is your turn.");
                }
                totalTurns++;
                turnResult = this.turn(playerId);
                playerId = turnResult[1];
                winner = turnResult[0];
            }else if(mode == 2){
                if (first) {
                    playerId = (Math.random() <= 0.5) ? 1 : 2;
                }
                if (playerId == 1) {
                    System.out.println(playerOne.playerName + ", it is your turn");

                } else if (playerId == 2) {
                    System.out.println(playerTwo.playerName + ", it is your turn.");
                }
                totalTurns++;
                if(first || superCell==0){
                    turnResult = this.turn(playerId);
                    first = false;
                }else{
                    turnResult = this.restrictedTurn(playerId, superCell);
                }
                playerId = turnResult[1];
                winner = turnResult[0];
                superCell = turnResult[2];
            }
            if (winner != -1)
                superBoard.printSuperBoard();
            if (winner == 2) {
                System.out.println("Congrats, " + playerTwo.playerName + "! You Won!!!");
                playerTwoWins++;
            } else if (winner == 1) {
                System.out.println("Congrats, " + playerOne.playerName + "! You Won!!!");
                playerOneWins++;
            } else if ((totalTurns == size * size * size * size) && (winner == -1)) {
                System.out.println("Uh!...Oh! It's a Cat's Game");

            } else {
                this.superBoard.printSuperBoard();
                continue;
            }
            afterGame();
        }
    }

    private int[] turn(int playerId) {
        int[] turnResult = new int[3];
        int winner = -1;
        if (playerId == 1) {
            Mark mark1 = new Mark('X');
            int[] superIndex = superBoard.superCellIndex();
            superRowPosition = superIndex[0];
            superColPosition = superIndex[1];
            int superCellNumber = (superRowPosition*size)+superColPosition+1;
            System.out.println("You have chosen Super-Cell Number " + superCellNumber);
            superBoard.getSubBoard(superRowPosition,superColPosition).printBoard();
            validatePlayerMove(superBoard.getSubBoard(superRowPosition,superColPosition), mark1, this.playerOne);
            rowPosition = this.playerOne.getRow();
            colPosition = this.playerOne.getCol();
            move = this.playerOne.getMove();
            if(superBoard.getSubBoard(superRowPosition,superColPosition).checkWinner(rowPosition, colPosition, playerId) == 1){
                superBoard.getSubBoard(superRowPosition,superColPosition).setWin();
                superBoard.getSubBoard(superRowPosition,superColPosition).setWinnerMark(mark1);
                winner = superBoard.checkWinner(superRowPosition, superColPosition, playerId);
            }
            int[] futureSuperIndex = superBoard.getSuperCellIndices(move);
            int futureSuperRowPosition = futureSuperIndex[0];
            int futureSuperColPosition = futureSuperIndex[1];
            if(superBoard.getSubBoard(futureSuperRowPosition,futureSuperColPosition).isFull()){
                move = 0;
            }
            playerId = 2;
        } else if (playerId == 2) {
            Mark mark2 = new Mark('O');
            int[] superIndex = superBoard.superCellIndex();
            superRowPosition = superIndex[0];
            superColPosition = superIndex[1];
            int superCellNumber = (superRowPosition*size)+superColPosition+1;
            System.out.println("You have chosen Super-Cell Number" + superCellNumber);
            superBoard.getSubBoard(superRowPosition,superColPosition).printBoard();
            validatePlayerMove(superBoard.getSubBoard(superRowPosition,superColPosition), mark2, this.playerTwo);
            rowPosition = this.playerTwo.getRow();
            colPosition = this.playerTwo.getCol();
            move = this.playerTwo.getMove();
            if(superBoard.getSubBoard(superRowPosition,superColPosition).checkWinner(rowPosition, colPosition, playerId) == 2){
                superBoard.getSubBoard(superRowPosition,superColPosition).setWin();
                superBoard.getSubBoard(superRowPosition,superColPosition).setWinnerMark(mark2);
                winner = superBoard.checkWinner(superRowPosition, superColPosition, playerId);
            }
            int[] futureSuperIndex = superBoard.getSuperCellIndices(move);
            int futureSuperRowPosition = futureSuperIndex[0];
            int futureSuperColPosition = futureSuperIndex[1];
            if(superBoard.getSubBoard(futureSuperRowPosition,futureSuperColPosition).isFull()){
                move = 0;
            }
            playerId = 1;

        }
        turnResult[0] = winner;
        turnResult[1] = playerId;
        turnResult[2] = move;

        return turnResult;
    }

    private int[] restrictedTurn(int playerId, int superCell) {
        int[] turnResult = new int[3];
        int winner = -1;
        if (playerId == 1) {
            Mark mark1 = new Mark('X');
            int[] superIndex = superBoard.getSuperCellIndices(superCell);
            superRowPosition = superIndex[0];
            superColPosition = superIndex[1];
            System.out.println("Your opponent has determined that you should make your next move in board " + superCell);
            superBoard.getSubBoard(superRowPosition,superColPosition).printBoard();
            validatePlayerMove(superBoard.getSubBoard(superRowPosition,superColPosition), mark1, this.playerOne);
            rowPosition = this.playerOne.getRow();
            colPosition = this.playerOne.getCol();
            move = this.playerOne.getMove();
            if(superBoard.getSubBoard(superRowPosition,superColPosition).checkWinner(rowPosition, colPosition, playerId) == 1){
                superBoard.getSubBoard(superRowPosition,superColPosition).setWin();
                superBoard.getSubBoard(superRowPosition,superColPosition).setWinnerMark(mark1);
                winner = superBoard.checkWinner(superRowPosition, superColPosition, playerId);
            }
                int[] futureSuperIndex = superBoard.getSuperCellIndices(move);
                int futureSuperRowPosition = futureSuperIndex[0];
                int futureSuperColPosition = futureSuperIndex[1];
            if(superBoard.getSubBoard(futureSuperRowPosition,futureSuperColPosition).isFull() ){
                    move = 0;
                }
            playerId = 2;
        } else if (playerId == 2) {
            Mark mark2 = new Mark('O');
            int[] superIndex = superBoard.getSuperCellIndices(superCell);
            superRowPosition = superIndex[0];
            superColPosition = superIndex[1];
            System.out.println("Your opponent has determined that you should place your mark in superCell " + superCell);
            superBoard.getSubBoard(superRowPosition,superColPosition).printBoard();
            validatePlayerMove(superBoard.getSubBoard(superRowPosition,superColPosition), mark2, this.playerTwo);
            rowPosition = this.playerTwo.getRow();
            colPosition = this.playerTwo.getCol();
            move = this.playerTwo.getMove();
            if(superBoard.getSubBoard(superRowPosition,superColPosition).checkWinner(rowPosition, colPosition, playerId) == 2){
                superBoard.getSubBoard(superRowPosition,superColPosition).setWin();
                superBoard.getSubBoard(superRowPosition,superColPosition).setWinnerMark(mark2);
                winner = superBoard.checkWinner(superRowPosition, superColPosition, playerId);
            }
                int[] futureSuperIndex = superBoard.getSuperCellIndices(move);
                int futureSuperRowPosition = futureSuperIndex[0];
                int futureSuperColPosition = futureSuperIndex[1];
                if(superBoard.getSubBoard(futureSuperRowPosition,futureSuperColPosition).isFull()){
                    move = 0;
                }
            playerId = 1;

        }
        turnResult[0] = winner;
        turnResult[1] = playerId;
        turnResult[2] = move;
        return turnResult;
    }

    @Override
    public void afterGame() {
        int choice;
        System.out.println();
        System.out.println("Here is your Score Card");
        System.out.println("" + playerOne.playerName + "'s score: " + playerOneWins );
        System.out.println("" + playerTwo.playerName + "'s score: " + playerTwoWins );
        System.out.println();
        choice = new Menu().playAgain();
        if (choice == 1) {
            System.out.println("Here we go again!!");
            size = new Menu().getBoardSize();
            first = true;
            totalTurns = 0;
            this.superBoard = new SuperBoard(size);
            this.play();

        }else if (choice == 2) {
            System.out.println("Sorry to see you leave");
            sc.close();
            System.exit(0);

        }

    }

}
