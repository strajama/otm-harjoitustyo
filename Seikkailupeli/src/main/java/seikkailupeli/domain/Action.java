/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seikkailupeli.domain;

import seikkailupeli.domain.Area;
import seikkailupeli.domain.World;

/**
 *
 * @author strajama
 */
public class Action {

    public Action() {
    }

    public void move(World world, Player player, Direction d) {
        Area now = player.getArea();

        Location newL;
        switch (d) {
            case NORTH:
                if (now.getLocation().getI() - 1 >= 0) {
                    newL = new Location(now.getLocation().getI() - 1, now.getLocation().getJ());
                } else {
                    newL = new Location(world.getGrid().length - 1, now.getLocation().getJ());
                }
                player.setArea(world.findArea(newL));
                break;
            case SOUTH:
                if (now.getLocation().getI() + 1 < world.getGrid().length) {
                    newL = new Location(now.getLocation().getI() + 1, now.getLocation().getJ());
                } else {
                    newL = new Location(0, now.getLocation().getJ());
                }
                player.setArea(world.findArea(newL));
                break;
            case EAST:
                if (now.getLocation().getJ() - 1 >= 0) {
                    newL = new Location(now.getLocation().getI(), now.getLocation().getJ() - 1);
                } else {
                    newL = new Location(now.getLocation().getI(), world.getGrid()[0].length - 1);
                }
                player.setArea(world.findArea(newL));
                break;
            case WEST:
                if (now.getLocation().getJ() + 1 < world.getGrid()[0].length) {
                    newL = new Location(now.getLocation().getI(), now.getLocation().getJ() + 1);
                } else {
                    newL = new Location(now.getLocation().getI(), 0);
                }
                player.setArea(world.findArea(newL));
                break;
        }
    }

    public void take(World world, Player player) {
        if (!player.getArea().getItems().isEmpty()) {
            Item item = player.getArea().giveSomeItem();
            player.putInBag(item);
        }
    }
}
