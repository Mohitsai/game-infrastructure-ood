import java.util.Scanner;

public class Quoridor extends BoardGame {

    Scanner sc;
    String playerOneName;
    String playerTwoName;
    int size;
    int playerOneWins;
    int playerTwoWins;
    int totalTurns;
    Player playerOne;
    Player playerTwo;

    QuoridorBoard board;

    boolean first;

    public Quoridor() {
        this.sc = new Scanner(System.in);
        int confirmation = new Menu().confirm();
        if (confirmation == 1) {
            System.out.println("Let's Play Quoridor!!");
        } else if (confirmation == 2) {
            System.out.println("Sorry to see you leave");
            System.exit(0);
        }

        String[] playerNames = new Menu().getPlayerNames();
        playerOneName = playerNames[0];
        playerTwoName = playerNames[1];

        size = 9;
        first = true;
        playerOneWins = 0;
        playerTwoWins = 0;
        totalTurns = 0;
        playerOne = new Player(4, playerOneName, 1);
        playerTwo = new Player(4, playerTwoName, 2);
        this.board = new QuoridorBoard();
        board.placePlayer(playerOne);
        board.placePlayer(playerTwo);
    }

    @Override
    public void play() {
        System.out.println("This is quoridor game play");
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
            if(playerOne.getRow()==0){
                System.out.println("Congrats, " + playerOne.playerName + "! You Won!!!");
                playerOneWins++;
            } else if (playerTwo.getRow()==8) {
                System.out.println("Congrats, " + playerTwo.playerName + "! You Won!!!");
                playerTwoWins++;
            }else{
                board.printBoard();
                continue;
            }
            this.afterGame();
        }
    }

    private int turn(int playerId) {
        int choice =0;
        if (playerId == 1) {
            choice = new Menu().selectMoveType(playerOne);
            if(choice==1){
                System.out.println("Enter your move! Enter r/right to move right, l/left to move left, u/up to move up or d/down to move down");
                validateMove(this.board, this.playerOne, this.playerTwo);
            } else if (choice==2) {
                validateWallMove(this.board, this.playerOne);
            }
            playerId = 2;
        } else if (playerId == 2) {
            choice = new Menu().selectMoveType(playerTwo);
            if(choice==1){
                System.out.println("Enter your move! Enter r/right to move right, l/left to move left, u/up to move or d/down to move down");
                validateMove(this.board, this.playerTwo, this.playerOne);
            } else if (choice==2) {
                validateWallMove(this.board, this.playerTwo);
            }
            playerId = 1;

        }
        return playerId;
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
            first = true;
            totalTurns = 0;
            this.board = new QuoridorBoard();
            this.play();

        }else if (choice == 2) {
            System.out.println("Sorry to see you leave");
            sc.close();
            System.exit(0);

        }

    }
}
