public class Spell extends Consumable{

    int damage;
    int manaCost;
    public Spell(String type, String name, int cost, int requiredLevel, int remainingUses){
        super(type, name, cost, requiredLevel, remainingUses);
    }
}
