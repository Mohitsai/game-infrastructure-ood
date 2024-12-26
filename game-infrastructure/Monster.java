import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Monster extends Character{

    int defense;
    int damage;
    int dodgeChance;

    public void setAttackPossible(boolean attackPossible) {
        this.attackPossible = attackPossible;
    }

    private boolean attackPossible = false;

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(int dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public Monster(String type, String name, int level, int damage, int defense, int dodgeChance){
        super(name, level, type);
        this.damage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
    }

    public Monster(String type, String name, int level, int damage, int defense, int dodgeChance, int row, int col, Mark mark){
        super(name, level, type, mark, row, col);
        this.damage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
    }

    public void monsterAttack(Hero hero){
        Random random = new Random();
        if(random.heroDodge(hero.getAgility())){
            System.out.println(hero.getName() + " has dodged " + this.getName() + " attack!");
        }
        else {
            int damage=0;
            if (hero.armoryEquiped!=null){
                damage = this.getDamage()-hero.armoryEquiped.damageReduction;
                if (damage<0){
                    damage=0;
                }
            }else {
                damage=this.getDamage();
            }

            hero.setHP(hero.getHP() - damage);
            System.out.println(this.getName() + " has attacked " + hero.getName() + " for " + damage + " damage!");
            if (hero.getHP()<0){
                System.out.println(hero.getName()+" fainted!");
            }
        }
        System.out.println();
    }

    public boolean monsterAction(ValorWorld world){
        if(this.isAttackPossible(world)){
            this.monsterValorAttack(world);
        }else{
            return this.monsterMove(world);
        }
        return false;
    }

    public boolean isAttackPossible(ValorWorld world){
        int row = this.getRow();
        int col = this.getCol();
        System.out.println(this.name + " checked if they can make an attack");
        if (world.getValorWorld()[row][col].heroPresent||
                row<7&&world.getValorWorld()[row+1][col].heroPresent||
                row>0&&world.getValorWorld()[row-1][col].heroPresent||
                col<7&&world.getValorWorld()[row][col+1].heroPresent||
                col>0&&world.getValorWorld()[row][col-1].heroPresent){
            return true;
        }
        return false;
    }

    public void monsterValorAttack(ValorWorld world){
        Hero hero = getHeroToFight(world);
        System.out.println("Attack is possible. " + this.name + " is going to attack " + hero.getName());
        if (hero!=null){
            monsterAttack(hero);
        }
    }

    public Hero getHeroToFight(ValorWorld world){
        Hero hero=null;
        if (world.heroMapping.get(Arrays.asList(this.getRow(),this.getCol()))!=null){
            hero = world.heroMapping.get(Arrays.asList(this.getRow(),this.getCol()));
        }else if (world.heroMapping.get(Arrays.asList(this.getRow()-1,this.getCol()))!=null){
            hero = world.heroMapping.get(Arrays.asList(this.getRow()-1,this.getCol()));
        }else if (world.heroMapping.get(Arrays.asList(this.getRow()+1,this.getCol()))!=null){
            hero = world.heroMapping.get(Arrays.asList(this.getRow()+1,this.getCol()));
        }else if (world.heroMapping.get(Arrays.asList(this.getRow(),this.getCol()-1))!=null){
            hero = world.heroMapping.get(Arrays.asList(this.getRow(),this.getCol()-1));
        }else if (world.heroMapping.get(Arrays.asList(this.getRow(),this.getCol()+1))!=null){
            hero = world.heroMapping.get(Arrays.asList(this.getRow(),this.getCol()+1));
        }
        return hero;
    }

    public boolean monsterMove(ValorWorld world){
        int beforeMoveRow = this.getRow();
        int beforeMoveCol = this.getCol();
        if (beforeMoveRow+1==7){
            System.out.println("Monster "+this.name+" go into your Nexus! You lose! ");
            return true;
        }else {
            this.setRow(beforeMoveRow+1);
            world.getValorWorld()[this.getRow()][this.getCol()].setMonsterPresent(true);
            world.getValorWorld()[this.getRow()][this.getCol()].setRightMark(this.getBoardMark());
            world.getValorWorld()[this.getRow()][this.getCol()].setCellContents();
            world.monsterMapping.put(Arrays.asList(this.getRow(),this.getCol()),this);
            world.getValorWorld()[beforeMoveRow][beforeMoveCol].setMonsterPresent(false);
            world.getValorWorld()[beforeMoveRow][beforeMoveCol].setRightMark(new Mark("  "));
            world.getValorWorld()[beforeMoveRow][beforeMoveCol].setCellContents();
            world.monsterMapping.remove(Arrays.asList(beforeMoveRow,beforeMoveCol));
            System.out.println("Attack is not possible! They are going to make a move forward instead");
            System.out.println();
        }
        return false;
    }

    public List<String> toStringArray(){
        List<String> stringArray = new ArrayList<>();
        stringArray.add(getType());
        stringArray.add(getName());
        stringArray.add(String.valueOf(getHP()));
        stringArray.add(String.valueOf(getLevel()));
        stringArray.add(String.valueOf(getDefense()));
        stringArray.add(String.valueOf(getDamage()));
        stringArray.add(String.valueOf(getDodgeChance()));
        return stringArray;
    }
}
