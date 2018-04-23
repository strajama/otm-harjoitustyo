package seikkailupeli.domain;

public class Action {
    
    private World world;
    private Player player;

    public Action(World world, Player player) {
        this.world = world;
        this.player = player;
    }

    public boolean move(Direction d) {
        Area now = player.getArea();
        if (now.getNeighbors().get(d) != null) {
            Area next = now.getNeighbors().get(d);
            player.setArea(next);
            return true;
        }
        return false;
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
