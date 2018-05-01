package adventuregame.domain;

/**
 * Luokka Item on tarkoitettu esineiden ylläpitämiseen ja toteuttaa abstraktin
 * luokan Finding
 * @author strajama
 */
public class Item extends Finding {

    public Item(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean isItem() {
        return true;
    }
}
