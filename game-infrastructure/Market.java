import java.util.*;

public class Market {

    Scanner sc;

    Inventory inventory =  new Inventory();

    Map<Integer, List<Item>> market;

    public Market(){
        market = inventory.generateMarketInventory();
        printMarket();
    }

    public void printMarket(){
        inventory.printInventory(market);
    }

    public void buyItemFromHero(Hero hero){
        hero.printHeroInventory();
        Map<Integer, List<Item>> inventory = hero.getHeroInventory();
        System.out.println("Enter the number corresponding to the item type you want to sell");
        sc = new Scanner(System.in);
        int itemTypeSelection = sc.nextInt();

        while (!inventory.containsKey(itemTypeSelection)) {
            System.out.println("Please choose a valid number");
            itemTypeSelection = sc.nextInt();
        }

        System.out.println("Enter the number corresponding to the item you want to choose");
        sc = new Scanner(System.in);
        int itemSelection = sc.nextInt();

        while (!(itemSelection < inventory.get(itemTypeSelection).size())) {
            System.out.println("Please choose a valid number");
            itemSelection = sc.nextInt();
        }

        if(market.containsKey(itemTypeSelection)){
            market.get(itemTypeSelection).add(inventory.get(itemTypeSelection).get(itemSelection));
        }else{
            List<Item> itemList = new ArrayList<>();
            itemList.add(inventory.get(itemTypeSelection).get(itemSelection));
            market.put(itemTypeSelection, itemList);
        }
        hero.setMoney(hero.getMoney()+(int)(inventory.get(itemTypeSelection).get(itemSelection).getCost()*0.5));
        hero.removeFromInventory(itemTypeSelection, inventory.get(itemTypeSelection).get(itemSelection));
    }

    public void sellItemToHero(Hero hero){
        printMarket();
        boolean flag = true;
        while(flag){
            System.out.println("Enter the number corresponding to the item type you want to purchase");
            sc = new Scanner(System.in);
            int itemTypeSelection = sc.nextInt();

            while (!market.containsKey(itemTypeSelection)) {
                System.out.println("Please choose a valid number");
                itemTypeSelection = sc.nextInt();
            }

            System.out.println("Enter the number corresponding to the item you want to choose");
            sc = new Scanner(System.in);
            int itemSelection = sc.nextInt();

            while (!(itemSelection < market.get(itemTypeSelection).size())) {
                System.out.println("Please choose a valid number");
                itemSelection = sc.nextInt();
            }

            if(hero.isItemPresent(itemTypeSelection, market.get(itemTypeSelection).get(itemSelection))){
                System.out.println("This item already exists in the hero's inventory.");
            }

            if(checkValidBuy(market.get(itemTypeSelection).get(itemSelection), hero)){
                hero.makePurchase(itemTypeSelection, market.get(itemTypeSelection).get(itemSelection));
                System.out.println("Purchase Successful");
                flag = false;
            }else{
                System.out.println("You don't have enough gold or your level isn't enough to equip this. ");
                System.out.println("Purchase unsuccessful");
                flag = false;
            }
            System.out.println("Hero:"+hero.name+"'s inventory: ");
            inventory.printInventory(hero.getHeroInventory());
        }

    }

    public boolean checkValidBuy(Item item, Hero hero){
        return hero.getLevel() >= item.levelRequired && !(hero.getMoney() < item.getCost());
    }

}
