import java.util.ArrayList;
import java.util.List;

public class Armory extends Equipment{

    public int getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(int damageReduction) {
        this.damageReduction = damageReduction;
    }

    int damageReduction;

    public Armory(String type, String name, int cost, int requiredLevel, int damageReduction){
        super(type, name, cost, requiredLevel);
        this.damageReduction = damageReduction;
    }

    public List<String> toStringArray(){
        List<String> stringArray = new ArrayList<>();
        stringArray.add(getName());
        stringArray.add(String.valueOf(getCost()));
        stringArray.add(String.valueOf(getLevelRequired()));
        stringArray.add(String.valueOf(getDamageReduction()));
        return stringArray;
    }

}
