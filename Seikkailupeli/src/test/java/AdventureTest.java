
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import adventuregame.dao.AreaDao;
import adventuregame.dao.Database;
import adventuregame.dao.HelperDao;
import adventuregame.dao.ItemDao;
import adventuregame.dao.MonsterDao;
import adventuregame.domain.Adventure;
import adventuregame.domain.World;

public class AdventureTest {

    Adventure adventure;

    World w;
    Database d;
    AreaDao a;
    ItemDao i;
    HelperDao h;
    MonsterDao m;

    @Before
    public void setup() throws Exception {

        d = new Database("jdbc:sqlite:test.db");
        d.init();
        a = new AreaDao(d);
        i = new ItemDao(d);
        h = new HelperDao(d);
        m = new MonsterDao(d);
        w = new World(a, i, h, m);

        adventure = new Adventure(w);
    }

    @Test
    public void gettersWork() {
        assertEquals(w, adventure.getWorld());
        assertTrue(adventure.getItemGoal() == null);
        assertTrue(adventure.getHelperGoal() == null);
        assertEquals(0, adventure.getPoints());
    }

    @Test
    public void takeTurn() {
        adventure.takeTurn();
        assertEquals(-1, adventure.getPoints());
    }

    @Test
    public void givePoints() {
        adventure.givePoints(10);
        assertEquals(10, adventure.getPoints());
    }

    @Test
    public void makeAGameItemNull() {
        adventure.makeAGame();
        assertFalse(adventure.getHelperGoal() == null);
        assertFalse(adventure.getItemGoal() == null);
    }

    @Test
    public void makeAGameHelperNull() {
        adventure.makeAGame();
        assertFalse(adventure.getItemGoal() == null);
    }
}
