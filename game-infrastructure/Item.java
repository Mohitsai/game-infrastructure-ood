import java.util.ArrayList;
import java.util.List;

public class Item {

    public String name;

    public int cost;

    public int levelRequired;

    public int getItemAvailability() {
        return itemAvailability;
    }

    public void setItemAvailability(int itemAvailability) {
        this.itemAvailability = itemAvailability;
    }

    int itemAvailability;

    public String getType() {
        return type;
    }

    String type;

    public Item(String type, String name, int cost, int levelRequired) {
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.levelRequired = levelRequired;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

//    public List<String> toStringArray(){
//        List<String> stringArray = new ArrayList<>();
//        stringArray.add(getType());
//        stringArray.add(getName());
//        stringArray.add(String.valueOf(getCost()));
//        stringArray.add(String.valueOf(getLevelRequired()));
//        stringArray.add(String.valueOf(getDamage()));
//        stringArray.add(String.valueOf(getManaCost()));
//        stringArray.add(String.valueOf(getRemainingUses()));
//        return stringArray;
//    }

}
