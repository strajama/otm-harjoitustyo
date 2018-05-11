package adventuregame.domain;

import adventuregame.ui.SeikkailuFXMain;
import adventuregame.ui.UI;
import java.util.Iterator;
import javafx.scene.control.Label;

/**
 * Luokkaa käytetään pelin edistämiseen ja muutosten tekemiseen
 *
 * @author strajama
 */
public class Action {

    private Adventure adventure;
    private UI s;

    /**
     * Metodi luo uuden Action-olion
     *
     * @param adventure - seikkailu, jota pelataan
     * @param s - käyttöliittymä, jonka tekstejä päivitetään
     */
    public Action(Adventure adventure, UI s) {
        this.adventure = adventure;
        this.s = s;
    }

    /**
     * Metodi liikuttaa pelaajaa parametrina annettuun suuntaan. Liikkumisen
     * onnistuessa tehdään yksi vuoro ja liikutetaan hirviötä.
     *
     * @param direction - suunta johon pelaajan halutaan liikkuvan
     * @return - tieto siitä, että onnistuiko liikkuminen
     */
    public void move(Direction direction) {
        Area now = adventure.getPlayer().getArea();
        if (now.getNeighbors().get(direction) != null) {
            adventure.getPlayer().setArea(now.getNeighbors().get(direction));
            adventure.takeTurn();
            updateLastAction("Matkustit " + giveFinnish(direction));
            moveMonster();
            actionShow();
            return;
        }
        updateLastAction("Et voi mennä " + giveFinnish(direction));
        actionShow();
        return;
    }

    /**
     * Metodin avulla pelaaja poimii esineen. Poimimisen onnistuessa tehdään
     * yksi vuoro.
     *
     */
    public void take() {
        if (thereIsFinding()) {
            Item item = (Item) adventure.getPlayer().getArea().giveSomeItem();
            if (item != null) {
                adventure.getPlayer().putInBag(item);
                adventure.takeTurn();
                if (item.equals(adventure.getItemGoal())) {
                    adventure.givePoints(10);
                    updateLastAction("Poimit tavoite-esineen " + item.getName().toUpperCase());
                } else {
                    adventure.givePoints(5);
                    updateLastAction("Poimit esineen " + item.getName().toUpperCase() + ".");
                }
                actionShow();
                return;
            }
        }
        updateLastAction("Täällä ei ole mitään poimittavaa.");
        actionShow();
    }

    /**
     * Metodia käytetään apurien kanssa puhumiseen. Puhumisen onnistuessa
     * tehdään yksi vuoro.
     */
    public void speak() {
        if (thereIsFinding()) {
            Helper helper = adventure.getPlayer().getArea().speakWithNewHelper(adventure.getPlayer());
            if (helper != null) {
                adventure.getPlayer().speakWith(helper);
                adventure.takeTurn();
                if (helper.equals(adventure.getHelperGoal())) {
                    adventure.givePoints(10);
                    updateLastAction("Etsimäsi " + helper.getName().toUpperCase() + " kaipaa: " + helper.getItem().getDescription() + ".");
                } else {
                    adventure.givePoints(5);
                    updateLastAction(helper.getName().toUpperCase() + " kaipaa: " + helper.getItem().getDescription() + ".");
                }
                actionShow();
                return;
            }
        }
        updateLastAction("Täällä ei ole uusia keskusteluja käytävänä.");
        actionShow();
    }

    /**
     * Metodia käytetään, kun annetaan apurille esine. Antaminen onnistuu, kun
     * alueella on apuri ja pelaajalla on repussa esine, jota apuri kaipaa.
     * Onnistuessa tehdään yksi vuoro, esine poistuu repusta ja apuri alueelta.
     */
    public void give() {
        if (thereIsFinding()) {
            Helper helper = adventure.getPlayer().getArea().findHelper();
            if (helper != null) {
                if (adventure.getPlayer().getItems().containsKey(helper.getItem().getName())) {
                    adventure.getPlayer().removeFromBag(helper.getItem());
                    adventure.getPlayer().getArea().removeHelper(helper);
                    adventure.takeTurn();
                    adventure.givePoints(10);
                    updateLastAction("Reppusi on kevyempi, kun sieltä puuttuu " + helper.getItem().getName() + ".");
                    actionShow();
                    return;
                }
            }
        }
        updateLastAction("Sinulla ei ole mitään sopivaa annettavaa.");
        actionShow();
    }

