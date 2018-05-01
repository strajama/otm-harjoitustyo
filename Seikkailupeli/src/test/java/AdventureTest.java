
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
import adventuregame.domain.Item;
import adventuregame.domain.World;

public class AdventureTest {

    Adventure ad;

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

        ad = new Adventure(w);
    }

    @Test
    public void getItemGoalNone() {
        assertTrue(ad.getItemGoal() == null);
    }

    @Test
    public void getTimeGoalNone() {
        assertTrue(ad.getTimeGoal() == 0);
    }

    @Test
    public void setTimeGoal() {
        ad.setTimeGoal(1);
        assertEquals(1, ad.getTimeGoal());
    }

    @Test
    public void setItemGoal() {
        Item item = new Item("testi", "toimiiko");
        ad.setItemGoal(item);
        assertEquals(item, ad.getItemGoal());
    }

    @Test
    public void takeTurn() {
        ad.setTimeGoal(2);
        ad.takeTurn();
        assertEquals(1, ad.getTimeGoal());
    }

    @Test
    public void makeAGameTime() {
        ad.makeAGame(10);
        assertEquals(10, ad.getTimeGoal());
    }

    @Test
    public void makeAGameItem() {
        ad.makeAGame(10);
        assertFalse(ad.getItemGoal() == null);
    }

    @Test
    public void makeAGameHelper() {
        ad.makeAGame(10);
        assertFalse(ad.getItemGoal() == null);
    }
}
