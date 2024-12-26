public class Consumable extends Item {

    int remainingUses;

    public Consumable(String type, String name, int cost, int requiredLevel, int remainingUses){
        super(type, name, cost, requiredLevel);
        this.remainingUses = remainingUses;
    }

    public int getRemainingUses() {
        return remainingUses;
    }

    public void setRemainingUses(int remainingUses) {
        this.remainingUses = remainingUses;
    }

    public void reduceRemainingUses(int remainingUses){
        this.remainingUses = remainingUses-1;
    }
}
