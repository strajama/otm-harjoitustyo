package seikkailupeli.domain;

import java.util.Objects;

public class Item extends Finding {

    public Item(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean isItem() {
        return true;
    }
}

