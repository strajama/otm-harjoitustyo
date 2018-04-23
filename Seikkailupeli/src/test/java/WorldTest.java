
import org.junit.Before;
import seikkailupeli.dao.AreaDao;
import seikkailupeli.dao.Database;
import seikkailupeli.dao.HelperDao;
import seikkailupeli.dao.ItemDao;
import seikkailupeli.dao.MonsterDao;
import seikkailupeli.domain.World;

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
