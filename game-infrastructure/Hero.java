import java.util.*;

public class Hero extends Character{
    public int mana;
    public int experiencePoints;
    public int strength;
    public int dexterity;
    public int agility;
    public int money;

    private int homeRow;

    public ValorWorld getHeroPlayingWorld() {
        return heroPlayingWorld;
    }

    public void setHeroPlayingWorld(ValorWorld heroPlayingWorld) {
        this.heroPlayingWorld = heroPlayingWorld;
    }

    private ValorWorld heroPlayingWorld;

    public int getHomeRow() {
        return homeRow;
    }

    public void setHomeRow(int homeRow) {
        this.homeRow = homeRow;
    }

    public int getHomeCol() {
        return homeCol;
    }

    public void setHomeCol(int homeCol) {
        this.homeCol = homeCol;
    }

    private int homeCol;

    PrintTable printTable = new PrintTable();

    Inventory inventory = new Inventory();

    public Map<Integer, List<Item>> getHeroInventory() {
        return heroInventory;
    }

    Map<Integer, List<Item>> heroInventory =  new HashMap<>();

    Armory armoryEquiped;
    List<Weaponry> weaponriesEquiped = new ArrayList<>();

    public Armory getArmoryEquiped() {
        return armoryEquiped;
    }

    public void setArmoryEquiped(Armory armoryEquiped) {
        this.armoryEquiped = armoryEquiped;
    }

    public List<Weaponry> getWeaponriesEquiped() {
        return weaponriesEquiped;
    }

    public void setWeaponriesEquiped(List<Weaponry> weaponriesEquiped) {
        this.weaponriesEquiped = weaponriesEquiped;
    }

    public Hero(){}

    public Hero(String type, String name, int mana, int strength, int agility, int dexterity, int money, int experience){
        super(name,1, type);
        this.mana = mana;
        this.experiencePoints = experience;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.money = money;
    }

    public Hero(String type, String name, int mana, int strength, int agility, int dexterity, int money, int experience, int row, int col, Mark mark){
        super(name,1, type, mark, row, col);
        this.mana = mana;
        this.experiencePoints = experience;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.money = money;
    }


    public boolean heroAttack(Monster monster){
        Random random = new Random();
        if(random.monsterDodge(monster.getDodgeChance())){
            System.out.println(monster.getName() + "has dodged " + this.getName() + " attack!");
        }
        else {
            int damage=0;
            for (Weaponry weaponry:weaponriesEquiped){
                damage+=weaponry.damage;
            }
            monster.setHP(monster.getHP() - (int)((this.getStrength()+damage)*0.1));
            System.out.println(this.getName() + " has attacked " + monster.getName() + " for " + (int)((this.getStrength()+damage)*0.1) + " damage!");
            if (monster.getHP()<0){
                System.out.println(monster.getName()+" defeated!");
            }
        }
        System.out.println();
        return true;
    }

    public void heroBattleMove(Monster monster){
        int selection = new Menu().heroBattleSelection();
        switch(selection){
            case 1:
                this.heroAttack(monster);
                break;
            case 2:

            case 3:

            case 4:
                System.out.println("This functionality has not been added to the game yet! Sorry!!\n Check back in Later!");
                this.heroAttack(monster);
                break;
            case 5:
                new EquipArmor().makeBattleMove(this, monster);
                break;
        }
    }

    public boolean heroAction(ValorWorld world,HeroParty heroParty,MonsterParty monsterParty){
        boolean battleEnd =false;
        while(!battleEnd){
            System.out.println(this.getBoardMark().getValorMarkType() + "'S TURN");
            int selection = new Menu().valorHeroAction();
            switch(selection){
                case 1:
                    System.out.println("You have chosen to make a move");
                    battleEnd = this.makeMove(world,heroParty,monsterParty);
                    world.printWorld();
                    //Check if you have won
                    if (this.getRow()==0){
                        System.out.println("Hero "+ this.name+" go into monster's Nexus! You win! ");
                        return true;
                    }
                    break;
                case 2:
                    System.out.println("You have chosen to attack");
                    if(this.isAttackPossible(world)){
                        Monster monster = getMonsterToFight(world);
                        battleEnd=this.heroAttack(monster);
                    }
                    break;
                case 3:
                    System.out.println("You have chosen to cast a spell");
                    if(this.isAttackPossible(world)){
                        Monster monster = getMonsterToFight(world);
                        battleEnd=this.castSpell(monster);
                    }
                    break;
                case 4:
                    System.out.println("You have chosen to consume a potion");
                    battleEnd = this.consumePotion();
                    break;
                case 5:
                    System.out.println("You have chosen to equip yourself with an armor");
                    new EquipArmor().makeBattleMove(this, null);
                    break;
                case 6:
                    System.out.println("You have chosen to equip yourself with a weapon");
                    new EquipWeapon().makeBattleMove(this, null);
                    break;
                case 7:
                    System.out.println("You have chosen to teleport");
                    battleEnd = this.teleport(world);
                    break;
                case 8:
                    System.out.println("You have chosen to recall the hero to home cell.");
                    world.printWorld();
                    battleEnd = this.recall(world);
                    break;
            }
        }
        return false;
    }