    /**
     * Metodi lyö hirviötä, jos alueella on sellainen ja tekee silloin yhden
     * vuoron. Jos hirviö kuolee, se poistetaan alueelta.
     */
    public void hit() {
        if (monsterIsHere() && !adventure.getMonster().isDead()) {
            if (adventure.getPlayer().getItems().isEmpty()) {
                adventure.getMonster().hitMonster(1);
                updateLastAction("Lyöt hirviötä " + adventure.getMonster().getName() + ", joka ottaa osuman.");
            } else {
                adventure.getMonster().hitMonster(2);
                updateLastAction("Lyöt hirviötä " + adventure.getMonster().getName() + ", joka ottaa kovan osuman.");

            }
            if (adventure.getMonster().isDead()) {
                adventure.givePoints(20);
                updateLastAction(adventure.getMonster().getName().toUpperCase() + " on kukistettu.");
            }

            adventure.takeTurn();
            actionShow();
            return;
        }
        updateLastAction("Huidot ilmaa niin, että sinulle tulee hiki.");
        actionShow();
    }

    /**
     * Metodi, joka liikuttaa hirviötä, jos pelaaja ei ole samassa ruudussa
     */
    private void moveMonster() {
        if (!adventure.getMonster().isDead()) {
            if (!monsterIsHere()) {
                Area next = adventure.getMonster().getArea().randomNeighbor();
                adventure.getMonster().setArea(next);
            }
        }
    }

    /**
     * Metodi on apumetodi, joka kertoo onko alueella, jolla pelaaja on, jotain
     * löydettävää
     *
     * @return true tai false
     */
    private boolean thereIsFinding() {
        return !adventure.getPlayer().getArea().getFindings().isEmpty();
    }

    /**
     * Metodi kertoo ovatko pelaaja ja hirviö samalla alueella
     *
     * @return true tai false
     */
    private boolean monsterIsHere() {
        return adventure.getPlayer().getArea().equals(adventure.getMonster().getArea());
    }

    /**
     * Metodi on apumetodi, joka auttaa suomenkielisessä toteutuksessa
     *
     * @param direction - suunta, joka annetaan
     * @return String-olio, joka on suunnan nimi suomeksi
     */
    private String giveFinnish(Direction direction) {
        if (direction.equals(Direction.NORTH)) {
            return "pohjoiseen.";
        } else if (direction.equals(Direction.EAST)) {
            return "itään.";
        } else if (direction.equals(Direction.SOUTH)) {
            return "etelään.";
        }
        return "länteen.";
    }

    /**
     * Metodia käytetään käyttöliittymän playscenen tekstien päivittämiseen.
     * Testaukseen tehtyä käyttöliittymää ei päivitetä
     *
     * @param s - käyttöliittymä, joka annetaan parametrina
     */
    private void actionShow() {
        if (!s.isTestUI()) {
            s.getAreaLabel().setText(adventure.getPlayer().getArea().getName().toUpperCase());
            s.getDescriptionLabel().setText(adventure.getPlayer().getArea().getDescription());
            s.getFindingLabel().setText(adventure.getPlayer().getArea().show());
            s.getBagLabel().setText(adventure.getPlayer().bag());
            s.getMonsterLabel().setText(showMonster());
            s.getPointsLabel().setText(adventure.printPoints());
            s.getDoingLabel().setText(adventure.getLastAction());
            s.getPlayCenter().getChildren().clear();
            s.getPlayCenter().getChildren().add(s.getBagLabel());
            Iterator<String> it = adventure.getPlayer().getItems().keySet().iterator();
            while (it.hasNext()) {
                s.getPlayCenter().getChildren().add(new Label(it.next().toUpperCase()));
            }
        }
    }

    /**
     * Metodi kertoo käyttäjälle onko alueella hirviötä. Tätä käytetään
     * käyttöliittymän päivittämisessä.
     *
     * @return - String (kuvailu hirviöistä)
     */
    private String showMonster() {
        if (!monsterIsHere() || adventure.getMonster().isDead()) {
            return "";
        }
        return "Edessäsi on hirvittävä " + adventure.getMonster().getName().toUpperCase() + ". Se sanoo: '" + adventure.getMonster().getSlogan() + "'.";
    }

    /**
     * Metodi päivittää seikkailun viimeisintä näkymää. Testaukseen tehtyä
     * käyttöliittymää ei päivitetä
     *
     * @param r
     */
    private void updateLastAction(String r) {
        if (!s.isTestUI()) {
            adventure.setLastAction(r);
        }
    }
}
