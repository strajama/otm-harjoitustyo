package seikkailupeli.domain;

public class Helper extends Finding {

    public Helper(String name, String description) {
        super(name,description);
    }

    @Override
    public boolean isItem() {
        return false;
    }
}
