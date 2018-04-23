package seikkailupeli.domain;

public class Action {
    
    private World world;
    private Player player;

    public Action(World world, Player player) {
        this.world = world;
        this.player = player;
    }

    public void move(Direction d) {
        Area now = player.getArea();

        Location newL = player.getArea().getLocation();
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
        System.out.println(newL);
    }

    public Item take() {
        if (!player.getArea().getFindings().isEmpty()) {
            Item item = (Item) player.getArea().giveSomeItem();
            if (item != null) {
                player.putInBag(item);
                return item;
            }
        }
        return null;
    }
    
    public Helper speak() {
        
        if (!player.getArea().getFindings().isEmpty()) {
            Helper helper = player.getArea().speakHelper(player);
            if (helper != null) {
                player.speakWith(helper);
                return helper;
            }
        }        
        return null;
    }
}
