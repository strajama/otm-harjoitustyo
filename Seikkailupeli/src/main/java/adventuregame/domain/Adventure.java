package adventuregame.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * Adventure-luokan tarkoitus on ylläpitää tietoa seikkailun tavoitteista ja
 * niiden toteutumisesta
 *
 * @author strajama
 */
public class Adventure {

    private World world;
    private Item itemGoal;
    private Helper helperGoal;
    private int points;
    private Random random;

    /**
     * Metodi luo uuden Adventure-olion
     *
     * @param world - maailma, jossa seikkailu tapahtuu
     */
    public Adventure(World world) {
        this.world = world;
        this.points = 0;
        this.random = new Random();
    }

    public World getWorld() {
        return world;
    }

    public Item getItemGoal() {
        return itemGoal;
    }

    public Helper getHelperGoal() {
        return helperGoal;
    }

    public int getPoints() {
        return points;
    }

    public void givePoints(int points) {
        this.points += points;
    }

    /**
     * Metodi aloittaa uuden pelin, jolla on arvottu esine- ja apuri-tavoitteet
     * ja peliaika, joka annetaan parametrina
     *
     * @param time pelivuorojen määrä
     */
    public void makeAGame() {
        this.randomItemGoal();
        this.randomHelperGoal();
    }

    /**
     * Metodi vähentää pisteitä yhdellä
     */
    public void takeTurn() {
        points--;
    }

    /**
     * Arpoo esineen, joka pelissä on tavoitteena löytää
     */
    private void randomItemGoal() {
        ArrayList<Item> items = world.getItems();
        int r = random.nextInt(items.size());
        this.itemGoal = items.get(r);
    }

    /**
     * Metodi arpoo apurin, jonka kanssa pelissä on tavoitteena puhua
     */
    private void randomHelperGoal() {
        ArrayList<Helper> helpers = world.getHelpers();
        int r = random.nextInt(helpers.size());
        this.helperGoal = helpers.get(r);
    }
}