import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Legends implements Game, RolePlayingGame {

    Scanner sc;

    int selection;

    int heroPartySize;

    Market market;

    int worldSize;

    LegendsWorld world;

    HeroParty heroParty;

    Player player;

    public Legends(){
        player = new Player(5, "Hero", 1);
        this.sc = new Scanner(System.in);
        int confirmation = new Menu().confirm();
        if (confirmation == 1) {
            System.out.println("Let's Play Legends : Monsters and Heroes!!");
        } else if (confirmation == 2) {
            System.out.println("Sorry to see you leave");
            System.exit(0);
        }
        initializeCharacters();
        initializeWorld();
        world.placePlayer(player);
    }

    @Override
    public void initializeCharacters(){
        heroPartySize = new Menu().getHeroPartySize();
        heroParty = new HeroParty(heroPartySize);
    }

    @Override
    public void initializeWorld(){
        worldSize = new Menu().getLegendsWorldSize();
        world = new LegendsWorld(worldSize);
        while(true){
            selection = new Menu().refreshBoard();
            if(selection == 1){
                world = new LegendsWorld(worldSize);
            }else{
                break;
            }
        }
    }

    @Override
    public void play(){
        System.out.println("Legends Start");
        while(true){
            world.validateHeroPartyMove(player, heroParty);
        }
    }

    @Override
    public void afterGame(){
        System.out.println("Legends afterGame");

    }
}
