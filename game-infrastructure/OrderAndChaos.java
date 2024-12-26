import java.util.Scanner;

public class OrderAndChaos extends TicTacToe{

    private Player order;
    private Player chaos;
    private int playerPicker = 0; //variable that helps assign order and chaos to players at random at the start of the game
    int[][] rows;
    int[][] cols;
    int[][] diagonals;
    int markType = 0;

    public OrderAndChaos() {
        super(false);
        int confirmation = new Menu().confirm();
        if(confirmation == 1){
            System.out.println("Let's Play Order & Chaos!!");
        }else if (confirmation==2) {
            System.out.println("Sorry to see you leave");
            System.exit(0);
        }

        board = new Board(6);
        sc = new Scanner(System.in);
        playerOneName = "";
        playerTwoName = "";
        String[] playerNames = new Menu().getPlayerNames();
        playerOneName = playerNames[0];
        playerTwoName = playerNames[1];
        playerPicker = (Math.random() <= 0.5) ? 1 : 2;
        if (playerPicker == 1) {
            System.out.println("" + playerOneName + " is Order");
            System.out.println("" + playerTwoName + " is Chaos");
            order = new Player(2, playerOneName, 1);
            chaos = new Player(2, playerTwoName, 2);
        } else if (playerPicker == 2) {
            System.out.println("" + playerTwoName + " is Order");
            System.out.println("" + playerOneName + " is Chaos");
            order = new Player(2, playerTwoName, 1);
            chaos = new Player(2, playerOneName, 2);

        }
        rows = new int[6][2];
        cols = new int[6][2];
        diagonals = new int[6][2];
        first =true;

    }

    public void play() {
        int turn = 0;
        int winner = -1;
        board.printBoard();
        while (true) {
            if (first) {
                turn = this.turn(1);
                first = false;

            } else
                turn = this.turn(turn);
            totalTurns++;

            winner = checkWinner(rowPosition, colPosition, markType);
            if (winner == 1) {
                System.out.println("Congrats, " + order.playerName + "! Order won!! ");
                System.out.println("");

                if (order.playerName.equalsIgnoreCase(playerOneName)) {
                    playerOneWins++;
                } else
                    playerTwoWins++;

            } else if (totalTurns == 36 && winner == -1) {
                System.out.println("Congrats,  " + chaos.playerName + "! Chaos won!!");
                System.out.println("");

                if (chaos.playerName.equalsIgnoreCase(playerOneName)) {
                    playerOneWins++;
                } else
                    playerTwoWins++;
            } else {
                board.printBoard();
                continue;
            }
            board.printBoard();
            this.afterGame();

        }
    }

    private int turn(int turn) {
        String turnName, mark;
        char markerType = ' ';
        if (turn == 1) {
            turnName = order.playerName;
            System.out.println("" + turnName + " 's Move. Enter the Mark Type 'X' or 'O'");
            mark = sc.nextLine();
            while (!mark.equalsIgnoreCase("X") && !mark.equalsIgnoreCase("O")) {
                System.out.println("invalid input Please enter again");
                mark = sc.nextLine();
            }
            markerType = mark.toUpperCase().toCharArray()[0];
            validatePlayerMove(board, new Mark(markerType), order);
            rowPosition = order.getRow();
            colPosition = order.getCol();
            turn = 2;

        } else if (turn == 2) {
            turnName = chaos.playerName;
            System.out.println("" + turnName + " 's Move. Enter the Mark Type 'X' or 'O'");
            mark = sc.nextLine();
            while (!mark.equalsIgnoreCase("X") && !mark.equalsIgnoreCase("O")) {
                System.out.println("invalid input Please enter again");
                mark = sc.nextLine();
            }
            markerType = mark.toUpperCase().toCharArray()[0];
            validatePlayerMove(board, new Mark(markerType), chaos);
            rowPosition = chaos.getRow();
            colPosition = chaos.getCol();
            turn = 1;

        }
        if (markerType == 'X')
            markType = 0;
        else
            markType = 1;

        return turn;

    }

    public int checkWinner(int row, int col, int markType) {
        if (++rows[row][markType] == 6) {
            return 1;
        }
        if (++cols[col][markType] == 6) {
            return 1;
        }
        if (row == col && ++diagonals[0][markType] == 6) {
            return 1;
        }
        if ((row + col == 5) && ++diagonals[1][markType] == 6) {
            return 1;
        }

        return -1;
    }

    @Override
    public void afterGame() {
        int choice;
        System.out.println();
        System.out.println("Here is your Score Card");
        System.out.println("" + playerOneName + "'s score: " + playerOneWins);
        System.out.println("" + playerTwoName + "'s score: " + playerTwoWins);
        System.out.println();
        choice = new Menu().playAgain();
        if (choice == 1) {
            System.out.println("Starting game");
            first = true;
            totalTurns = 0;
            playerPicker = (Math.random() <= 0.5) ? 1 : 2; // random 1 or 2 id
            if (playerPicker == 1) {
                System.out.println("Player 1 i.e. " + playerOneName + " is order");
                System.out.println("Player 2 i.e. " + playerTwoName + " is Chaos");
                order = new Player(2, playerOneName, 1);
                chaos = new Player(2, playerTwoName, 2);
            } else if (playerPicker == 2) {
                System.out.println("Player 2 i.e. " + playerTwoName + " is order");
                System.out.println("Player 1 i.e. " + playerOneName + " is Chaos");
                order = new Player(2, playerTwoName, 1);
                chaos = new Player(2, playerOneName, 2);

            }
            this.board = new Board(6);
            this.rows = new int[6][2];
            this.cols = new int[6][2];
            this.diagonals = new int[6][2];
            this.play();
        } else if (choice == 2) {
            System.out.println("Sorry to see you leave");
            sc.close();
            System.exit(0);
        }

    }

}
