import java.util.ArrayList;
import java.util.List;

public class Battle {

    public Battle(HeroParty heroParty){
        MonsterParty monsterParty = new MonsterParty(heroParty, heroParty.getHeroPartySize());
        List<Hero> inBattleHeroParty = new ArrayList<>(heroParty.getHeroParty());;
        System.out.print("\nUh! Oh! The MONSTERS HAVE ARRIVED!!!!\nPREPARE FOR BATTLE");
        heroParty.printHeroParty();
        monsterParty.printMonsterParty();
        for (int i = heroParty.getHeroPartySize()-1; i>=0; i--) {
            int result = this.battleRound(heroParty.getHeroParty().get(i), monsterParty.getMonsterParty().get(i));
            switch(result){
                case 1:
                    monsterParty.getMonsterParty().remove(i);
                    break;
                case 2:
                    inBattleHeroParty.remove(i);
                    break;
            }

        }
        if(inBattleHeroParty.isEmpty()){
            System.out.println("All Heroes have been defeated");
            System.exit(0);
        } else if (monsterParty.getMonsterParty().isEmpty()) {
            System.out.println("Your heroes have won the battle");
            System.out.println("All your heroes are revived! Continue marching on! Continue Exploring!!");
        }
    }

    public int battleRound(Hero hero, Monster monster){
        System.out.println("Hero " + hero.getName() + " will fight" + " Monster "+monster.getName());
        System.out.println();
        while (!(hero.isFainted()) && !(monster.isFainted())) {
            hero.heroBattleMove(monster);
            if (!(monster.isFainted())) {
                monster.monsterAttack(hero);
            }
        }
        if (!(hero.isFainted())) {
            System.out.println("Hero " + hero.getName() + " defeated Monster " + monster.getName());
            return 1;
        } else {
            System.out.println("Monster " + monster.getName() + " defeated Hero " + hero.getName());
            return 2;
        }
    }
}
