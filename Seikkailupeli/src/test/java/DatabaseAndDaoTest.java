
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import adventuregame.dao.AreaDao;
import adventuregame.dao.Database;
import adventuregame.dao.HelperDao;
import adventuregame.dao.ItemDao;
import adventuregame.dao.MonsterDao;
import adventuregame.domain.Area;
import adventuregame.domain.Helper;
import adventuregame.domain.Item;
import adventuregame.domain.Monster;


public class DatabaseAndDaoTest {
/*
    Database d;
    AreaDao a;
    ItemDao i;
    HelperDao h;
    MonsterDao m;

    @Before
    public void setup() {
        try {
            d = new Database("jdbc:sqlite:test.db");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseAndDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        d.init();
        a = new AreaDao(d);
        i = new ItemDao(d);
        h = new HelperDao(d);
        m = new MonsterDao(d);
    }

    @Test
    public void findAllArea() throws SQLException {
        assertEquals(14, a.findAll().size());
    }

    @Test
    public void findAllItem() throws SQLException {
        assertEquals(7, i.findAll().size());
    }

    @Test
    public void findAllHelper() throws SQLException {
        assertEquals(7, h.findAll().size());
    }

    @Test
    public void findAllMonster() throws SQLException {
        assertEquals(2, m.findAll().size());
    }

    @Test
    public void saveOrUpdateArea() throws SQLException {
        Area test = new Area("testi", "testi");
        Area test2 = a.saveOrUpdate(test);
        assertEquals(test, test2);
    }

    @Test
    public void saveOrUpdateItem() throws SQLException {
        Item test = new Item("testi", "testi");
        Item test2 = i.saveOrUpdate(test);
        assertEquals(test, test2);
    }

    @Test
    public void saveOrUpdateHelper() throws SQLException {
        Helper test = new Helper("testi", "testi");
        Helper test2 = h.saveOrUpdate(test);
        assertEquals(test, test2);
    }

    @Test
    public void saveOrUpdateMonster() throws SQLException {
        Monster test = new Monster("testi", "testi");
        Monster test2 = m.saveOrUpdate(test);
        assertEquals(test, test2);
    }
    
    @Test
    public void findOneDeleteArea() throws SQLException {
        Area test = new Area("testi","testi");
        a.saveOrUpdate(test);
        int id = a.findIdByName("testi");
        a.delete(id);
        assertEquals(13,a.findAll().size());
    }*/

}
