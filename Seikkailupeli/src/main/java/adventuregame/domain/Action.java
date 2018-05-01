package seikkailupeli.domain;

/**
 * Luokkaa käytetään pelin edistämiseen ja muutosten tekemiseen
 *
 * @author strajama
 */
public class Action {

    private Adventure adventure;

    /**
     * Metodi luo uuden Action-olion
     *
     * @param adventure - seikkailu, jota pelataan
     */
    public Action(Adventure adventure) {
        this.adventure = adventure;
    }

    /**
     * Metodi liikuttaa pelaajaa parametrina annettuun suuntaan. Liikkumisen
     * onnistuessa vähennetään yksi vuoro ja liikutetaan hirviötä.
     *
     * @param direction - suunta johon pelaajan halutaan liikkuvan
     * @return - tieto siitä, että onnistuiko liikkuminen
     */
    public boolean move(Direction direction) {
        Area now = adventure.getWorld().getPlayer().getArea();
        if (now.getNeighbors().get(direction) != null) {
            Area next = now.getNeighbors().get(direction);
            adventure.getWorld().getPlayer().setArea(next);
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
        if (!adventure.getWorld().getPlayer().getArea().getFindings().isEmpty()) {
            Item item = (Item) adventure.getWorld().getPlayer().getArea().giveSomeItem();
            if (item != null) {
                adventure.getWorld().getPlayer().putInBag(item);
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
        if (!adventure.getWorld().getPlayer().getArea().getFindings().isEmpty()) {
            Helper helper = adventure.getWorld().getPlayer().getArea().speakHelper(adventure.getWorld().getPlayer());
            if (helper != null) {
                adventure.getWorld().getPlayer().speakWith(helper);
                adventure.takeTurn();
                moveMonster();
                return helper;
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
}
