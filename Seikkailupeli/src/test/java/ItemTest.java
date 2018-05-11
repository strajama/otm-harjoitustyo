
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import adventuregame.domain.Item;

public class ItemTest {

    private Item item;

    @Before
    public void setup() {
        item = new Item("testi", "toimiiko");
    }

    @Test
    public void getters() {
        assertEquals("testi", item.getName());
        assertEquals("toimiiko", item.getDescription());
    }

    @Test
    public void itemToString() {
        assertEquals("TESTI, toimiiko", item.toString());
    }

    @Test
    public void hashTest() {
        Item n = new Item("testi", "erilainen kuvailu");
        Item m = new Item("testi1", "eriniminen");
        assertEquals(item.hashCode(), n.hashCode());
        assertFalse(item.hashCode() == m.hashCode());
    }

    @Test
    public void equalsTestSame() {
        Item n = new Item("testi", "erilainen kuvailu");
        Item m = new Item("testi1", "eriniminen");
        assertTrue(item.equals(n));
        assertFalse(item.equals(m));
        assertFalse(item.equals("testi"));
        assertFalse(item.equals(null));
    }

    @Test
    public void isItem() {
        assertTrue(item.isItem());
    }
}
