import java.util.ArrayList;
import java.util.List;

public class LightningSpell extends Spell{

    //int damage;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    //int manaCost;
    public LightningSpell(String type, String name, int cost, int requiredLevel, int damage, int manaCost, int remainingUses){
        super(type, name, cost, requiredLevel, remainingUses);
        this.damage = damage;
        this.manaCost = manaCost;
    }

    public List<String> toStringArray(){
        List<String> stringArray = new ArrayList<>();
        stringArray.add(getName());
        stringArray.add(String.valueOf(getCost()));
        stringArray.add(String.valueOf(getLevelRequired()));
        stringArray.add(String.valueOf(getDamage()));
        stringArray.add(String.valueOf(getManaCost()));
        stringArray.add(String.valueOf(getRemainingUses()));
        return stringArray;
    }
}
