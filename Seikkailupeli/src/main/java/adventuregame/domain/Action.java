package adventuregame.domain;

/**
 * Luokkaa käytetään pelin edistämiseen ja muutosten tekemiseen
 *
 * @author strajama
 */
public class Action {

    private Adventure adventure;
    private Player player;

    /**
     * Metodi luo uuden Action-olion
     *
     * @param adventure - seikkailu, jota pelataan
     */
    public Action(Adventure adventure) {
        this.adventure = adventure;
        this.player = adventure.getWorld().getPlayer();
    }

    /**
     * Metodi liikuttaa pelaajaa parametrina annettuun suuntaan. Liikkumisen
     * onnistuessa vähennetään yksi vuoro ja liikutetaan hirviötä.
     *
     * @param direction - suunta johon pelaajan halutaan liikkuvan
     * @return - tieto siitä, että onnistuiko liikkuminen
     */
    public boolean move(Direction direction) {
        Area now = player.getArea();
        if (now.getNeighbors().get(direction) != null) {
            Area next = now.getNeighbors().get(direction);
            player.setArea(next);
            adventure.takeTurn();
            moveMonster();
            return true;
        }
        return false;
    }

    /**
     * Metodin avulla pelaaja poimii esineen. Poimimisen onnistuessa vähennetään
     * yksi vuoro ja liikutetaan hirviötä.
     *
     * @return - Esine, joka poimittiin tai null
     */
    public Item take() {
        if (thereIsFinding()) {
            Item item = (Item) player.getArea().giveSomeItem();
            if (item != null) {
                player.putInBag(item);
                adventure.takeTurn();
                moveMonster();
                return item;
            }
        }
        return null;
    }

    /**
     * Metodia käytetään apurien kanssa puhumiseen. Puhumisen onnistuessa
     * vähennetään yksi vuoro ja liikutetaan hirviötä.
     *
     * @return - apuri, jonka kanssa puhuttiin tai null
     */
    public Helper speak() {
        if (thereIsFinding()) {
            Helper helper = player.getArea().speakWithNewHelper(player);
            if (helper != null) {
                player.speakWith(helper);
                adventure.takeTurn();
                moveMonster();
                return helper;
            }
        }
        return null;
    }
    
    public Item give() {
        if (thereIsFinding()) {
            Helper helper = player.getArea().findHelper();
            if (helper != null) {
                if (player.getItems().containsKey(helper.getItem().getName())) {
                    player.removeFromBag(helper.getItem());
                    player.getArea().removeHelper(helper);
                    return helper.getItem();
                }
            }
        }
        return null;
    }
/**
 * Metodi, joka liikuttaa hirviötä, jos pelaaja ei ole samassa ruudussa
 */
    private void moveMonster() {
        if (adventure.getWorld().getMonster().getArea() != adventure.getWorld().getPlayer().getArea()) {
            Area next = adventure.getWorld().getMonster().getArea().randomNeighbor();
            adventure.getWorld().getMonster().getArea().removeMonster();
            adventure.getWorld().getMonster().setArea(next);
            next.putMonster(adventure.getWorld().getMonster());
        }
    }
    
    private boolean thereIsFinding() {
        return !adventure.getWorld().getPlayer().getArea().getFindings().isEmpty();
    }
    
    
}
