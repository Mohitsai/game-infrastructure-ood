import java.util.ArrayList;
import java.util.List;

public class Weaponry extends Equipment{

    public int getRequiredHands() {
        return requiredHands;
    }

    public void setRequiredHands(int requiredHands) {
        this.requiredHands = requiredHands;
    }

    int requiredHands;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    int damage;

    public Weaponry(String type, String name, int cost, int requiredLevel, int damage, int requiredHands){
        super(type, name, cost, requiredLevel);
        this.requiredHands = requiredHands;
        this.damage = damage;
    }

    public List<String> toStringArray(){
        List<String> stringArray = new ArrayList<>();
        stringArray.add(getName());
        stringArray.add(String.valueOf(getCost()));
        stringArray.add(String.valueOf(getLevelRequired()));
        stringArray.add(String.valueOf(getDamage()));
        stringArray.add(String.valueOf(getRequiredHands()));
        return stringArray;
    }
}
