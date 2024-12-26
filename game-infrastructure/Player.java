import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    String playerName;

    private int remainingWalls;

    public int playerId;

    private int move;

    private int row;
    private int col;

    public boolean isHasVisitedMarket() {
        return hasVisitedMarket;
    }

    public void setHasVisitedMarket(boolean hasVisitedMarket) {
        this.hasVisitedMarket = hasVisitedMarket;
    }

    private boolean hasVisitedMarket = false;

    Player(int gameType, String playerName, int playerId) {
        if(gameType == 4){
            this.playerName = playerName;
            this.playerId = playerId;
            if(playerId == 1){
                this.row = 8;
                this.col = 4;
            }else{
                this.row = 0;
                this.col = 4;
            }
            this.remainingWalls = 10;

        }else if(gameType == 6){

        }else if(gameType == 5){
            this.row = 0;
            this.col = 0;
        }else{
            this.playerName = playerName;
            this.playerId = playerId;
            this.row = -1;
            this.col = -1;
        }

    }

    public void move(Board board, int input, Mark mark) {
        int iterator = 1, i = 0, j = 0;
        int flag = 0;

        for (i = 0; i < board.getBoard().length; i++) {
            for (j = 0; j < board.getBoard()[i].length; j++) {
                if (iterator == input) {
                    board.getBoard()[i][j].setMark(mark);
                    flag = 1;
                    break;
                }
                iterator++;
            }
            if (flag == 1)
                break;
        }
        this.row = i;
        this.col = j;
        this.move = input;

    }



    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public int getMove() {
        return this.move;
    }

    public int getRemainingWalls() {
        return remainingWalls;
    }

    public void setRemainingWalls(int remainingWalls) {
        this.remainingWalls = remainingWalls;
    }
}
