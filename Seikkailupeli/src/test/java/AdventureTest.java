
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import adventuregame.dao.AreaDao;
import adventuregame.dao.DaoService;
import adventuregame.dao.Database;
import adventuregame.dao.HelperDao;
import adventuregame.dao.ItemDao;
import adventuregame.dao.MonsterDao;
import adventuregame.domain.Adventure;
import adventuregame.domain.World;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdventureTest {

    private Adventure adventure;
    private Database d;
    private DaoService dao;

    @Before
    public void setup() {

        try {
            d = new Database("jdbc:sqlite:test.db");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdventureTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        d.init();
        try {
            dao = new DaoService(d);
        } catch (SQLException ex) {
            Logger.getLogger(AdventureTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            adventure = new Adventure(new World(dao));
        } catch (Exception ex) {
            Logger.getLogger(AdventureTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void gettersWork() {
        assertFalse(adventure.getWorld() == null);
        assertTrue(adventure.getItemGoal() == null);
        assertTrue(adventure.getHelperGoal() == null);
        assertEquals(0, adventure.getPoints());
        assertEquals("", adventure.getLastAction());
        assertFalse(adventure.getMonster() == null);
        assertFalse(adventure.getPlayer() == null);
    }

    @Test
    public void takeTurn() {
        adventure.takeTurn();
        assertEquals(-1, adventure.getPoints());
    }

    @Test
    public void givePrintPoints() {
        assertEquals("Sinulla on pisteitä 0.", adventure.printPoints());
        adventure.givePoints(10);
        assertEquals(10, adventure.getPoints());
        assertEquals("Sinulla on pisteitä 10.", adventure.printPoints());
    }

    @Test
    public void makeAGameItemHelperPlayerNotNull() {
        adventure.makeAGame();
        assertFalse(adventure.getHelperGoal() == null);
        assertFalse(adventure.getItemGoal() == null);
    }

}
