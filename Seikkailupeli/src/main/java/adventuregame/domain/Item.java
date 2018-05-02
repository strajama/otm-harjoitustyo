package adventuregame.domain;

/**
 * Item-luokka on tarkoitettu esineiden ylläpitämiseen ja toteuttaa abstraktin
 * luokan Finding
 * @author strajama
 */
public class Item extends Finding {

    public Item(String name, String description) {
        super(name, description);
    }
/**
 * Metodi palauttaa tiedon siitä, että Item on Item
 * @return true
 */
    @Override
    public boolean isItem() {
        return true;
    }
}