    private boolean teleport(ValorWorld world) {
        System.out.println("Enter the row num you want to teleport");
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        while (row<0||row>7) {
            System.out.println("Please choose a valid number(0-7)");
            row = sc.nextInt();
        }
        System.out.println("Enter the col num you want to teleport");
        int col = sc.nextInt();
        while (col<0||col>7) {
            System.out.println("Please choose a valid number(0-7)");
            col = sc.nextInt();
        }
        if (checkAvailableHeroTeleport(world,row,col)){
            world.getValorWorld()[row][col].setHeroPresent(true);
            world.getValorWorld()[row][col].setLeftMark(this.getBoardMark());
            world.getValorWorld()[row][col].setCellContents();
            Hero hero = world.heroMapping.get(Arrays.asList(this.row,this.col));
            world.heroMapping.put(Arrays.asList(this.row,this.col),hero);
            world.getValorWorld()[this.row][this.col].setHeroPresent(false);
            world.getValorWorld()[this.row][this.col].setLeftMark(new Mark("  "));
            world.getValorWorld()[this.row][this.col].setCellContents();
            world.heroMapping.remove(Arrays.asList(this.row,this.col));
            this.setRow(row);
            this.setCol(col);
            System.out.println("Teleport Successfully");
            world.printWorld();
            return true;
        }
        return false;
    }

    private boolean checkAvailableHeroTeleport(ValorWorld world,int row, int col) {
        //occupied by another hero
        if (world.getValorWorld()[row][col].isHeroPresent()){
            System.out.println("Occupied by another hero");
            return false;
        }
        //Teleport works only between different lanes
        if (Math.abs(this.col-col)<=1){
            System.out.println("Teleport works only between different lanes");
            return false;
        }
        //adjacent to hero
        if (col>0&&world.getValorWorld()[row][col-1].isHeroPresent()
                ||col<7&&world.getValorWorld()[row][col+1].isHeroPresent()
                ||row>0&&world.getValorWorld()[row-1][col].isHeroPresent()
                ||row<7&&world.getValorWorld()[row+1][col].isHeroPresent()&&world.getValorWorld()[row][col].isMonsterPresent()){
            return true;
        }
        System.out.println("There is no hero adjacent to the cell you want to teleport to or you are trying to pass over a hero");
        return false;
    }

    public List<String> toStringArray(){
        List<String> stringArray = new ArrayList<>();
        stringArray.add(getType());
        stringArray.add(getName());
        stringArray.add(String.valueOf(getHP()));
        stringArray.add(String.valueOf(getLevel()));
        stringArray.add(String.valueOf(getMana()));
        stringArray.add(String.valueOf(getStrength()));
        stringArray.add(String.valueOf(getAgility()));
        stringArray.add(String.valueOf(getDexterity()));
        stringArray.add(String.valueOf(getMoney()));
        stringArray.add(String.valueOf(getExperiencePoints()));
        return stringArray;
    }

    public void checkExperience(){
        if(this.experiencePoints>=super.getLevel()*10) {
            this.levelUp();
        }
    }

    public boolean isAttackPossible(ValorWorld world){
        int row = this.getRow();
        int col = this.getCol();
        System.out.println("Hero checked if they can make an attack");
        if (world.getValorWorld()[row][col].monsterPresent||
                row<7&&world.getValorWorld()[row+1][col].monsterPresent||
                row>0&&world.getValorWorld()[row-1][col].monsterPresent||
                col<7&&world.getValorWorld()[row][col+1].monsterPresent||
                col<0&&world.getValorWorld()[row][col-1].monsterPresent){
            return true;
        }
        System.out.println("No monster in your range");
        System.out.println();
        return false;
    }

