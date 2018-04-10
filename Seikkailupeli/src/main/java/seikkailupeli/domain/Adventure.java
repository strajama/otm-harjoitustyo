/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seikkailupeli.domain;

import seikkailupeli.domain.Item;
import seikkailupeli.domain.World;

/**
 *
 * @author strajama
 */
public class Adventure {
 
    private World world;
    private boolean find;
    private Item itemGoal;
    private int timeGoal;

    public Adventure(World world) {
        this.world = world;
        this.find = false;
    }
    
    public void itemGoal (Item item) {
        this.itemGoal = item;
    }
    
    public void timeGoal(int t) {
        this.timeGoal = t;
    }
    
    public void takeTurn() {
        timeGoal --;
    }

    public Item getItemGoal() {
        return itemGoal;
    }

    public int getTimeGoal() {
        return timeGoal;
    }
    
}
