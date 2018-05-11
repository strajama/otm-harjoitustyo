
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import adventuregame.domain.Helper;
import adventuregame.domain.Item;

public class HelperTest {

    private Helper helper;

    @Before
    public void setup() {
        helper = new Helper("testi", "toimiiko");
    }

    @Test
    public void getters() {
        assertEquals("testi", helper.getName());
        assertEquals("toimiiko", helper.getDescription());
        assertTrue(helper.getItem() == null);
    }

    @Test
    public void itemGetterSetter() {
        Item test = new Item("testi", "toimiiko");
        helper.setItem(test);
        assertEquals(test, helper.getItem());
    }

    @Test
    public void helperToString() {
        assertEquals("TESTI, toimiiko", helper.toString());
    }

    @Test
    public void hashTesting() {
        Helper n = new Helper("testi", "erilainen kuvailu");
        Helper m = new Helper("test", "toimiiko");
        assertEquals(helper.hashCode(), n.hashCode());
        assertFalse(helper.hashCode() == m.hashCode());
    }

    @Test
    public void equalsTest() {
        Helper n = new Helper("testi", "erilainen kuvailu");
        Helper m = new Helper("testi1", "eriniminen");
        assertTrue(helper.equals(n));
        assertFalse(helper.equals(m));
        assertFalse(helper.equals("testi"));
        assertFalse(helper.equals(null));
    }

    @Test
    public void isItem() {
        assertFalse(helper.isItem());
    }
}
