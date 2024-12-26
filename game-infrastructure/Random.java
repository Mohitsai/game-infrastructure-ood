import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Random {

    public char setWorldCellType(){
        int randomInt = ThreadLocalRandom.current().nextInt(0, 10);
        char worldCellType = ' ';
        if(randomInt == 0 || randomInt == 1){
            worldCellType = 'X';
        } else if (randomInt>=2 && randomInt<=4) {
            worldCellType = 'M';
        }
        return worldCellType;
    }

    public List<Integer> randomList(int size){
        List<Integer> returnList = new ArrayList<>();
        for(int i=0; i<size; i++){
            int randomInt = ThreadLocalRandom.current().nextInt(0, 10);
            if (randomInt>=0 && randomInt<=5) {
                returnList.add(i);
            }
        }
        return returnList;
    }

    public boolean haveBattle(Player player) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 10);
        if (player.isHasVisitedMarket()) {
            return randomNum < 7;
        } else {
            return randomNum < 4;
        }
    }

    public boolean heroDodge(int agility){
        double probability = (agility * 0.2)/(1 + (agility * 0.2));
        double randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        return randomNum / 100.0 <= probability;
    }

    public boolean monsterDodge(int dodge){
        double probability = (dodge * 0.01);
        double randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        if(randomNum/100.0 <= probability) return true;
        return false;
    }

    public char setValorWorldCellType(){
        int randomInt = ThreadLocalRandom.current().nextInt(0, 37);
        char worldCellType = ' ';
        if(randomInt>=0 && randomInt<=6){
            worldCellType = 'P';
        } else if (randomInt>=7 && randomInt<=12) {
            worldCellType = 'B';
        } else if(randomInt>=13 && randomInt<=18){
            worldCellType = 'P';
        } else if(randomInt>=19 && randomInt<=24){
            worldCellType = 'K';
        } else if(randomInt>=25 && randomInt<= 30){
            worldCellType = 'P';
        } else if(randomInt>= 31 && randomInt<= 36){
            worldCellType = 'C';
        }
        return worldCellType;
    }
}
