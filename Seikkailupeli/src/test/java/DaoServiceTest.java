
import adventuregame.dao.DaoService;
import adventuregame.dao.Database;
import java.sql.SQLException;
import static org.junit.Assert.assertFalse;
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
public class DaoServiceTest {

    private DaoService dao;
    private Database d;

    @Before
    public void setup() throws ClassNotFoundException, SQLException {
        d = new Database("jdbc:sqlite:test.db");
        d.init();
        dao = new DaoService(d);
    }
    
    @Test
    public void daoGettersNotNull() {
        assertFalse(dao.getAreaDao()== null);
        assertFalse(dao.getItemDao()== null);
        assertFalse(dao.getHelperDao()== null);
        assertFalse(dao.getMonsterDao()== null);
        assertFalse(dao.getScoreDao()== null);
    }
}
