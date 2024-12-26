import java.util.*;

public class LegendsWorld extends Board{

    Cell[][] legendsBoard;

    Market market;

    Scanner sc;

    Random rand;

    Map<List<Integer>, java.lang.Character> worldMapping;

    Map<List<Integer>, Market> marketMapping = new HashMap<>();

    @Override
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    int size;

    public LegendsWorld(int size){
        setLegendsWorld(size);
        createWorldMap();
        printWorld();
    }

    public void setLegendsWorld(int size){
        setSize(size);
        this.rand = new Random();
        this.legendsBoard = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i==0 && j==0){
                    legendsBoard[0][0] = new Cell(new Mark('H'));
                }else{
                    legendsBoard[i][j] = new Cell(new Mark(rand.setWorldCellType()));
                }
            }
        }
    }

    public void printWorld() {
        // Print a board that looks like the one in the assignment requirements file
        // Prints board as per the size desired by the player

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("+-----");
            }
            System.out.println("+");

            printRow(i);
            System.out.printf("\n");
        }

        for (int j = 0; j < size; j++) {
            System.out.print("+-----");
        }
        System.out.println("+");
    }

    @Override
    public void printRow(int row){
        //prints a row of the board
        for (int j = 0; j < size; j++) {
            Cell cell = legendsBoard[row][j];
            System.out.print("|  " + cell.getMark() + "  ");
        }
        System.out.print("|");
    }

    public void placePlayer(Player player){
        int row = player.getRow();
        int col = player.getCol();
        Mark playerMark;
        playerMark = new Mark('H');

        legendsBoard[row][col].setMark(playerMark);
    }

    public ArrayList<java.lang.Character> checkAvailablePlayerMoves(Player player){
        int row = player.getRow();
        int col = player.getCol();
        List<Integer> positionList = Arrays.asList(row, col);
        ArrayList<java.lang.Character> availableMoves = new ArrayList<>();
        availableMoves.add(('q'));availableMoves.add(('i'));availableMoves.add(('w'));availableMoves.add('a');availableMoves.add('s');availableMoves.add('d');availableMoves.add(('m'));
        if(!(worldMapping.get(positionList)=='M')){
            availableMoves.remove(6);
        }
        if(col == size-1 || getMarkAt(row, col+1)=='X'){
            availableMoves.remove(5);
        }
        if(row == size-1 || getMarkAt(row+1, col)=='X'){
            availableMoves.remove(4);
        }
        if(col == 0 || getMarkAt(row, col-1)=='X'){
            availableMoves.remove(3);
        }
        if(row==0 || getMarkAt(row-1, col)=='X'){
            availableMoves.remove(2);
        }
        return availableMoves;
    }

    public void validateHeroPartyMove(Player player, HeroParty heroParty){
        sc = new Scanner(System.in);
        char input;
        int row = player.getRow();
        int col = player.getCol();
        List<java.lang.Character> movesList = Arrays.asList('w', 'a', 's', 'd');
        List<Integer> previousPositionList = Arrays.asList(row, col);
        System.out.println("Enter your move!");
        if(worldMapping.get(previousPositionList) == 'M'){
            System.out.println("You are on a market space!! Press m to enter the market");
            System.out.print("w - move up\t a - move left\ts - move down\td - move right\tm - enter market\ti - info\tq - quit game\n");
        }else{
            System.out.print("w - move up\t a - move left\ts - move down\td - move right\ti - info\tq - quit game\n");
        }
        while (true) {
            while(true){
                String userInput = sc.nextLine();
                if(userInput.isEmpty() || userInput.length() != 1){
                    System.out.println("Please enter a valid move");
                }else{
                    input = userInput.charAt(0);
                    break;
                }
            }
            if (!checkAvailablePlayerMoves(player).contains(input)) {
                System.out.println("Please enter a valid move");
            }else{
                break;
            }
        }
        if(movesList.contains(input)){
            if(input=='w'){
                player.setRow(row-1);
            } else if (input=='a') {
                player.setCol(col-1);
            } else if (input=='s') {
                player.setRow(row+1);
            }else{
                player.setCol(col+1);
            }
            placePlayer(player);
            List<Integer> newPositionList = Arrays.asList(player.getRow(), player.getCol());
            Mark previousCellMark = new Mark(worldMapping.get(previousPositionList));
            legendsBoard[row][col].setMark(previousCellMark);
            printWorld();
            if(worldMapping.get(newPositionList) == ' '){
                if(rand.haveBattle(player)) {
                    Battle battle = new Battle(heroParty);
                }
            }
        } else if (input == 'q') {
            System.exit(0);
        }else if(input == 'i'){
            heroParty.printHeroParty();
        }else{
            if(!player.isHasVisitedMarket()){
                player.setHasVisitedMarket(true);
            }
            if(marketMapping.isEmpty() || !marketMapping.containsKey(previousPositionList)){
                market = new Market();
                marketMapping.put(previousPositionList, market);
            }else{
                market = marketMapping.get(previousPositionList);
                market.printMarket();
            }
            heroParty.enterMarket(market);
        }
    }

    public char getMarkAt(int row, int col){
        return this.legendsBoard[row][col].getMark();
    }

    public void createWorldMap(){
        worldMapping = new HashMap<>();
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                char mark = getMarkAt(i,j);
                List<Integer> positionList = Arrays.asList(i,j);
                if(mark=='H'){
                    worldMapping.put(positionList, ' ');
                }else{
                    worldMapping.put(positionList, mark);
                }
            }
        }
    }
}
