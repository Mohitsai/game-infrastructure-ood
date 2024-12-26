import java.util.*;

public final class Menu {
    //This class is responsible for giving choices and taking corresponding input from the player.
    //The menu can appear in different forms based on the state.

    private Scanner sc;

    private int selection;

    public Menu(){}

    public void gameSelect() {
        //This menu method gives the choice of which game player would like to play.
        Map<Integer, String> gameDisplay = new HashMap<Integer, String>();
        gameDisplay.put(1,"Tic-Tac-Toe");
        gameDisplay.put(2,"Order and Chaos");
        gameDisplay.put(3, "Super Tic-Tac-Toe");
        gameDisplay.put(4, "Quoridor");
        gameDisplay.put(5, "Legends : Heroes and Monsters");
        gameDisplay.put(6, "Legends of Valor");
        System.out.println();
        System.out.println("Enter the number corresponding to the game you want to play");

        for(Map.Entry m:gameDisplay.entrySet()){
            System.out.println(m.getKey()+": "+m.getValue());
        }
        System.out.println();
        sc = new Scanner(System.in);
        selection = sc.nextInt();

        while (!gameDisplay.containsKey(selection)) {
            System.out.println("Please choose a valid number");
            selection = sc.nextInt();
        }

        switch(selection){
            case 1:
                new TicTacToe(true).play();
                break;
            case 2:
                new OrderAndChaos().play();
                break;
            case 3:
                int mode = new Menu().selectMode();
                switch(mode){
                    case 1:
                        new SuperTicTacToe().play(1);
                        break;
                    case 2:
                        new SuperTicTacToe().play(2);
                        break;
                }break;
            case 4:
                new Quoridor().play();
                break;
            case 5:
                new Legends().play();
                break;
            case 6:
                new LegendsOfValor().play();
        }
    }

    public String[] getPlayerNames() {
        String[] playerNames = new String[2];
        System.out.println("Enter player 1 name:");
        sc = new Scanner(System.in);
        playerNames[0] = sc.nextLine();
        System.out.println("Enter player 2 name:");
        playerNames[1] = sc.nextLine();

        for(int i=0; i<2; i++){
            if(playerNames[i].isEmpty()){
                playerNames[i] = "Player"+ (i + 1);
            }
        }

        return playerNames;
    }

    public int confirm() {

        Map<Integer, String> confirmDisplay = new HashMap<Integer, String>();
        confirmDisplay.put(1,"Yes, I want to play");
        confirmDisplay.put(2, "No, I want to exit");

        System.out.println("Please confirm. Enter number corresponding to the option you want to choose");
        System.out.println();

        for(Map.Entry m:confirmDisplay.entrySet()){
            System.out.println(m.getKey()+": "+m.getValue());
        }
        System.out.println();
        sc = new Scanner(System.in);
        selection = sc.nextInt();

        while (!confirmDisplay.containsKey(selection)) {
            System.out.println("Please choose a valid number");
            selection = sc.nextInt();
        }

        return selection;
    }

