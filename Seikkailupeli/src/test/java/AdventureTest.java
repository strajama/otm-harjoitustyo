
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import seikkailupeli.domain.Adventure;
import seikkailupeli.domain.Item;
import seikkailupeli.domain.World;

public class AdventureTest {

    Adventure a;

    @Before
    public void setup() throws Exception {
        a = new Adventure(new World(3, 4));
    }

    @Test
    public void getItemGoalNone() {
        assertTrue(a.getItemGoal() == null);
    }

    @Test
    public void getTimeGoalNone() {
        assertTrue(a.getTimeGoal() == 0);
    }

    @Test
    public void setTimeGoal() {
        a.setTimeGoal(1);
        assertEquals(1, a.getTimeGoal());
    }

    @Test
    public void setItemGoal() {
        Item item = new Item("testi", "toimiiko");
        a.setItemGoal(item);
        assertEquals(item, a.getItemGoal());
    }

    @Test
    public void takeTurn() {
        a.setTimeGoal(2);
        a.takeTurn();
        assertEquals(1, a.getTimeGoal());
    }
    
    @Test
    public void randomItemTestNull() {
        a.randomItemGoal();
        assertTrue(a.getItemGoal()==null);
    }
}
