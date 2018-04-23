
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import seikkailupeli.domain.Item;

public class ItemTest {

    Item item;

    @Before
    public void setup() {
        item = new Item("testi", "toimiiko");
    }

    @Test
    public void getName() {
        assertEquals("testi", item.getName());
    }

    @Test
    public void getDescription() {
        assertEquals("toimiiko", item.getDescription());
    }

    @Test
    public void itemToString() {
        assertEquals("TESTI, toimiiko", item.toString());
    }

    @Test
    public void hashi() {
        Item n = new Item("testi", "erilainen kuvailu");
        assertEquals(item.hashCode(), n.hashCode());
    }

    @Test
    public void equalsTestSame() {
        Item n = new Item("testi", "erilainen kuvailu");
        assertTrue(item.equals(n));
    }

    @Test
    public void equalsTestDifferent1() {
        Item n = new Item("testi1", "eriniminen");
        assertFalse(item.equals(n));
    }

    @Test
    public void equalsTestDifferent2() {
        assertFalse(item.equals("testi"));
    }

    @Test
    public void equalsTestDifferent3() {
        assertFalse(item.equals(null));
    }
    @Test
    public void isItem() {
        assertTrue(item.isItem());
    }
}
