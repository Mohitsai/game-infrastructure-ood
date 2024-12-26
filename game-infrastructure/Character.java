public class Character {
    public String name;
    public int level;
    public int HP;
    public String type;
    public boolean isFainted = false;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    protected int row;

    protected int col;

    public Mark getBoardMark() {
        return boardMark;
    }

    public void setBoardMark(Mark boardMark) {
        this.boardMark = boardMark;
    }

    protected Mark boardMark;

    public Character(){
        name = "Null";
        level = 1;
        HP = 1000;
        type = "Null";
    }
    public Character(String n, int l, String t){
        this.name = n;
        this.level = l;
        setHPWithLevel(level);
        this.type = t;
    }

    public Character(String n, int l, String t, Mark mark, int row, int col){
        this.name = n;
        this.level = l;
        setHPWithLevel(level);
        this.type = t;
        this.boardMark = mark;
        this.row = row;
        this.col = col;
    }

    public boolean isFainted(){
        return this.HP <= 0;
    }

    //GETTERS AND SETTERS

    public String getName(){
        return this.name;
    }
    public int getLevel(){
        return this.level;
    }

    public int getHP(){
        return this.HP;
    }
    public boolean getIsFainted(){
        return this.isFainted;
    }
    public String getType(){
        return this.type;
    }


    public void setLevel(int lev){
        this.level = lev;
    }
    public void setHP(int hp){
        this.HP = hp;
    }

    public void setHPWithLevel(int level){this.HP = level*100;}
    public void setMPWithLevel(int level){this.HP = level*100;}
    public void setIsFainted(boolean res){
        this.isFainted = res;
    }

}
