import java.util.Scanner;

public class TicTacToe extends BoardGame {
    Scanner sc;
    String playerOneName;
    String playerTwoName;
    int size;
    int playerOneWins;
    int playerTwoWins;
    int rowPosition;
    int colPosition;
    int[][] rows;
    int[][] cols;
    int[][] diagonals;
    int totalTurns;
    Player playerOne;
    Player playerTwo;
    Board board;

    boolean first;

    public TicTacToe(boolean makeDo) {
        if(makeDo) {
            this.sc = new Scanner(System.in);
            int confirmation = new Menu().confirm();
            if (confirmation == 1) {
                System.out.println("Let's Play Tic-Tac-Toe!!");
            } else if (confirmation == 2) {
                System.out.println("Sorry to see you leave");
                System.exit(0);
            }

            String[] playerNames = new Menu().getPlayerNames();
            playerOneName = playerNames[0];
            playerTwoName = playerNames[1];

            size = new Menu().getBoardSize();
            first = true;
            playerOneWins = 0;
            playerTwoWins = 0;
            totalTurns = 0;
            playerOne = new Player(1, playerOneName, 1);
            playerTwo = new Player(1, playerTwoName, 2);
            this.board = new Board(size);
            this.rows = new int[3][size];
            this.cols = new int[3][size];
            this.diagonals = new int[3][2];
        }

    }

    @Override
    public void play() {
        int playerId = 0, winner = 0;
        board.printBoard();
        while (true) {
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
            playerId = this.turn(playerId);
            winner = checkWinner(rowPosition, colPosition, playerId);
            if (winner != -1)
                board.printBoard();

            if (winner == 1) {
                System.out.println("Congrats, " + playerTwo.playerName + "! You Won!!!");
                playerTwoWins++;
            } else if (winner == 2) {
                System.out.println("Congrats, " + playerOne.playerName + "! You Won!!!");
                playerOneWins++;
            } else if ((totalTurns == size * size) && (winner == -1)) {
                System.out.println("Uh!...Oh! It's a Cat's Game");

            } else {
                this.board.printBoard();
                continue;
            }
            this.afterGame();

        }
    }
    private int turn(int playerId) {
        if (playerId == 1) {
            Mark mark1 = new Mark('X');
            validatePlayerMove(this.board, mark1, this.playerOne);
            rowPosition = this.playerOne.getRow();
            colPosition = this.playerOne.getCol();
            playerId = 2;
        } else if (playerId == 2) {
            Mark mark2 = new Mark('O');
            validatePlayerMove(this.board, mark2, this.playerTwo);
            rowPosition = this.playerTwo.getRow();
            colPosition = this.playerTwo.getCol();
            playerId = 1;

        }
        return playerId;
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
            this.board = new Board(size);
            this.play();

        }else if (choice == 2) {
            System.out.println("Sorry to see you leave");
            sc.close();
            System.exit(0);

        }

    }

}
