import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LegendsOfValor implements Game, RolePlayingGame{
    static boolean isGameOver=false;
    HeroParty heroParty;
    Player player;
    MonsterParty monsterParty;

    ValorWorld world;

    Scanner sc;

    public LegendsOfValor(){
        player = new Player(5, "ValorHeroes", 1);
        this.sc = new Scanner(System.in);
        int confirmation = new Menu().confirm();
        if (confirmation == 2) {
            System.out.println("Sorry to see you leave");
            System.exit(0);
        }
        System.out.println();
        System.out.print("Welcome, brave adventurer, to the mystical realm of Legends of Valor!\nEmbark on a journey where courage and wisdom are your greatest allies, along with the other heroes of course.\nYour quest is to ensure the survival of our heroes and navigate the perilous path to the Monsters' Nexus cell.\nThe destiny of this fantastical world lies in your hands. Are you ready to become a legend?\n\nSHARPEN YOUR WEAPONS, GATHER YOUR SUPPLIES, STRATEGIZE WITH YOUR FELLOW HEROES and LET THE EPIC QUEST BEGIN!!!\n\n");
        initializeWorld();
        System.out.println("You will choose 3 heroes from any category to form your hero party. Choose wisely as you cannot change your choice during gameplay");
        initializeCharacters();
        System.out.println("The Party of Monsters that were randomly generated to match the level of your heroes, the ones you need to defeat and evade to complete the conquest, are as follows : ");
        monsterParty.printMonsterParty();
        world.printWorld();
    }

    @Override
    public void play() {
        int round=1;
        while(!isGameOver){
            System.out.println("Round No."+round+" STARTS");
            System.out.println();
            for (Hero hero: heroParty.getHeroParty()) {
                isGameOver=hero.heroAction(world,heroParty,monsterParty);
                if (isGameOver){
                    break;
                }
            }
            if (isGameOver){
                break;
            }
            System.out.println("It is Monster's turn now");
            System.out.println();
            List<Monster> defeatedMonsterList = new ArrayList<>();
            for (Monster monster : monsterParty.getMonsterParty()){
                if (monster.HP>0){
                    isGameOver=monster.monsterAction(world);
                    if (isGameOver){
                        break;
                    }
                }else {
                    defeatedMonsterList.add(monster);
                }
            }
            removeMonsters(defeatedMonsterList);

            System.out.println("Round No."+round+" ENDS");
            System.out.println();
            round++;
            if (round%8==0){
                System.out.println("New Monsters Spawn");
                spawnNewMonsters();
            }
            world.printWorld();
        }
        System.out.println("Game Over");
        this.afterGame();
    }

    private void award() {
        int maxLevel = -1;
        for (Hero hero: heroParty.getHeroParty()){
            maxLevel = maxLevel>hero.level?maxLevel:hero.level;
        }
        heroParty.setMaxLevel(maxLevel);
        int num = heroParty.getHeroParty().size();
        for (Hero hero:heroParty.getHeroParty()){
            // who did not faint
            if (hero.HP>0){
                hero.money+=maxLevel*100;
                hero.experiencePoints+=num*2;
                if (hero.experiencePoints>hero.level*10){
                    hero.levelUp();
                }
            }else {
                hero.setHPWithLevel(hero.level);
                hero.setMPWithLevel(hero.level);
                hero.recall(world);
            }
        }
    }

    private void removeMonsters(List<Monster> monsters) {
        for (Monster monster:monsters){
            monsterParty.getMonsterParty().remove(monster);
            world.getValorWorld()[monster.row][monster.col].setMonsterPresent(false);
            world.getValorWorld()[monster.row][monster.col].setRightMark(new Mark("  "));
            world.getValorWorld()[monster.row][monster.col].setCellContents();
            world.monsterMapping.remove(Arrays.asList(monster.row,monster.col));
        }
        award();
    }

    private void spawnNewMonsters() {
        monsterParty.spawnNewMonsters(heroParty,heroParty.heroPartySize);
        int j=0;
        for (int i=monsterParty.getMonsterParty().size()-3; i< monsterParty.getMonsterParty().size(); i++) {
            Monster monster = monsterParty.getMonsterParty().get(i);
            monster.setRow(0);
            monster.setCol((j*3)+1);
            Mark mark = new Mark("M"+i);
            monster.setBoardMark(mark);
            world.getValorWorld()[monster.getRow()][monster.getCol()].setMonsterPresent(true);
            world.getValorWorld()[monster.getRow()][monster.getCol()].setRightMark(mark);
            world.getValorWorld()[monster.getRow()][monster.getCol()].setCellContents();
            world.monsterMapping.put(Arrays.asList(monster.getRow(),monster.getCol()),monster);
            j++;
        }
    }

    @Override
    public void afterGame() {
        isGameOver = false;
        int choice;
        System.out.println();
        choice = new Menu().playAgain();
        if (choice == 1) {
            System.out.println("Here we go again!!");
            initializeWorld();
            initializeCharacters();
            monsterParty.printMonsterParty();
            world.printWorld();
            this.play();
        }else if (choice == 2) {
            System.out.println("Sorry to see you leave");
            sc.close();
            System.exit(0);
        }
    }

    @Override
    public void initializeCharacters() {
        heroParty = new HeroParty(3);
        for (int i=0; i< heroParty.getHeroPartySize(); i++) {
            Hero hero = heroParty.getHeroParty().get(i);
            hero.setRow(7);
            hero.setCol(i*3);
            hero.setHomeRow(hero.getRow());
            hero.setHomeCol(hero.getCol());
            Mark mark = new Mark(Colour.WHITE_BACKGROUND + Colour.PURPLE + "H"+i + Colour.RESET);
            hero.setBoardMark(mark);
            world.getValorWorld()[hero.getRow()][hero.getCol()].setHeroPresent(true);
            world.getValorWorld()[hero.getRow()][hero.getCol()].setLeftMark(mark);
            world.getValorWorld()[hero.getRow()][hero.getCol()].setCellContents();
            world.heroMapping.put(Arrays.asList(hero.getRow(),hero.getCol()),hero);
        }
        monsterParty = new MonsterParty(heroParty, 3);
        for (int i=0; i< monsterParty.getMonsterParty().size(); i++) {
            Monster monster = monsterParty.getMonsterParty().get(i);
            monster.setRow(0);
            monster.setCol((i*3)+1);
            Mark mark = new Mark(Colour.MAGENTA_BACKGROUND + "M"+i+ Colour.RESET);
            monster.setBoardMark(mark);
            world.getValorWorld()[monster.getRow()][monster.getCol()].setMonsterPresent(true);
            world.getValorWorld()[monster.getRow()][monster.getCol()].setRightMark(mark);
            world.getValorWorld()[monster.getRow()][monster.getCol()].setCellContents();
            world.monsterMapping.put(Arrays.asList(monster.getRow(),monster.getCol()),monster);
        }
    }

    @Override
    public void initializeWorld() {
        world = new ValorWorld();
    }

}
