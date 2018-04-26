
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import seikkailupeli.domain.Helper;

public class HelperTest {

    Helper helper;

    @Before
    public void setup() {
        helper = new Helper("testi", "toimiiko");
    }

    @Test
    public void getName() {
        assertEquals("testi", helper.getName());
    }

    @Test
    public void getDescription() {
        assertEquals("toimiiko", helper.getDescription());
    }

    @Test
    public void helperToString() {
        assertEquals("TESTI, toimiiko", helper.toString());
    }

    @Test
    public void hashi() {
        Helper n = new Helper("testi", "erilainen kuvailu");
        assertEquals(helper.hashCode(), n.hashCode());
    }

    @Test
    public void equalsTestSame() {
        Helper n = new Helper("testi", "erilainen kuvailu");
        assertTrue(helper.equals(n));
    }

    @Test
    public void equalsTestDifferent1() {
        Helper n = new Helper("testi1", "eriniminen");
        assertFalse(helper.equals(n));
    }

    @Test
    public void equalsTestDifferent2() {
        assertFalse(helper.equals("testi"));
    }

    @Test
    public void equalsTestDifferent3() {
        assertFalse(helper.equals(null));
    }

    @Test
    public void isItem() {
        assertFalse(helper.isItem());
    }
}