    public int getBoardSize() {
        int size = 0;
        while (true) {
            System.out.println("Choose your board size. Enter 3 for a 3x3 board, 4 for 4x4, etc.");
            sc = new Scanner(System.in);
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid board size");
            }else {
                size = sc.nextInt();
                if (size < 3 || size > 10) {
                    System.out.println("Please enter a valid board size");
                } else {
                    break;
                }
            }
        }
        return size;
    }

    public int refreshBoard(){
        Map<Integer, String> confirmDisplay = new HashMap<Integer, String>();
        confirmDisplay.put(1,"Yes. Refresh and create a new world!");
        confirmDisplay.put(2, "No, I would like to continue with this board.");

        System.out.print("Do you want to refresh and create a more reasonable board?\nEnter a number corresponding to the option you want to choose\n");
        System.out.println();

        for(Map.Entry m:confirmDisplay.entrySet()){
            System.out.println(m.getKey()+": "+m.getValue());
        }
        System.out.println();
        sc = new Scanner(System.in);
        selection = sc.nextInt();

        while (!confirmDisplay.containsKey(selection)) {
            System.out.println("Please choose a valid number");
            selection = sc.nextInt();
        }

        return selection;
    }

    public int atMarket(){
        Map<Integer, String> confirmDisplay = new HashMap<Integer, String>();
        confirmDisplay.put(1,"Buy an item from the market.");
        confirmDisplay.put(2, "Sell an item to the market (Important to note that you will sell your item at half it's cost price)");
        confirmDisplay.put(3, "Exit Market.");

        System.out.println("What would you like to do at the market?\nEnter a number corresponding to the option you want to choose\n");
        System.out.println();

        for(Map.Entry m:confirmDisplay.entrySet()){
            System.out.println(m.getKey()+": "+m.getValue());
        }
        System.out.println();
        sc = new Scanner(System.in);
        selection = sc.nextInt();

        while (!confirmDisplay.containsKey(selection)) {
            System.out.println("Please choose a valid number");
            selection = sc.nextInt();
        }

        return selection;
    }

    public int heroBattleSelection(){
        Map<Integer, String> confirmDisplay = new HashMap<Integer, String>();
        confirmDisplay.put(1,"Attack the monster!");
        confirmDisplay.put(2, "Cast a spell from your inventory");
        confirmDisplay.put(3, "Consume a potion from your inventory");
        confirmDisplay.put(4, "Equip yourself with an armor from your inventory to increase your defense");
        confirmDisplay.put(5, "Equip yourself with a weapon from your to increase damage during attack");

        System.out.println("It is your turn to attack! What would you like to do?\nEnter a number corresponding to the option you want to choose\n");

        for(Map.Entry m:confirmDisplay.entrySet()){
            System.out.println(m.getKey()+": "+m.getValue());
        }
        System.out.println();
        sc = new Scanner(System.in);
        selection = sc.nextInt();

        while (!confirmDisplay.containsKey(selection)) {
            System.out.println("Please choose a valid number");
            selection = sc.nextInt();
        }

        return selection;
    }

    public int valorHeroAction(){
        Map<Integer, String> confirmDisplay = new HashMap<Integer, String>();
        confirmDisplay.put(1, "Make a Move");
        confirmDisplay.put(2, "Attack!!!");
        confirmDisplay.put(3, "Cast a spell from your inventory");
        confirmDisplay.put(4, "Consume a potion from your inventory");
        confirmDisplay.put(5, "Equip yourself with an armor from your inventory to increase your defense");
        confirmDisplay.put(6, "Equip yourself with a weapon from your to increase damage during attack");
        confirmDisplay.put(7, "Teleport");
        confirmDisplay.put(8, "Recall");
//        confirmDisplay.put(9, "Information");
//        confirmDisplay.put(10, "Quit Game");

        System.out.println("What would you like to do?\nEnter a number corresponding to the option you want to choose\n");

        for(Map.Entry m:confirmDisplay.entrySet()){
            System.out.println(m.getKey()+": "+m.getValue());
        }
        System.out.println();
        sc = new Scanner(System.in);
        while (true){
            try{
                selection = sc.nextInt();
                break;
            }catch (Exception e){
                System.out.println("Please choose a valid number");
                sc.next();
            }
        }


        while (!confirmDisplay.containsKey(selection)) {
            System.out.println("Please choose a valid number");
            selection = sc.nextInt();
        }

        return selection;
    }

    public int getLegendsWorldSize() {
        int size = 0;
        while (true) {
            System.out.println("Choose the size of the world you would like to play the game on. The allowed world size is between 8x8 and 12x12. Enter 8 for a 8x8 square board, 9 for 9x9, etc.");
            sc = new Scanner(System.in);
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid integer");
            }else {
                size = sc.nextInt();
                if (size < 8 || size > 12) {
                    System.out.println("Please enter a valid board size");
                } else {
                    break;
                }
            }
        }
        return size;
    }

    public int getHeroPartySize() {
        int size = 0;
        while (true) {
            System.out.println("Choose the number of heroes in your party (1-3). Enter a number between 1-3");
            sc = new Scanner(System.in);
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid integer");
            }else {
                size = sc.nextInt();
                if (size < 1 || size > 3) {
                    System.out.println("Please enter a valid integer between 1 and 3");
                } else {
                    break;
                }
            }
        }
        return size;
    }

    public int playAgain(){
        Map<Integer, String> confirmDisplay = new HashMap<Integer, String>();
        confirmDisplay.put(1,"I want to Play Again.");
        confirmDisplay.put(2, "No, I want to exit.");

        System.out.println("Do you want to play again?");
        System.out.println();

        for(Map.Entry m:confirmDisplay.entrySet()){
            System.out.println(m.getKey()+": "+m.getValue());
        }
        System.out.println();
        sc = new Scanner(System.in);
        selection = sc.nextInt();

        while (!confirmDisplay.containsKey(selection)) {
            System.out.println("Please choose a valid number");
            selection = sc.nextInt();
        }

        return selection;
    }

    public int selectMode(){
        Map<Integer, String> modeDisplay = new HashMap<Integer, String>();
        modeDisplay.put(1,"Full Freedom Mode");
        modeDisplay.put(2, "Restricted Mode");

        System.out.println("Select the mode of the Super Tic-Tac-Toe game you would like to play");
        System.out.println();

        for(Map.Entry m:modeDisplay.entrySet()){
            System.out.println(m.getKey()+": "+m.getValue());
        }
        System.out.println();
        sc = new Scanner(System.in);
        selection = sc.nextInt();

        while (!modeDisplay.containsKey(selection)) {
            System.out.println("Please choose a valid number");
            selection = sc.nextInt();
        }

        return selection;
    }

    public int selectMoveType(Player player){
        Map<Integer, String> moveTypeDisplay = new HashMap<Integer, String>();
        moveTypeDisplay.put(1,"Make a move");
        moveTypeDisplay.put(2, "Place a wall");

        if(!(player.getRemainingWalls() == 0)){
            System.out.println("Select the type of move you want to make");
            System.out.println();
            for(Map.Entry m:moveTypeDisplay.entrySet()){
                System.out.println(m.getKey()+": "+m.getValue());
            }
            System.out.println();
            sc = new Scanner(System.in);
            selection = sc.nextInt();

            while (!moveTypeDisplay.containsKey(selection)) {
                System.out.println("Please choose a valid number");
                selection = sc.nextInt();
            }

        }else{
            selection = 1;
        }
        return selection;
    }

    public int selectWallDirection(){
        Map<Integer, String> modeDisplay = new HashMap<Integer, String>();
        modeDisplay.put(1,"Horizontal Wall");
        modeDisplay.put(2, "Vertical Wall");

        System.out.println("Select the direction of the wall you want to place");
        System.out.println();

        for(Map.Entry m:modeDisplay.entrySet()){
            System.out.println(m.getKey()+": "+m.getValue());
        }
        System.out.println();
        sc = new Scanner(System.in);
        selection = sc.nextInt();

        while (!modeDisplay.containsKey(selection)) {
            System.out.println("Please choose a valid number");
            selection = sc.nextInt();
        }

        return selection;
    }

}

