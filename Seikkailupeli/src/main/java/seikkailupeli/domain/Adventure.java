package seikkailupeli.domain;

import java.util.ArrayList;
import java.util.Random;

public class Adventure {

    private World world;
    private Item itemGoal;
    private int timeGoal;
    private Random random;

    public Adventure(World world) {
        this.world = world;
        this.random = new Random();
    }

    public void setItemGoal(Item item) {
        this.itemGoal = item;
    }

    public void setTimeGoal(int t) {
        this.timeGoal = t;
    }

    public void takeTurn() {
        timeGoal--;
    }

    public Item getItemGoal() {
        return itemGoal;
    }

    public int getTimeGoal() {
        return timeGoal;
    }

    public void randomItemGoal() {
        if (!world.getItems().isEmpty()) {
            ArrayList<Item> items = world.getItems();
            int r = random.nextInt(items.size());
            this.itemGoal = items.get(r);
        }

    }

}
