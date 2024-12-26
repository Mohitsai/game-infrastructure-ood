public class Mark {
    //This class defines the mark placed/used by players in a board game
    //The mark can take many forms. For example, it can be 'X' or 'O' in a game of tic tac toe.
    private char markType;

    private String valorMarkType;

    public String getValorMarkType() {
        return valorMarkType;
    }

    public void setValorMarkType(String valorMarkType) {
        this.valorMarkType = valorMarkType;
    }

    public Mark(char markType) {
        this.setMarkType(markType);
    }

    public Mark(String markType){
        this.setValorMarkType(markType);
    }

    public void setMarkType(char markType) {
        this.markType = markType;
    }

    public char getMarkType() {
        return markType;
    }
}
