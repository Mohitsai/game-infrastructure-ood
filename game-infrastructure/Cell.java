public class Cell {
    //This class defines the fundamental unit of a board game i.e. it's each cell or the square.
    //each cell can have a mark (of any type based on the state)
    protected Mark mark;

    protected Mark rightMark;

    protected String border;

    protected String cellContents;

    protected boolean heroPresent;

    public boolean isHeroPresent() {
        return heroPresent;
    }

    public void setHeroPresent(boolean heroPresent) {
        this.heroPresent = heroPresent;
    }

    public boolean isMonsterPresent() {
        return monsterPresent;
    }

    public void setMonsterPresent(boolean monsterPresent) {
        this.monsterPresent = monsterPresent;
    }

    protected boolean monsterPresent;

    public Mark getRightMark() {
        return rightMark;
    }

    public void setRightMark(Mark rightMark) {
        this.rightMark = rightMark;
    }

    public Mark getLeftMark() {
        return leftMark;
    }

    public void setLeftMark(Mark leftMark) {
        this.leftMark = leftMark;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    protected Mark leftMark;

    protected char type;

    public boolean[] getWallList() {
        return wallList;
    }

    public void setWallList(boolean[] wallList) {
        this.wallList = wallList;
    }

    private boolean[] wallList = new boolean[4];

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    private String colour;


    public Cell(Mark mark) {
        this.setMark(mark);
    }

    public Cell(Mark left, Mark right, char type, String colour){
        this.leftMark = left;
        this.rightMark = right;
        this.type = type;
        this.colour = colour;
        setBorder();
        setCellContents();
    }

    public void setBorder(){
         this.border = this.getColour() + this.type + " - " + this.type + " - " + this.type + "  " + Colour.RESET;
    }

    public String getBorder(){
        return border;
    }

    public String getCellContents(){
        return cellContents;
    }

    public void setCellContents(){
        this.cellContents = this.getColour() + "| " + Colour.RESET + this.getLeftMark().getValorMarkType() + " " + this.getRightMark().getValorMarkType() + this.getColour() + " |  "+ Colour.RESET;
    }


    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public char getMark() {
        return mark.getMarkType();
    }
}
