import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Inventory {

    Map<Integer, Map<Integer, Item>> globalInventory = new HashMap<>();

    PrintTable printTable = new PrintTable();

    FromFile fromFile = new FromFile();

    Random random = new Random();

    List<String> categoriesList = Arrays.asList("Armory", "Weaponry", "Potion", "FireSpell", "IceSpell", "LightningSpell");


    Item item;

    public Inventory(){
        generateGlobalInventory();
    }

    public Map<Integer, List<Item>> generateMarketInventory(){
        Map<Integer, List<Item>> marketInventory = new HashMap<>();
        List<Integer> marketCategories = random.randomList(categoriesList.size());
        for(Integer categories : marketCategories){
            List<Integer> marketProducts = random.randomList(globalInventory.get(categories).size());
            List<Item> productList = new ArrayList<>();
            for (Integer marketProduct : marketProducts) {
                productList.add(globalInventory.get(categories).get(marketProduct));
                globalInventory.get(categories).get(marketProduct).setItemAvailability(ThreadLocalRandom.current().nextInt(1, 10));
            }
            marketInventory.put(categories, productList);
        }
        return marketInventory;
    }

    public void generateGlobalInventory(){
        for(int i=0; i<categoriesList.size(); i++){
            Map<Integer, Item> bufferInventory = new HashMap<>();
            switch(i){
                case 0:
                    Map<Integer, List<String>> armoryMap = fromFile.getArmoryDetails();
                    for(int j=0; j<armoryMap.size(); j++){
                        List<String> armoryArray = armoryMap.get(j);
                        Item armory =  new Armory("Armory", armoryArray.get(0), Integer.parseInt(armoryArray.get(1)), Integer.parseInt(armoryArray.get(2)), Integer.parseInt(armoryArray.get(3)));
                        bufferInventory.put(j, armory);
                    }
                    globalInventory.put(i, bufferInventory);
                    break;
                case 1:
                    Map<Integer, List<String>> weaponryMap = fromFile.getWeaponryDetails();
                    for(int j=0; j<weaponryMap.size(); j++){
                        List<String> armoryArray = weaponryMap.get(j);
                        Item weaponry =  new Weaponry("Weaponry", armoryArray.get(0), Integer.parseInt(armoryArray.get(1)), Integer.parseInt(armoryArray.get(2)), Integer.parseInt(armoryArray.get(3)), Integer.parseInt(armoryArray.get(4)));
                        bufferInventory.put(j, weaponry);
                    }
                    globalInventory.put(i, bufferInventory);
                    break;
                case 2:
                    Map<Integer, List<String>> potionMap = fromFile.getPotionDetails();
                    for(int j=0; j<potionMap.size(); j++){
                        List<String> armoryArray = potionMap.get(j);
                        Item potion =  new Potion("Potion", armoryArray.get(0), Integer.parseInt(armoryArray.get(1)), Integer.parseInt(armoryArray.get(2)), Integer.parseInt(armoryArray.get(3)), armoryArray.get(4), Integer.parseInt(armoryArray.get(5)));
                        bufferInventory.put(j, potion);
                    }
                    globalInventory.put(i, bufferInventory);
                    break;
                case 3:
                    Map<Integer, List<String>> fireSpellMap = fromFile.getFireSpellDetails();
                    for(int j=0; j<fireSpellMap.size(); j++){
                        List<String> armoryArray = fireSpellMap.get(j);
                        Item fireSpell =  new FireSpell("FireSpell", armoryArray.get(0), Integer.parseInt(armoryArray.get(1)), Integer.parseInt(armoryArray.get(2)), Integer.parseInt(armoryArray.get(3)), Integer.parseInt(armoryArray.get(4)), Integer.parseInt(armoryArray.get(5)));
                        bufferInventory.put(j, fireSpell);
                    }
                    globalInventory.put(i, bufferInventory);
                    break;
                case 4:
                    Map<Integer, List<String>> iceSpellMap = fromFile.getIceSpellDetails();
                    for(int j=0; j<iceSpellMap.size(); j++){
                        List<String> armoryArray = iceSpellMap.get(j);
                        Item iceSpell =  new IceSpell("IceSpell", armoryArray.get(0), Integer.parseInt(armoryArray.get(1)), Integer.parseInt(armoryArray.get(2)), Integer.parseInt(armoryArray.get(3)), Integer.parseInt(armoryArray.get(4)), Integer.parseInt(armoryArray.get(5)));
                        bufferInventory.put(j, iceSpell);
                    }
                    globalInventory.put(i, bufferInventory);
                    break;
                case 5:
                    Map<Integer, List<String>> lightningSpellMap = fromFile.getLightningSpellDetails();
                    for(int j=0; j<lightningSpellMap.size(); j++){
                        List<String> armoryArray = lightningSpellMap.get(j);
                        Item lightningSpell =  new LightningSpell("LightningSpell", armoryArray.get(0), Integer.parseInt(armoryArray.get(1)), Integer.parseInt(armoryArray.get(2)), Integer.parseInt(armoryArray.get(3)), Integer.parseInt(armoryArray.get(4)), Integer.parseInt(armoryArray.get(5)));
                        bufferInventory.put(j, lightningSpell);
                    }
                    globalInventory.put(i, bufferInventory);
                    break;
            }
        }
    }



    public void printInventory(Map<Integer, List<Item>> inventory){
        for(Integer category : inventory.keySet()){
            Map<Integer, List<String>> subList = new HashMap<>();
            switch(category){
                case 0:
                    System.out.println(category + ". Armory");
                    for(int i=0; i<inventory.get(0).size(); i++){
                        List<String> armoryInInventory = ((Armory) inventory.get(0).get(i)).toStringArray();
                        armoryInInventory.add(String.valueOf(inventory.get(0).get(i).getItemAvailability()));
                        subList.put(i, armoryInInventory);
                    }
                    printTable.printSubList(subList, printTable.armoryHeader);
                    break;
                case 1:
                    System.out.println(category + ". Weaponry");
                    for(int i=0; i<inventory.get(1).size(); i++){
                        List<String> weaponryInInventory = ((Weaponry) inventory.get(1).get(i)).toStringArray();
                        weaponryInInventory.add(String.valueOf(inventory.get(1).get(i).getItemAvailability()));
                        subList.put(i, weaponryInInventory);
                    }
                    printTable.printSubList(subList, printTable.weaponryHeader);
                    break;
                case 2:
                    System.out.println(category + ". Potion");
                    for(int i=0; i<inventory.get(2).size(); i++){
                        List<String> weaponryInInventory = ((Potion) inventory.get(2).get(i)).toStringArray();
                        weaponryInInventory.add(String.valueOf(inventory.get(2).get(i).getItemAvailability()));
                        subList.put(i, weaponryInInventory);
                    }
                    printTable.printSubList(subList, printTable.potionHeader);
                    break;
                case 3:
                    System.out.println(category + ". Fire Spell");
                    for(int i=0; i<inventory.get(3).size(); i++){
                        List<String> weaponryInInventory = ((FireSpell) inventory.get(3).get(i)).toStringArray();
                        weaponryInInventory.add(String.valueOf(inventory.get(3).get(i).getItemAvailability()));
                        subList.put(i, weaponryInInventory);
                    }
                    printTable.printSubList(subList, printTable.spellHeader);
                    break;
                case 4:
                    System.out.println(category + ". Ice Spell");
                    for(int i=0; i<inventory.get(4).size(); i++){
                        List<String> weaponryInInventory = ((IceSpell) inventory.get(4).get(i)).toStringArray();
                        weaponryInInventory.add(String.valueOf(inventory.get(4).get(i).getItemAvailability()));
                        subList.put(i, weaponryInInventory);
                    }
                    printTable.printSubList(subList, printTable.spellHeader);
                    break;
                case 5:
                    System.out.println(category + ". Lightning Spell");
                    for(int i=0; i<inventory.get(category).size(); i++){
                        List<String> weaponryInInventory = ((LightningSpell) inventory.get(category).get(i)).toStringArray();
                        weaponryInInventory.add(String.valueOf(inventory.get(category).get(i).getItemAvailability()));
                        subList.put(i, weaponryInInventory);
                    }
                    printTable.printSubList(subList, printTable.spellHeader);
                    break;
            }
        }

    }

}