    public Monster getMonsterToFight(ValorWorld world){
        Monster monster=null;
        if (world.monsterMapping.get(Arrays.asList(this.getRow(),this.getCol()))!=null){
            monster = world.monsterMapping.get(Arrays.asList(this.getRow(),this.getCol()));
        }else if (world.monsterMapping.get(Arrays.asList(this.getRow()-1,this.getCol()))!=null){
            monster = world.monsterMapping.get(Arrays.asList(this.getRow()-1,this.getCol()));
        }else if (world.monsterMapping.get(Arrays.asList(this.getRow()+1,this.getCol()))!=null){
            monster = world.monsterMapping.get(Arrays.asList(this.getRow()+1,this.getCol()));
        }else if (world.monsterMapping.get(Arrays.asList(this.getRow(),this.getCol()-1))!=null){
            monster = world.monsterMapping.get(Arrays.asList(this.getRow(),this.getCol()-1));
        }else if (world.monsterMapping.get(Arrays.asList(this.getRow(),this.getCol()+1))!=null){
            monster = world.monsterMapping.get(Arrays.asList(this.getRow(),this.getCol()+1));
        }
        return monster;
    }

    public void levelUp(){
        super.setLevel(super.getLevel()+1);
        super.setHP(super.getLevel()*100);
        this.mana = (int)(this.mana *1.1);
        this.agility = (int)(this.agility *1.05);
        this.dexterity = (int)(this.dexterity *1.05);
        this.strength = (int)(this.strength *1.05);
    }

    public int getMana(){
        return this.mana;
    }

    public int getExperiencePoints(){
        return this.experiencePoints;
    }

    public int getStrength(){
        return this.strength;
    }

    public int getDexterity(){
        return this.dexterity;
    }

    public int getAgility(){
        return this.agility;
    }

    public int getMoney(){
        return this.money;
    }

    public void setMana(int mp){
        this.mana = mp;
    }

    public void setStrength(int sVal){
        this.strength = sVal;
    }

    public void setDexterity(int dVal){
        this.dexterity = dVal;
    }

    public void setAgility(int aVal){
        this.agility = aVal;
    }

    public void setMoney(int g){
        this.money = g;
    }

    public void setExperiencePoints(int ex){
        this.experiencePoints = ex;
    }

    public void addToInventory(int type, Item item){
        if(heroInventory.containsKey(type)){
            heroInventory.get(type).add(item);
        }else{
            List<Item> itemList = new ArrayList<>();
            itemList.add(item);
            heroInventory.put(type, itemList);
        }

    }

    public void removeFromInventory(int type, Item item) {
        if (heroInventory.containsKey(type)) {
            heroInventory.get(type).remove(item);

            if (heroInventory.get(type).isEmpty()) {
                heroInventory.remove(type);
            }
        } else {
            System.out.println("Type " + type + " not found in inventory.");
        }
    }

    public boolean isItemPresent(int type, Item item) {
        if (heroInventory.containsKey(type)) {
            return heroInventory.get(type).contains(item);
        } else {
            return false;
        }
    }

    public void makePurchase(int type, Item item){
        addToInventory(type, item);
        setMoney(getMoney() - item.getCost());
        item.setItemAvailability(item.getItemAvailability()-1);
    }

    public void printHeroInventory(){
        if(heroInventory.isEmpty()){
            System.out.println("The Hero's inventory is Empty!");
        }else{
            inventory.printInventory(heroInventory);
        }
    }

    public Potion retrievePotion(){
        System.out.println("Select a Potion");
        Map<Integer, List<String>> subList = new HashMap<>();
        for(int i=0; i<heroInventory.get(2).size(); i++){
            List<String> weaponryInInventory = ((Potion) heroInventory.get(2).get(i)).toStringArray();
            weaponryInInventory.add(String.valueOf(heroInventory.get(2).get(i).getItemAvailability()));
            subList.put(i, weaponryInInventory);
        }
        printTable.printSubList(subList, printTable.potionHeader);
        System.out.println("Enter number corresponding to the potion you want to select!");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        int selection = sc.nextInt();

        while (!subList.containsKey(selection)) {
            System.out.println("Please choose a valid number");
            selection = sc.nextInt();
        }

        List<String> potionDetails = subList.get(selection);
        return (Potion) heroInventory.get(2).get(selection);
    }

