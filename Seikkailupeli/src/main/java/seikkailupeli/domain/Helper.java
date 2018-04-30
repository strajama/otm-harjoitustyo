package seikkailupeli.domain;
/**
 * Luokka Helper on tarkoitettu pelaajan tapaamien apureiden ylläpitämiseen ja
 * toteuttaa abstraktin luokan Finding
 * @author strajama
 */
public class Helper extends Finding {

    public Helper(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean isItem() {
        return false;
    }
}
