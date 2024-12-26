public class EquipArmor implements Attack{

    @Override
    public void makeBattleMove(Hero hero, Monster monster) {
        if(!(hero.getHeroInventory().containsKey(0))){
            System.out.println("You have no armor in your inventory to equip!");
        }else {
            Armory armory = hero.retrieveArmor();
            hero.setArmoryEquiped(armory);
            System.out.println("Hero:"+hero.name+" equip the armor "+armory.name);
        }
    }
}
