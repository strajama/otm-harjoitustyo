package adventuregame.domain;

/**
 * Helper-luokka on tarkoitettu pelaajan tapaamien apureiden ylläpitämiseen ja
 * se toteuttaa abstraktin luokan Finding
 *
 * @author strajama
 */
public class Helper extends Finding {

    private Item item;
/**
 * Metodi luo uuden Helper-olion ja asettaa sen tavoite-esineeksi null
 * @param name - apurin nimi
 * @param description - apurin kuvailu
 */
    public Helper(String name, String description) {
        super(name, description);
        this.item = null;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
/**
 * Metodi kertoo, että Helper ei ole Item
 * @return false
 */
    @Override
    public boolean isItem() {
        return false;
    }
}
