
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import seikkailupeli.domain.Area;
import seikkailupeli.domain.Helper;
import seikkailupeli.domain.Item;

public class AreaTest {

    Area area;

    public AreaTest() {
    }

    @Before
    public void setup() {
        area = new Area("testi", "kokeillaan nyt toimiiko");
    }

    @Test
    public void getName() {
        assertEquals("testi", area.getName());
    }

    @Test
    public void getDescription() {
        assertEquals("kokeillaan nyt toimiiko", area.getDescription());
    }

    @Test
    public void areaToString() {
        assertEquals("testi", area.toString());
    }

    @Test
    public void showNull() {
        assertEquals("Täällä ei ole mitään mielenkiintoista.", area.show());
    }

    @Test
    public void randomNeighborNull() {
        assertTrue(area.randomNeighbor() == null);
    }

    @Test
    public void neighborsNull() {
        assertTrue(area.getNeighbors().size() == 4);
    }

    @Test
    public void getFindingsEmpty() {
        assertTrue(area.getFindings().isEmpty());
    }

    @Test
    public void putItem() {
        Item test = new Item("testi", "testi");
        area.putFinding(test);
        assertFalse(area.getFindings().isEmpty());
    }

    @Test
    public void putHelper() {
        Helper test = new Helper("testi", "testi");
        area.putFinding(test);
        assertFalse(area.getFindings().isEmpty());
    }

    @Test
    public void giveItem() {
        Item test = new Item("testi", "testi");
        area.putFinding(test);
        assertEquals(test, area.giveSomeItem());
    }

}
