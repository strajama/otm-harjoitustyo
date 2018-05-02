
import adventuregame.dao.AreaDao;
import adventuregame.dao.Database;
import adventuregame.dao.HelperDao;
import adventuregame.dao.ItemDao;
import adventuregame.dao.MonsterDao;
import adventuregame.dao.ScoreDao;
import adventuregame.domain.Area;
import adventuregame.domain.Helper;
import adventuregame.domain.Item;
import adventuregame.domain.Monster;
import adventuregame.domain.Score;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author strajama
 */
public class DaoTest {

    Database d;
    AreaDao a;
    ItemDao i;
    HelperDao h;
    MonsterDao m;
    ScoreDao s;

    @Before
    public void setUp() throws Exception {

        d = new Database("jdbc:sqlite:test.db");
        d.init();
        a = new AreaDao(d);
        i = new ItemDao(d);
        h = new HelperDao(d);
        m = new MonsterDao(d);
        s = new ScoreDao(d);
    }

    @Test
    public void areaTest() throws SQLException {
        Area area = new Area("testi", "testing");
        assertFalse(a.findAll().isEmpty());
        assertEquals(13, a.findAll().size());
        assertEquals(area, a.saveOrUpdate(area));
        assertTrue(a.saveOrUpdate(area) == null);
        assertEquals(14, a.findAll().size());
        Integer key = a.findIdByName("testi");
        a.delete(key);
        assertEquals(13, a.findAll().size());
    }

    @Test
    public void itemTest() throws SQLException {
        Item item = new Item("testi", "testing");
        assertFalse(i.findAll().isEmpty());
        assertEquals(6, i.findAll().size());
        assertEquals(item, i.saveOrUpdate(item));
        assertTrue(i.saveOrUpdate(item) == null);
        assertEquals(7, i.findAll().size());
        Integer key = i.findIdByName("testi");
        i.delete(key);
        assertEquals(6, i.findAll().size());
    }

    @Test
    public void helperTest() throws SQLException {
        Helper helper = new Helper("testi", "testing");
        assertFalse(h.findAll().isEmpty());
        assertEquals(6, h.findAll().size());
        assertEquals(helper, h.saveOrUpdate(helper));
        assertTrue(h.saveOrUpdate(helper) == null);
        assertEquals(7, h.findAll().size());
        Integer key = h.findIdByName("testi");
        h.delete(key);
        assertEquals(6, h.findAll().size());
    }

    @Test
    public void monsterTest() throws SQLException {
        Monster monster = new Monster("testi", "testing");
        assertFalse(m.findAll().isEmpty());
        assertEquals(1, m.findAll().size());
        assertEquals(monster, m.saveOrUpdate(monster));
        assertTrue(m.saveOrUpdate(monster) == null);
        assertEquals(2, m.findAll().size());
        Integer key = m.findIdByName("testi");
        m.delete(key);
        assertEquals(1, m.findAll().size());
    }

    @Test
    public void scoreTest() throws SQLException {
        Score score = new Score("testi", 10);
        assertTrue(s.findAll().isEmpty());
        assertEquals(0, s.findAll().size());
        assertEquals(0, s.bestScores().size());
        assertEquals(score, s.saveOrUpdate(score));
        assertTrue(s.saveOrUpdate(score) == null);
        assertEquals(1, s.findAll().size());
        assertEquals(1, s.bestScores().size());
        assertEquals(10, s.bestScores().get(0).getPoints());
        Integer key = s.findIdByName("testi");
        s.delete(key);
        assertEquals(0, s.findAll().size());
    }
}
