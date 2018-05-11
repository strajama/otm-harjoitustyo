
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import adventuregame.dao.DaoService;
import adventuregame.dao.Database;
import adventuregame.domain.Action;
import adventuregame.domain.Adventure;
import adventuregame.domain.Area;
import adventuregame.domain.Direction;
import adventuregame.domain.Helper;
import adventuregame.domain.Item;
import adventuregame.domain.Monster;
import adventuregame.domain.World;
import adventuregame.ui.TestMain;
import adventuregame.ui.UI;
import static org.junit.Assert.assertEquals;

public class ActionTest {

    private Action action;

    private Adventure a;
    private Database d;
    private DaoService dao;
    private UI s;

    @Before
    public void setUp() throws Exception {

        d = new Database("jdbc:sqlite:test.db");
        d.init();
        dao = new DaoService(d);
        a = new Adventure(new World(dao));
        s = new TestMain();
        action = new Action(a, s);
    }

    @Test
    public void moveTesting() {
        Area monsterArea = a.getMonster().getArea();
        assertEquals(a.getWorld().getHome(), a.getPlayer().getArea());
        action.move(Direction.SOUTH);
        assertEquals(0, a.getPoints());
        assertTrue(a.getWorld().getHome().equals(a.getPlayer().getArea()));
        assertTrue(monsterArea.equals(a.getMonster().getArea()));
        action.move(Direction.NORTH);
        assertEquals(-1, a.getPoints());
        assertFalse(a.getWorld().getHome().equals(a.getPlayer().getArea()));
        assertFalse(monsterArea.equals(a.getMonster().getArea()));
        action.move(Direction.SOUTH);
        assertEquals(-2, a.getPoints());
        assertTrue(a.getWorld().getHome().equals(a.getPlayer().getArea()));
    }

    @Test
    public void takeTesting() {
        Item item = new Item("testi", "testi");
        Helper helper = new Helper("testi", "toimiiko");
        action.take();
        assertEquals(0, a.getPoints());
        assertTrue(a.getPlayer().getItems().isEmpty());
        a.getPlayer().getArea().putFinding(helper);
        action.take();
        assertEquals(0, a.getPoints());
        assertTrue(a.getPlayer().getItems().isEmpty());
        a.getPlayer().getArea().putFinding(item);
        action.take();
        assertEquals(4, a.getPoints());
        assertTrue(a.getPlayer().getItems().containsValue(item));
        a.setItemGoal(item);
        a.getPlayer().getArea().putFinding(item);
        action.take();
        assertEquals(13, a.getPoints());

    }

    @Test
    public void speakTesting() {
        Item item = new Item("testi", "testi");
        Helper helper = new Helper("testi", "toimiiko");
        Helper helper2 = new Helper("testi2", "toimiiko");
        helper.setItem(item);
        a.setHelperGoal(helper2);
        action.speak();
        assertEquals(0, a.getPoints());
        assertTrue(a.getPlayer().getHelpers().isEmpty());
        a.getPlayer().getArea().putFinding(item);
        action.speak();
        assertEquals(0, a.getPoints());
        assertTrue(a.getPlayer().getHelpers().isEmpty());
        a.getPlayer().getArea().giveSomeItem();
        a.getPlayer().getArea().putFinding(helper);
        action.speak();
        assertEquals(4, a.getPoints());
        assertTrue(a.getPlayer().spokenWith(helper.getName()));
        a.getPlayer().getArea().removeHelper(helper);
        a.getPlayer().getArea().putFinding(helper2);
        helper2.setItem(item);
        action.speak();
        assertEquals(13, a.getPoints());
        assertTrue(a.getPlayer().spokenWith(helper2.getName()));
    }

    @Test
    public void giveTesting() {
        Item item = new Item("testi", "testi");
        Item item2 = new Item("testi2", "testi");
        Helper helper = new Helper("testi", "toimiiko");
        helper.setItem(item);
        action.give();
        assertEquals(0, a.getPoints());
        a.getPlayer().putInBag(item2);
        action.give();
        assertEquals(0, a.getPoints());
        assertTrue(a.getPlayer().getItems().containsValue(item2));
        a.getPlayer().getArea().putFinding(item2);
        action.give();
        assertEquals(0, a.getPoints());
        a.getPlayer().getArea().giveSomeItem();
        a.getPlayer().getArea().putFinding(helper);
        action.give();
        assertEquals(0, a.getPoints());
        assertTrue(a.getPlayer().getItems().size() == 1);
        action.give();
        assertEquals(0, a.getPoints());
        assertTrue(a.getPlayer().getItems().size() == 1);
        helper.setItem(item2);
        action.give();
        assertEquals(9, a.getPoints());
        assertFalse(a.getPlayer().getItems().containsValue(item));

    }

    @Test
    public void hitTesting() {
        Item item = new Item("testi", "testi");
        Monster monster = a.getMonster();
        action.hit();
        assertEquals(0, a.getPoints());
        assertEquals(5, monster.getLife());
        monster.setArea(a.getWorld().getHome());
        action.hit();
        assertEquals(4, monster.getLife());
        assertEquals(-1, a.getPoints());
        assertFalse(monster.isDead()); 
        a.getPlayer().putInBag(item);
        action.hit();
        assertEquals(2, monster.getLife());
        assertEquals(-2, a.getPoints());
        assertFalse(monster.isDead()); 
        action.hit();
        assertEquals(0, monster.getLife());
        assertEquals(17, a.getPoints());
        assertTrue(monster.isDead());        
        action.hit();
        assertEquals(17,a.getPoints());
        assertTrue(monster.isDead());
        a.getMonster().setArea(a.getMonster().getArea().randomNeighbor());
        action.hit();
        assertFalse(a.getPlayer().getArea().equals(a.getMonster().getArea()));
        assertEquals(17,a.getPoints());
        assertTrue(monster.isDead());
    }

    @Test
    public void testPointsZero() {
        assertEquals(0, a.getPoints());
        action.take();
        assertEquals(0, a.getPoints());
        action.give();
        assertEquals(0, a.getPoints());
        action.hit();
        assertEquals(0, a.getPoints());
        action.speak();
        assertEquals(0, a.getPoints());
        action.move(Direction.SOUTH);
        assertEquals(0, a.getPoints());
    }

    @Test
    public void pointsTestItemHelper() {
        Item item = new Item("testi", "testi");
        Helper helper = new Helper("testi", "toimiiko");
        helper.setItem(item);
        a.getPlayer().putInBag(item);
        action.move(Direction.NORTH);
        assertEquals(-1, a.getPoints());
        action.move(Direction.SOUTH);
        assertEquals(-2, a.getPoints());
        a.getWorld().getHome().putFinding(item);
        action.take();
        assertEquals(2, a.getPoints());
        a.getWorld().getHome().putFinding(helper);
        action.speak();
        assertEquals(6, a.getPoints());
        action.give();
        assertEquals(15, a.getPoints());
    }

    @Test
    public void pointsItemHelperGoals() {
        Item item = new Item("testi", "testi");
        Helper helper = new Helper("testi", "toimiiko");
        helper.setItem(item);
        a.setHelperGoal(helper);
        a.setItemGoal(item);
        a.getPlayer().getArea().putFinding(item);
        action.take();
        assertEquals(9, a.getPoints());
        a.getPlayer().getArea().putFinding(helper);
        action.speak();
        assertEquals(18, a.getPoints());
        Area monsterArea = a.getMonster().getArea();
        a.getMonster().hitMonster(10);
        action.move(Direction.NORTH);
        assertEquals(17, a.getPoints());
        assertTrue(a.getMonster().getArea().equals(monsterArea));
    }
}
