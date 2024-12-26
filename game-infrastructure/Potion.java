import java.util.ArrayList;
import java.util.List;

public class Potion extends Consumable{

    int attributeIncrease;

    String attributeAffected;

    public Potion(String type, String name, int cost, int requiredLevel, int attributeIncrease, String attributeAffected, int remainingUses){
        super(type, name, cost, requiredLevel, remainingUses);
        this.attributeAffected = attributeAffected;
        this.attributeIncrease = attributeIncrease;
    }

    public List<String> toStringArray(){
        List<String> stringArray = new ArrayList<>();
        stringArray.add(getName());
        stringArray.add(String.valueOf(getCost()));
        stringArray.add(String.valueOf(getLevelRequired()));
        stringArray.add(String.valueOf(getAttributeIncrease()));
        stringArray.add(getAttributeAffected());
        stringArray.add(String.valueOf(getRemainingUses()));
        return stringArray;
    }

    public int getAttributeIncrease() {
        return attributeIncrease;
    }

    public void setAttributeIncrease(int attributeIncrease) {
        this.attributeIncrease = attributeIncrease;
    }

    public String getAttributeAffected() {
        return attributeAffected;
    }

    public void setAttributeAffected(String attributeAffected) {
        this.attributeAffected = attributeAffected;
    }
}