    public Spell retrieveSpell(){
        System.out.println("Select a Spell");
        //fire
        Map<Integer, List<String>> fireSpellList = new HashMap<>();
        if (heroInventory.get(3)!=null){
            for(int i=0; i<heroInventory.get(3).size(); i++){
                List<String> fireSpellInInventory = ((FireSpell) heroInventory.get(3).get(i)).toStringArray();
                fireSpellInInventory.add(String.valueOf(heroInventory.get(3).get(i).getItemAvailability()));
                fireSpellList.put(i, fireSpellInInventory);
            }
            if (fireSpellList.values().size()!=0){
                System.out.println(3 + ". Fire Spell");
                printTable.printSubList(fireSpellList, printTable.spellHeader);
            }
        }
        //ice
        Map<Integer, List<String>> iceSpellList = new HashMap<>();
        if (heroInventory.get(4)!=null){
            for(int i=0; i<heroInventory.get(4).size(); i++){
                List<String> iceSpellInInventory = ((IceSpell) heroInventory.get(4).get(i)).toStringArray();
                iceSpellInInventory.add(String.valueOf(heroInventory.get(4).get(i).getItemAvailability()));
                iceSpellList.put(i, iceSpellInInventory);
            }
            if (iceSpellList.values().size()!=0){
                System.out.println(4 + ". Ice Spell");
                printTable.printSubList(iceSpellList, printTable.spellHeader);
            }
        }

        //lightning
        Map<Integer, List<String>> lightningSpellList = new HashMap<>();
        if (heroInventory.get(5)!=null){
            for(int i=0; i<heroInventory.get(5).size(); i++){
                List<String> lightningSpellInInventory = ((LightningSpell) heroInventory.get(5).get(i)).toStringArray();
                lightningSpellInInventory.add(String.valueOf(heroInventory.get(5).get(i).getItemAvailability()));
                lightningSpellList.put(i, lightningSpellInInventory);
            }
            if (lightningSpellList.values().size()!=0){
                System.out.println(5 + ". Lightning Spell");
                printTable.printSubList(lightningSpellList, printTable.spellHeader);
            }
        }
        System.out.println("Enter the number corresponding to the spell type you want to cast");
        Scanner sc = new Scanner(System.in);
        int spellTypeSelection = sc.nextInt();
        while (spellTypeSelection!=3&&spellTypeSelection!=4&&spellTypeSelection!=5) {
            System.out.println("Please choose a valid number");
            spellTypeSelection = sc.nextInt();
        }
        System.out.println();
        System.out.println("Enter the number corresponding to the item you want to choose");
        sc = new Scanner(System.in);
        int spellSelection = sc.nextInt();

        while (!(spellSelection < heroInventory.get(spellTypeSelection).size())) {
            System.out.println("Please choose a valid number");
            spellSelection = sc.nextInt();
        }

        return (Spell) heroInventory.get(spellTypeSelection).get(spellSelection);
    }

    public Armory retrieveArmor(){
        System.out.println("Select an Armor");
        Map<Integer, List<String>> subList = new HashMap<>();
        for(int i=0; i<heroInventory.get(0).size(); i++){
            List<String> armorInInventory = ((Armory) heroInventory.get(0).get(i)).toStringArray();
            armorInInventory.add(String.valueOf(heroInventory.get(0).get(i).getItemAvailability()));
            subList.put(i, armorInInventory);
        }
        printTable.printSubList(subList, printTable.armoryHeader);
        System.out.println("Enter number corresponding to the armor you want to select!");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        int selection = sc.nextInt();

        while (!subList.containsKey(selection)) {
            System.out.println("Please choose a valid number");
            selection = sc.nextInt();
        }

        return (Armory) heroInventory.get(0).get(selection);
    }

    public Weaponry retrieveWeapon(){
        System.out.println("Select an Weapon");
        Map<Integer, List<String>> subList = new HashMap<>();
        for(int i=0; i<heroInventory.get(1).size(); i++){
            List<String> weaponInInventory = ((Weaponry) heroInventory.get(1).get(i)).toStringArray();
            if (weaponInInventory==null){
                weaponInInventory = new ArrayList<>();
            }
            weaponInInventory.add(String.valueOf(heroInventory.get(1).get(i).getItemAvailability()));
            subList.put(i, weaponInInventory);
        }
        printTable.printSubList(subList, printTable.weaponryHeader);
        System.out.println("Enter number corresponding to the weapon you want to select!");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        int selection = sc.nextInt();

        while (!subList.containsKey(selection)) {
            System.out.println("Please choose a valid number");
            selection = sc.nextInt();
        }

        return (Weaponry) heroInventory.get(1).get(selection);
    }


