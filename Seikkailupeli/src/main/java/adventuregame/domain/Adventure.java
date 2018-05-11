package adventuregame.domain;

import java.util.ArrayList;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

/**
 * Adventure-luokan tarkoitus on ylläpitää tietoa seikkailun tavoitteista ja
 * niiden toteutumisesta
 *
 * @author strajama
 */
public class Adventure {

    private World world;
    private Player player;
    private Item itemGoal;
    private Helper helperGoal;
    private int points;
//    private Random random;
    private UniformRandomProvider rng;
    private String lastAction;
    private Monster monster;

    /**
     * Metodi luo uuden Adventure-olion, asettaa aluksi 0 pistettä, luo uuden
     * Player-olion, asettaa viimeiseksi teoksi tyhjän ja luo kirjastosta haetun
     * satunnaisuuksia antavan olion.
     *
     * @param world - World-olio, jossa seikkailu tapahtuu
     */
    public Adventure(World world) {
        this.world = world;
        this.points = 0;
//        this.random = new Random();
        this.rng = RandomSource.create(RandomSource.MT);
        this.lastAction = "";
        this.monster = world.getMonster();
        createPlayer();
    }

    public World getWorld() {
        return world;
    }

    public Item getItemGoal() {
        return itemGoal;
    }

    public void setItemGoal(Item itemGoal) {
        this.itemGoal = itemGoal;
    }

    public Helper getHelperGoal() {
        return helperGoal;
    }

    public void setHelperGoal(Helper helperGoal) {
        this.helperGoal = helperGoal;
    }

    public int getPoints() {
        return points;
    }

    public Player getPlayer() {
        return player;
    }

    public String getLastAction() {
        return lastAction;
    }

    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }

    public Monster getMonster() {
        return monster;
    }

    public void givePoints(int points) {
        this.points += points;
    }

    public String printPoints() {
        return "Sinulla on pisteitä " + points + ".";
    }

    /**
     * Metodi arpoo uudet Item- ja Helper-tavoitteet pelille.
     */
    public void makeAGame() {
        this.randomItemGoal();
        this.randomHelperGoal();
    }

    /**
     * Metodi vähentää pisteitä yhdellä.
     */
    public void takeTurn() {
        points--;
    }

    /**
     * Metodi arpoo esineen, joka pelissä on tavoitteena löytää.
     */
    private void randomItemGoal() {
        ArrayList<Item> items = world.getItems();
        int r = rng.nextInt(items.size());
        this.itemGoal = items.get(r);
    }

    /**
     * Metodi arpoo apurin, jonka kanssa pelissä on tavoitteena puhua.
     */
    private void randomHelperGoal() {
        ArrayList<Helper> helpers = world.getHelpers();
        int r = rng.nextInt(helpers.size());
        this.helperGoal = helpers.get(r);
    }

    /**
     * Metodi luo uuden pelaajan ja sijoittaa hänen alueekseen kodin.
     */
    private void createPlayer() {
        this.player = new Player();
        player.setArea(world.getHome());
    }

}
