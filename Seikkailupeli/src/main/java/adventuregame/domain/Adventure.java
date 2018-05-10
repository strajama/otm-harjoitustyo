package adventuregame.domain;

import java.util.ArrayList;
import java.util.Random;
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
    private Item itemGoal;
    private Helper helperGoal;
    private int points;
//    private Random random;
    private UniformRandomProvider rng;

    /**
     * Metodi luo uuden Adventure-olion ja asettaa aluksi 0 pistettä.
     *
     * @param world - World-olio, jossa seikkailu tapahtuu
     */
    public Adventure(World world) {
        this.world = world;
        this.points = 0;
//        this.random = new Random();
        this.rng = RandomSource.create(RandomSource.MT);
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

    public void givePoints(int points) {
        this.points += points;
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
}
