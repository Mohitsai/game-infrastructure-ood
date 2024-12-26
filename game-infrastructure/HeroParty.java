import java.util.*;

public class HeroParty {

    Scanner sc;

    FromFile fromFile;

    PrintTable print;

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    int maxLevel = 1;
    List<String> selectedHeroes = new ArrayList<>();
    public int getHeroPartySize() {
        return heroPartySize;
    }

    public void setHeroPartySize(int heroPartySize) {
        this.heroPartySize = heroPartySize;
    }

    String[] heroTypes = {"Warrior", "Sorcerer", "Paladin"};

    int heroPartySize;

    public List<Hero> getHeroParty() {
        return heroParty;
    }

    public void setHeroParty(List<Hero> heroParty) {
        this.heroParty = heroParty;
    }

    List<Hero> heroParty;

    public HeroParty(int heroPartySize){
        setHeroPartySize(heroPartySize);
        heroParty = new ArrayList<>();
        fromFile = new FromFile();
        print = new PrintTable();
        Map<Integer, Map<Integer, List<String>>> heroList = fromFile.getHeroDetails();
        System.out.println();
        for(int i=0; i<heroPartySize; i++) {
            for (Integer key : heroList.keySet()) {
                switch (key) {
                    case 0:
                        System.out.println(key + ". Warriors");
                        break;
                    case 1:
                        System.out.println(key + ". Sorcerers");
                        break;
                    case 2:
                        System.out.println(key + ". Paladins");
                        break;
                }
                print.printSubList(heroList.get(key), print.initialHeroHeader);
                System.out.println();
            }
            System.out.println("Enter the number corresponding to the Hero Type you want to choose from");
            sc = new Scanner(System.in);
            int heroTypeSelection = sc.nextInt();

            while (!fromFile.allHeroes.containsKey(heroTypeSelection)) {
                System.out.println("Please choose a valid number");
                heroTypeSelection = sc.nextInt();
            }

            System.out.println();
            print.printSubList(heroList.get(heroTypeSelection), print.initialHeroHeader);
            System.out.println();

            System.out.println("Enter the number corresponding to the Hero you want to choose");
            sc = new Scanner(System.in);
            int heroSelection = sc.nextInt();

            while (!fromFile.allHeroes.get(heroTypeSelection).containsKey(heroSelection)) {
                System.out.println("Please choose a valid number");
                heroSelection = sc.nextInt();
            }
            while (!heroParty.isEmpty() && selectedHeroes.contains(fromFile.allHeroes.get(heroTypeSelection).get(heroSelection).get(0))) {
                System.out.println("Choose a new hero. You cannot choose heroes that have already been selected.");
                heroSelection = sc.nextInt();
            }

            List<String> heroArray = fromFile.allHeroes.get(heroTypeSelection).get(heroSelection);
            switch(heroTypeSelection) {
                case 0:
                    Warrior warrior = new Warrior(heroTypes[heroTypeSelection],heroArray.get(0), Integer.parseInt(heroArray.get(1)), Integer.parseInt(heroArray.get(2)), Integer.parseInt(heroArray.get(3)), Integer.parseInt(heroArray.get(4)), Integer.parseInt(heroArray.get(5)), Integer.parseInt(heroArray.get(6)));
                    heroParty.add(warrior);
                    selectedHeroes.add(warrior.getName());
                    break;
                case 1:
                    Sorcerer sorcerer = new Sorcerer(heroTypes[heroTypeSelection],heroArray.get(0), Integer.parseInt(heroArray.get(1)), Integer.parseInt(heroArray.get(2)), Integer.parseInt(heroArray.get(3)), Integer.parseInt(heroArray.get(4)), Integer.parseInt(heroArray.get(5)), Integer.parseInt(heroArray.get(6)));
                    heroParty.add(sorcerer);
                    selectedHeroes.add(sorcerer.getName());
                    break;
                case 2:
                    Paladin paladin = new Paladin(heroTypes[heroTypeSelection],heroArray.get(0), Integer.parseInt(heroArray.get(1)), Integer.parseInt(heroArray.get(2)), Integer.parseInt(heroArray.get(3)), Integer.parseInt(heroArray.get(4)), Integer.parseInt(heroArray.get(5)), Integer.parseInt(heroArray.get(6)));
                    heroParty.add(paladin);
                    selectedHeroes.add(paladin.getName());
                    break;
            }

        }
        printHeroParty();
    }

    public void printHeroParty(){
        System.out.println();
        System.out.println("The Hero Party you have chosen is as follows : ");
        Map<Integer, List<String>> heroPartyMap = new HashMap<>();
        for(int i=0; i<heroParty.size(); i++){
            heroPartyMap.put(i, heroParty.get(i).toStringArray());
        }
        print.printSubList(heroPartyMap, print.gameHeroHeader);
        System.out.println();

    }

    public void enterMarket(Market market){
        for(Hero hero : heroParty){
            System.out.println();
            System.out.println(hero.getName()+" is now entering the market.");
            System.out.println();
            int selection = new Menu().atMarket();
            switch(selection){
                case 1:
                    market.sellItemToHero(hero);
                    break;
                case 2:
                    if (!hero.getHeroInventory().isEmpty()) {
                        market.buyItemFromHero(hero);
                    }
                    break;
                case 3:
                    System.out.println("Exiting the market!");
                    break;
            }

        }
    }


}
