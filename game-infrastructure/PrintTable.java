import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PrintTable {

    List<String> initialHeroHeader = Arrays.asList("Name", "Mana", "Strength", "Agility", "Dexterity", "Money", "Experience");
    List<String> gameHeroHeader = Arrays.asList("Type", "Name", "HP", "Level", "Mana", "Strength", "Agility", "Dexterity", "Money", "Experience");
    List<String> armoryHeader = Arrays.asList("Name", "Cost", "Required Level", "Damage Reduction", "Availability");

    List<String> weaponryHeader = Arrays.asList("Name","Cost","Level","Damage","Required Hands", "Availability");

    List<String> potionHeader = Arrays.asList("Name", "Cost", "Required Level", "Attributed Increase", "Attribute Affected", "Lifetime", "Availability");

    List<String> spellHeader = Arrays.asList("Name", "Cost", "Required Level", "Damage", "Mana Cost", "Lifetime", "Availability");

    List<String> monsterHeader = Arrays.asList("Type", "Name", "HP", "Level",  "Damage", "Defense", "Dodge Chance");

    public <T> void printSubList(Map<Integer, List<T>> subList, List<String> tableHeader){
        int maxWidth = 0;
        int[] columnWidths = new int[tableHeader.size()];
        for (int i=0; i < tableHeader.size(); i++) {
            for(int j=0; j< subList.size(); j++){
                maxWidth = Math.max(maxWidth, String.valueOf(subList.get(j).get(i)).length());
            }
            columnWidths[i] = Math.max(maxWidth, tableHeader.get(i).length())+2;
            maxWidth = 0;
        }

        StringBuilder border = new StringBuilder("+---------+");
        for (int i = 0; i < tableHeader.size(); i++) {
            border.append("-".repeat(Math.max(0, columnWidths[i])));
            border.append("+");
        }

        System.out.println(border);

        System.out.print("|Sl. No.  ");

        for (int i = 0; i < tableHeader.size(); i++) {
            System.out.printf("%-" + (columnWidths[i]+1) + "s", "|"+tableHeader.get(i));
        }
        System.out.println("|");

        for (Integer key : subList.keySet()) {
            System.out.println(border);
            System.out.printf("%-9s", "|"+key);
            for (int i = 0; i < tableHeader.size(); i++) {
                System.out.printf("%-" + (columnWidths[i]+1) + "s", " | "+subList.get(key).get(i));
            }
            System.out.println(" |");
        }
        System.out.println(border);
    }
}
