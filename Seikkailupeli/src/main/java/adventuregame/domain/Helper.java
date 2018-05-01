package adventuregame.domain;

/**
 * Luokka Helper on tarkoitettu pelaajan tapaamien apureiden ylläpitämiseen ja
 * toteuttaa abstraktin luokan Finding
 *
 * @author strajama
 */
public class Helper extends Finding {

    private Item item;

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

    @Override
    public boolean isItem() {
        return false;
    }
}
