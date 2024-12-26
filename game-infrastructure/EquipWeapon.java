import java.util.List;

public class EquipWeapon implements Attack{

    @Override
    public void makeBattleMove(Hero hero, Monster monster) {
        if(!(hero.getHeroInventory().containsKey(1))){
            System.out.println("You have no weapon in your inventory to equip!");
        }else {
            Weaponry weaponry = hero.retrieveWeapon();
            List<Weaponry> weaponriesEquiped =  hero.getWeaponriesEquiped();
            int hands=0;
            for (Weaponry equiped: weaponriesEquiped){
                hands+=equiped.requiredHands;
            }
            int requireHands = weaponry.requiredHands;
            if ((hands+requireHands)>2){
                Weaponry removedWeapon = weaponriesEquiped.remove(0);
                System.out.println("Hero:"+hero.name+" remove the weapon "+removedWeapon.name);
            }
            weaponriesEquiped.add(weaponry);
            System.out.println("Hero:"+hero.name+" equip the weapon "+weaponry.name);
        }
    }
}
