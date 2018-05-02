
import org.junit.Before;
import org.junit.Test;
import adventuregame.dao.AreaDao;
import adventuregame.dao.Database;
import adventuregame.dao.HelperDao;
import adventuregame.dao.ItemDao;
import adventuregame.dao.MonsterDao;
import adventuregame.domain.Area;
import adventuregame.domain.World;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class WorldTest {

    World world;

    Database d;
    AreaDao a;
    ItemDao i;
    HelperDao h;
    MonsterDao m;

    public WorldTest() {
    }

    @Before
    public void setUp() throws Exception {

        d = new Database("jdbc:sqlite:test.db");
        d.init();
        a = new AreaDao(d);
        i = new ItemDao(d);
        h = new HelperDao(d);
        m = new MonsterDao(d);
        world = new World(a, i, h, m);
    }

    @Test
    public void worldGetters() {
        Area home = new Area("koti", "Oma koti kullan kallis");
        assertFalse(world.getAreas().isEmpty());
        assertFalse(world.getItems().isEmpty());
        assertFalse(world.getHelpers().isEmpty());
        assertFalse(world.getMonster() == null);
        assertFalse(world.getPlayer() == null);
        assertEquals(home, world.getHome());
    }

}
