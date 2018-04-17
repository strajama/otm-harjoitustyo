

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import seikkailupeli.domain.Location;
import seikkailupeli.domain.World;

public class WorldTest {

    World world;

    public WorldTest() {
    }

    @Before
    public void setUp() throws Exception {
        world = new World(3, 4);
        world.createWorldTest();
    }

    @Test
    public void worldSize() {
        assertEquals(12, world.getSize());
    }

    @Test
    public void thereIsGrid() {
        assertTrue(world.getGrid() != null);
    }

    @Test
    public void thereIsPlayer() {
        assertTrue(world.getPlayer() != null);
    }
    
    @Test
    public void thereIsArea() {
        assertTrue(world.findArea(new Location(0,0)) != null);
    }
    
}
