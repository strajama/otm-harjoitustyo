
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import seikkailupeli.dao.AreaDao;
import seikkailupeli.dao.Database;
import seikkailupeli.dao.HelperDao;
import seikkailupeli.dao.ItemDao;
import seikkailupeli.dao.MonsterDao;
import seikkailupeli.domain.Action;
import seikkailupeli.domain.Adventure;
import seikkailupeli.domain.Direction;
import seikkailupeli.domain.Helper;
import seikkailupeli.domain.Item;
import seikkailupeli.domain.World;

public class ActionTest {

    Action action;

    Adventure ad;
    World w;
    Database d;
    AreaDao a;
    ItemDao i;
    HelperDao h;
    MonsterDao m;

    @Before
    public void setUp() throws Exception {

        d = new Database("jdbc:sqlite:test.db");
        d.init();
        a = new AreaDao(d);
        i = new ItemDao(d);
        h = new HelperDao(d);
        m = new MonsterDao(d);
        w = new World(a, i, h, m);
        ad = new Adventure(w);
        action = new Action(ad);
    }

    @Test
    public void moveTrue() {
        assertTrue(action.move(Direction.NORTH));
    }

    @Test
    public void moveFalse() {
        assertFalse(action.move(Direction.SOUTH));
    }

    @Test
    public void takeNull() {
        assertTrue(action.take() == null);
    }

    @Test
    public void speakNull() {
        assertTrue(action.speak() == null);
    }

    @Test
    public void takeTest() {
        Item test = new Item("testi", "testi");
        w.getHome().putFinding(test);
        assertTrue(action.take() == test);
    }

    @Test
    public void speakTest() {
        Helper test = new Helper("testi", "testi");
        w.getHome().putFinding(test);
        assertTrue(action.speak() == test);
    }
}
