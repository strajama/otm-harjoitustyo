package adventuregame.domain;

import adventuregame.ui.SeikkailuFXMain;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.control.Label;
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

    /**
     * Metodia käytetään käyttöliittymän playscenen tekstien päivittämiseen
     * @param s - käyttöliittymä, joka annetaan parametrina
     */
    public void actionShow(SeikkailuFXMain s) {

        s.getAreaLabel().setText(player.getArea().getName().toUpperCase());
        s.getDescriptionLabel().setText(player.getArea().getDescription());
        s.getFindingLabel().setText(player.getArea().show());
        s.getBagLabel().setText(player.bag());
        s.getMonsterLabel().setText(player.getArea().showMonster());
        s.getPointsLabel().setText(this.printPoints());
        s.getDoingLabel().setText(this.getLastAction());

        s.getPlayCenter().getChildren().clear();
        s.getPlayCenter().getChildren().add(s.getBagLabel());
        Iterator<String> it = player.getItems().keySet().iterator();
        while (it.hasNext()) {
            s.getPlayCenter().getChildren().add(new Label(it.next().toUpperCase()));
        }
    }
}
