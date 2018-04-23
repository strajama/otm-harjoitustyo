package seikkailupeli.domain;

import java.util.ArrayList;
import java.util.Random;

public class Adventure {

    private World world;
    private Item itemGoal;
    private Helper helperGoal;
    private int timeGoal;
    private Random random;
    private boolean over;
    private Monster monster;

    public Adventure(World world, Monster monster) {
        this.world = world;
        this.monster = monster;
        this.random = new Random();
        this.over = false;
        Area area = world.getAreas().get(0);
        area.putMonster(monster);
        monster.setArea(area);
    }

    public void setItemGoal(Item item) {
        this.itemGoal = item;
    }

    public void setTimeGoal(int t) {
        this.timeGoal = t;
    }

    public void takeTurn() {
        if (!over) {
            timeGoal--;
            if (timeGoal <= 0) {
                over = true;
            }
        }
        Area next = monster.getArea().randomNeighbor();
        monster.getArea().removeMonster(monster);
        monster.setArea(next);
        next.putMonster(monster);
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

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Helper getHelperGoal() {
        return helperGoal;
    }

    public void setHelperGoal(Helper helperGoal) {
        this.helperGoal = helperGoal;
    }

    public void randomHelperGoal() {
        if (!world.getHelpers().isEmpty()) {
            ArrayList<Helper> helpers = world.getHelpers();
            int r = random.nextInt(helpers.size());
            this.helperGoal = helpers.get(r);
        }
    }
}