    public boolean recall(ValorWorld world){
        if(this.getRow() == this.getHomeRow() && this.getCol()==this.getHomeCol()){
            System.out.println("You are already in your home nexus cell. Please choose a different action!");
            return false;
        }else{
            Hero hero = world.heroMapping.get(Arrays.asList(this.getRow(),this.getCol()));
            world.heroMapping.remove(Arrays.asList(this.getRow(),this.getCol()));
            world.getValorWorld()[this.getRow()][this.getCol()].setLeftMark(new Mark("  "));
            world.getValorWorld()[this.getRow()][this.getCol()].setCellContents();
            world.getValorWorld()[this.getRow()][this.getCol()].setHeroPresent(false);
            this.setRow(this.getHomeRow());
            this.setCol(this.getHomeCol());
            world.heroMapping.put(Arrays.asList(this.getRow(),this.getCol()),hero);
            world.getValorWorld()[this.getRow()][this.getCol()].setLeftMark(this.getBoardMark());
            world.getValorWorld()[this.getRow()][this.getCol()].setCellContents();
            world.getValorWorld()[this.getRow()][this.getCol()].setHeroPresent(true);
            System.out.println("Hero successfully recalled to the home cell.");
            world.printWorld();
            return true;
        }
    }

    public boolean castSpell(Monster monster){
        if(!(this.getHeroInventory().containsKey(3))&&
                !(this.getHeroInventory().containsKey(4))&&
                !(this.getHeroInventory().containsKey(5))){
            System.out.println("You have no spell in your inventory to cast!");
            return false;
        }else{
            Spell spell =  this.retrieveSpell();
            if (mana<spell.manaCost){
                System.out.println("You have no mana to cast!");
                return false;
            }
            int type=0;
            switch (spell.getClass().getSimpleName()) {
                case "FireSpell":
                    monster.defense=(int)(0.9*monster.defense);
                    type=3;
                    break;
                case "IceSpell":
                    monster.damage=(int)(0.9*monster.damage);
                    type=4;
                    break;
                case "LightningSpell":
                    monster.dodgeChance=(int)(0.9*monster.dodgeChance);
                    type=5;
                    break;
            }
            monster.setHP(monster.getHP() - spell.damage);
            System.out.println(this.getName() + " cast "+spell.name +" to attack "+ monster.getName() + " for " + spell.damage + " damage!");
            if (monster.getHP()<0){
                System.out.println(monster.getName()+" defeated!");
            }
            System.out.println("Spell successfully consumed.");
            this.removeFromInventory(type, spell);
            return true;
        }
    }

    public boolean consumePotion(){
        if(!(this.getHeroInventory().containsKey(2))){
            System.out.println("You have no potions in your inventory to consume!");
            return false;
        }else{
            Potion potion =  this.retrievePotion();
            switch (potion.attributeAffected) {
                case "Health":
                    this.setHP(this.getHP() + potion.getAttributeIncrease());
                    break;
                case "Strength":
                    this.setStrength(this.getStrength() + potion.getAttributeIncrease());
                    break;
                case "Mana":
                    this.setMana(this.getMana() + potion.getAttributeIncrease());
                    break;
                case "Agility":
                    this.setAgility(this.getAgility() + potion.getAttributeIncrease());
                    break;
                case "Dexterity":
                    this.setDexterity(this.getDexterity() + potion.getAttributeIncrease());
                    break;
            }
            System.out.println("Potion successfully consumed.");
            System.out.println(potion.getAttributeAffected() + " has increased by "+ potion.getAttributeIncrease());
            this.removeFromInventory(2, potion);
            return true;
        }
    }

