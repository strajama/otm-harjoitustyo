package seikkailupeli.domain;

public class Monster extends Finding {

    public Monster(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean isItem() {
        return false;
    }

}
