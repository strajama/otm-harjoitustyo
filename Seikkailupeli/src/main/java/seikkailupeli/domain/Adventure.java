
package seikkailupeli.domain;

import seikkailupeli.domain.Item;
import seikkailupeli.domain.World;

public class Adventure {
 
    private World world;
    private boolean find;
    private Item itemGoal;
    private int timeGoal;

    public Adventure(World world) {
        this.world = world;
        this.find = false;
    }
    
    public void itemGoal(Item item) {
        this.itemGoal = item;
    }
    
    public void timeGoal(int t) {
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
    
}
