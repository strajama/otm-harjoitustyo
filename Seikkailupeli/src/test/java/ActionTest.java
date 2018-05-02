
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import adventuregame.dao.AreaDao;
import adventuregame.dao.Database;
import adventuregame.dao.HelperDao;
import adventuregame.dao.ItemDao;
import adventuregame.dao.MonsterDao;
import adventuregame.domain.Action;
import adventuregame.domain.Adventure;
import adventuregame.domain.Direction;
import adventuregame.domain.Helper;
import adventuregame.domain.Item;
import adventuregame.domain.Monster;
import adventuregame.domain.World;
import static org.junit.Assert.assertEquals;

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
    public void moveTesting() {
        assertFalse(action.move(Direction.SOUTH));
        assertTrue(action.move(Direction.NORTH));
    }

    @Test
    public void takeTesting() {
        Item item = new Item("testi", "testi");
        Helper helper = new Helper("testi", "toimiiko");
        assertTrue(action.take() == null);
        w.getHome().putFinding(helper);
        assertTrue(action.take() == null);
        w.getHome().putFinding(item);
        assertEquals(item, action.take());
    }

    @Test
    public void speakTesting() {
        Item item = new Item("testi", "testi");
        Helper helper = new Helper("testi", "toimiiko");
        assertTrue(action.speak() == null);
        w.getHome().putFinding(item);
        assertTrue(action.speak() == null);
        w.getHome().putFinding(helper);
        assertEquals(helper, action.speak());
    }

    @Test
    public void giveTesting() {
        Item item = new Item("testi", "testi");
        Item item2 = new Item("testi2", "testi");
        Helper helper = new Helper("testi", "toimiiko");
        helper.setItem(item);
        assertTrue(action.give() == null);
        w.getHome().putFinding(item);
        assertTrue(action.give() == null);
        w.getHome().putFinding(helper);
        assertTrue(action.give() == null);
        w.getPlayer().putInBag(item2);
        assertTrue(action.give() == null);
        w.getPlayer().putInBag(item);
        assertEquals(item, action.give());
    }
    
    @Test
    public void hitTesting() {
        Monster monster = w.getMonster();
        assertTrue(action.hit() == null);
        monster.setArea(w.getHome());
    }

}
