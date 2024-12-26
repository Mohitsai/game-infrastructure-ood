import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MonsterParty {

    public List<Monster> getMonsterParty() {
        return monsterParty;
    }

    public void setMonsterParty(List<Monster> monsterParty) {
        this.monsterParty = monsterParty;
    }

    List<Monster> monsterParty = new ArrayList<>();
    FromFile fromFile = new FromFile();

    Random random = new Random();

    PrintTable print = new PrintTable();

    Map<Integer, Map<Integer, List<String>>> monsterMapping = fromFile.getMonsterDetails();

    List<String> monsterCategories = Arrays.asList("Dragon", "Exoskeleton", "Spirit");

    public MonsterParty(HeroParty heroParty, int heroPartySize){
        Map<Integer, Map<Integer, List<String>>> scaledMonsterMapping = new HashMap<>();
        scaledMonsterMapping = getScaledMonsterMapping(heroParty);
        for(int i = 0; i<heroPartySize; i++){
            int monsterCategory = ThreadLocalRandom.current().nextInt(0, scaledMonsterMapping.size());
            int monsterChoice = ThreadLocalRandom.current().nextInt(0, scaledMonsterMapping.get(monsterCategory).size());
            List<String> chosenMonsterDetails = scaledMonsterMapping.get(monsterCategory).get(monsterChoice);
            Monster chosenMonster = new Monster(monsterCategories.get(monsterCategory), chosenMonsterDetails.get(0), Integer.parseInt(chosenMonsterDetails.get(1)), Integer.parseInt(chosenMonsterDetails.get(2)), Integer.parseInt(chosenMonsterDetails.get(3)), Integer.parseInt(chosenMonsterDetails.get(4)));
            monsterParty.add(chosenMonster);
        }
    }

    public void spawnNewMonsters(HeroParty heroParty, int heroPartySize){
        Map<Integer, Map<Integer, List<String>>> scaledMonsterMapping = new HashMap<>();
        scaledMonsterMapping = getScaledMonsterMapping(heroParty);
        for(int i = 0; i<heroPartySize; i++){
            int monsterCategory = ThreadLocalRandom.current().nextInt(0, scaledMonsterMapping.size());
            int monsterChoice = ThreadLocalRandom.current().nextInt(0, scaledMonsterMapping.get(monsterCategory).size());
            List<String> chosenMonsterDetails = scaledMonsterMapping.get(monsterCategory).get(monsterChoice);
            Monster chosenMonster = new Monster(monsterCategories.get(monsterCategory), chosenMonsterDetails.get(0), Integer.parseInt(chosenMonsterDetails.get(1)), Integer.parseInt(chosenMonsterDetails.get(2)), Integer.parseInt(chosenMonsterDetails.get(3)), Integer.parseInt(chosenMonsterDetails.get(4)));
            monsterParty.add(chosenMonster);
        }
    }

    public void printMonsterParty(){
        System.out.println();
        Map<Integer, List<String>> monsterPartyMap = new HashMap<>();
        for(int i=0; i<monsterParty.size(); i++){
            monsterPartyMap.put(i, monsterParty.get(i).toStringArray());
        }
        print.printSubList(monsterPartyMap, print.monsterHeader);
        System.out.println();

    }

    public Map<Integer, Map<Integer, List<String>>> getScaledMonsterMapping(HeroParty heroParty){
        Map<Integer, Map<Integer, List<String>>> scaledMonsterMapping = new HashMap<>();
        int maxLevel = heroParty.getMaxLevel();
        for(Integer monsterType : monsterMapping.keySet()){
            Map<Integer, List<String>> bufferMap = new HashMap<>();
            int count = 0;
            for (int i = 0; i < monsterMapping.get(monsterType).size(); i++) {
                if(Integer.parseInt(monsterMapping.get(monsterType).get(i).get(1)) <= maxLevel){
                    bufferMap.put(count, monsterMapping.get(monsterType).get(i));
                    count++;
                }
            }
            scaledMonsterMapping.put(monsterType, bufferMap);
        }
        return scaledMonsterMapping;
    }
}
