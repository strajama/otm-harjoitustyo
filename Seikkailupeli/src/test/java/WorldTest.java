
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import adventuregame.dao.AreaDao;
import adventuregame.dao.Database;
import adventuregame.dao.HelperDao;
import adventuregame.dao.ItemDao;
import adventuregame.dao.MonsterDao;
import adventuregame.domain.World;

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
        World world = new World(a, i, h, m);
    }


}
