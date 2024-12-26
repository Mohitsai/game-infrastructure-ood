import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FromFile {

    TextReader reader = new TextReader();

    public Map<Integer, Map<Integer, List<String>>> allHeroes;
    public Map<Integer, Map<Integer, List<String>>> allMonsters;
    public Map<Integer, List<String>> warriors;
    public Map<Integer, List<String>> sorcerers;
    public Map<Integer, List<String>> paladins;
    public Map<Integer, List<String>> dragons;
    public Map<Integer, List<String>> exoskeletons;
    public Map<Integer, List<String>> spirits;
    public Map<Integer, List<String>> potions;
    public Map<Integer, List<String>> weaponry;
    public Map<Integer, List<String>> armory;
    public Map<Integer, List<String>> iceSpells;
    public Map<Integer, List<String>> fireSpells;
    public Map<Integer, List<String>> lightningSpells;

    public Map<Integer, Map<Integer, List<String>>> getHeroDetails(){

        allHeroes = new HashMap<>();
        warriors = new HashMap<>();
        sorcerers = new HashMap<>();
        paladins = new HashMap<>();

        reader.readFile(warriors, "./Warriors.txt");
        allHeroes.put(0, warriors);

        reader.readFile(sorcerers, "./Sorcerers.txt");
        allHeroes.put(1, sorcerers);

        reader.readFile(paladins, "./Paladins.txt");
        allHeroes.put(2, paladins);

        return allHeroes;
    }

    public Map<Integer, Map<Integer, List<String>>> getMonsterDetails(){

        allMonsters = new HashMap<>();
        dragons = new HashMap<>();
        exoskeletons = new HashMap<>();
        spirits = new HashMap<>();

        reader.readFile(dragons, "./Dragons.txt");
        allMonsters.put(0, dragons);

        reader.readFile(exoskeletons, "./Exoskeletons.txt");
        allMonsters.put(1, exoskeletons);

        reader.readFile(spirits, "./Spirits.txt");
        allMonsters.put(2, spirits);

        return allMonsters;
    }

    public Map<Integer, List<String>> getDragonDetails(){

        dragons = new HashMap<>();

        reader.readFile(dragons, "./Dragons.txt");

        return dragons;
    }

    public Map<Integer, List<String>> getSpiritDetails(){

        spirits = new HashMap<>();

        reader.readFile(spirits, "./Spirits.txt");

        return spirits;
    }

    public Map<Integer, List<String>> getExoskeletonDetails(){

        exoskeletons = new HashMap<>();

        reader.readFile(exoskeletons, "./Exoskeletons.txt");

        return exoskeletons;
    }


    public Map<Integer, List<String>> getIceSpellDetails(){

        iceSpells = new HashMap<>();

        reader.readFile(iceSpells, "./IceSpells.txt");

        return iceSpells;
    }

    public Map<Integer, List<String>> getFireSpellDetails(){

        fireSpells = new HashMap<>();

        reader.readFile(fireSpells, "./FireSpells.txt");

        return fireSpells;
    }

    public Map<Integer, List<String>> getLightningSpellDetails(){

        lightningSpells = new HashMap<>();

        reader.readFile(lightningSpells, "./LightningSpells.txt");

        return lightningSpells;
    }

    public Map<Integer, List<String>> getPotionDetails(){

        potions = new HashMap<>();

        reader.readFile(potions, "./Potions.txt");

        return potions;
    }

    public Map<Integer, List<String>> getWeaponryDetails(){

        weaponry = new HashMap<>();

        reader.readFile(weaponry, "./Weaponry.txt");

        return weaponry;
    }

    public Map<Integer, List<String>> getArmoryDetails(){

        armory = new HashMap<>();

        reader.readFile(armory, "./Armory.txt");

        return armory;
    }


}