    public boolean makeMove(ValorWorld world,HeroParty heroParty,MonsterParty monsterParty){
        Scanner sc = new Scanner(System.in);
        boolean returnValue = false;
        char input;
        int beforeMoveRow = this.getRow();
        int beforeMoveCol = this.getCol();
        System.out.println("Enter your move!");
        System.out.print("w - move up\t a - move left\ts - move down\td - move right\tm - enter market\ti - info\tq - quit\n");
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
            if (!checkAvailableHeroMoves(world).contains(input)) {
                System.out.println("Please enter a valid move");
                if (input=='m'){
                    System.out.println("You are not in Nexus!");
                }
            }else{
                break;
            }
        }
        switch(input){
            case 'w':
                this.setRow(beforeMoveRow - 1);
                returnValue = true;
                break;
            case 'a':
                this.setCol(beforeMoveCol - 1);
                returnValue = true;
                break;
            case 's':
                this.setRow(beforeMoveRow + 1);
                returnValue = true;
                break;
            case 'd':
                this.setCol(beforeMoveCol + 1);
                returnValue = true;
                break;
            case 'm':
                System.out.println("You have chosen to enter the market!");
                Map<List<Integer>, Market> marketMapping = world.marketMapping;
                Market market;
                List<Integer> previousPositionList = Arrays.asList(row, col);
                if(marketMapping.isEmpty() || !marketMapping.containsKey(previousPositionList)){
                    market = new Market();
                    marketMapping.put(previousPositionList, market);
                }else{
                    market = marketMapping.get(previousPositionList);
                    market.printMarket();
                }
                enterMarket(market);
                return returnValue;
            case 'i':
                heroParty.printHeroParty();
                monsterParty.printMonsterParty();
                return returnValue;
            case 'q':
                return returnValue;
        }
        world.getValorWorld()[this.getRow()][this.getCol()].setHeroPresent(true);
        world.getValorWorld()[this.getRow()][this.getCol()].setLeftMark(this.getBoardMark());
        world.getValorWorld()[this.getRow()][this.getCol()].setCellContents();
        Hero hero = world.heroMapping.get(Arrays.asList(beforeMoveRow,beforeMoveCol));
        world.heroMapping.put(Arrays.asList(this.getRow(),this.getCol()),hero);
        world.getValorWorld()[beforeMoveRow][beforeMoveCol].setHeroPresent(false);
        world.getValorWorld()[beforeMoveRow][beforeMoveCol].setLeftMark(new Mark("  "));
        world.getValorWorld()[beforeMoveRow][beforeMoveCol].setCellContents();
        world.heroMapping.remove(Arrays.asList(beforeMoveRow,beforeMoveCol));
        return returnValue;
    }

    public void enterMarket(Market market) {
        System.out.println();
        System.out.println(getName()+" is now entering the market.");
        System.out.println();
        int selection = new Menu().atMarket();
        switch(selection){
            case 1:
                market.sellItemToHero(this);
                break;
            case 2:
                if (!this.getHeroInventory().isEmpty()) {
                    market.buyItemFromHero(this);
                }
                break;
            case 3:
                System.out.println("Exiting the market!");
                break;
        }
    }

    public ArrayList<java.lang.Character> checkAvailableHeroMoves(ValorWorld world){
        int row = this.getRow();
        int col = this.getCol();
//        List<Integer> positionList = Arrays.asList(row, col);
        ArrayList<java.lang.Character> availableMoves = new ArrayList<>();
        availableMoves.add(('m'));availableMoves.add(('w'));availableMoves.add('a');availableMoves.add('s');availableMoves.add('d');availableMoves.add('i');availableMoves.add('q');
        if(col == 7 || world.getValorWorld()[row][col+1].getType() == 'I' || world.getValorWorld()[row][col+1].isHeroPresent()){
            availableMoves.remove(4);
        }
        if(row == 7 || world.getValorWorld()[row+1][col].getType() == 'I' || world.getValorWorld()[row+1][col].isHeroPresent()){
            availableMoves.remove(3);
        }
        if(col == 0 || world.getValorWorld()[row][col-1].getType() == 'I' || world.getValorWorld()[row][col-1].isHeroPresent()){
            availableMoves.remove(2);
        }
        if(row==0 || world.getValorWorld()[row-1][col].getType() == 'I' || world.getValorWorld()[row-1][col].isHeroPresent()
                //can not pass over monster
                ||world.getValorWorld()[row][col].isMonsterPresent()
                ||col+1<world.getSize()&&world.getValorWorld()[row][col+1].isMonsterPresent()
                ||col-1>=0&&world.getValorWorld()[row][col-1].isMonsterPresent()){
            availableMoves.remove(1);
        }
        if (row!=7){
            availableMoves.remove(0);
        }
        return availableMoves;
    }

}
