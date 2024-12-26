import java.util.Scanner;

public class ConsumePotion {

    public void makeBattleMove(Hero hero){
        if(!(hero.getHeroInventory().containsKey(2))){
            System.out.println("You have no potions in your inventory to consume!");
        }else{
            Potion potion =  hero.retrievePotion();
            switch (potion.attributeAffected) {
                case "Health":
                    hero.setHP(hero.getHP() + potion.getAttributeIncrease());
                    break;
                case "Strength":
                    hero.setStrength(hero.getStrength() + potion.getAttributeIncrease());
                    break;
                case "Mana":
                    hero.setMana(hero.getMana() + potion.getAttributeIncrease());
                    break;
                case "Agility":
                    hero.setAgility(hero.getAgility() + potion.getAttributeIncrease());
                    break;
                case "Dexterity":
                    hero.setDexterity(hero.getDexterity() + potion.getAttributeIncrease());
                    break;
            }
            System.out.println("Potion successfully consumed.");
            System.out.println(potion.getAttributeAffected() + " has increased by "+ potion.getAttributeIncrease());
            hero.removeFromInventory(2, potion);
        }
    }
}
