package seikkailupeli.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * Adventure-luokan tarkoitus on ylläpitää tietoa seikkailun tavoitteista ja niiden
 * toteutumisesta
 * @author strajama
 */
public class Adventure {

    private World world;
    private Item itemGoal;
    private Helper helperGoal;
    private int timeGoal;
    private Random random;
    private boolean over;
/**
 * Metodi luo uuden Adventure-olion
 * 
 * @param world - maailma, jossa seikkailu tapahtuu
 */
    public Adventure(World world) {
        this.world = world;
        this.random = new Random();
        this.over = false;
    }
    
    public World getWorld() {
        return world;
    }

    public boolean getOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Item getItemGoal() {
        return itemGoal;
    }

    public void setItemGoal(Item item) {
        this.itemGoal = item;
    }

    public int getTimeGoal() {
        return timeGoal;
    }

    public void setTimeGoal(int t) {
        this.timeGoal = t;
    }

    public Helper getHelperGoal() {
        return helperGoal;
    }

    public void setHelperGoal(Helper helperGoal) {
        this.helperGoal = helperGoal;
    }

    /**
     * Metodi aloittaa uuden pelin, jolla on arvottu esine- ja apuri-tavoitteet
     * ja peliaika, joka annetaan parametrina
     *
     * @param time pelivuorojen määrä
     */
    public void makeAGame(int time) {
        this.randomItemGoal();
        this.randomHelperGoal();
        this.setTimeGoal(time);
    }

    /**
     * Arpoo esineen, joka pelissä on tavoitteena löytää
     */
    private void randomItemGoal() {
        if (!world.getItems().isEmpty()) {
            ArrayList<Item> items = world.getItems();
            int r = random.nextInt(items.size());
            this.itemGoal = items.get(r);
        }
    }

    /**
     * Metodi arpoo apurin, jonka kanssa pelissä on tavoitteena puhua
     */
    private void randomHelperGoal() {
        if (!world.getHelpers().isEmpty()) {
            ArrayList<Helper> helpers = world.getHelpers();
            int r = random.nextInt(helpers.size());
            this.helperGoal = helpers.get(r);
        }
    }

    /**
     * Metodi vähentää tavoiteaikaa yhdellä
     */
    public void takeTurn() {
        if (!over) {
            timeGoal--;
            if (timeGoal <= 0) {
                over = true;
            }
        }
    }

}
