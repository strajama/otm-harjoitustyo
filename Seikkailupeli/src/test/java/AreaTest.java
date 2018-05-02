
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import adventuregame.domain.Area;
import adventuregame.domain.Direction;
import adventuregame.domain.Helper;
import adventuregame.domain.Item;
import adventuregame.domain.Monster;
import adventuregame.domain.Player;

public class AreaTest {

    Area area;

    public AreaTest() {
    }

    @Before
    public void setup() {
        area = new Area("testi", "kokeillaan nyt toimiiko");
    }

    @Test
    public void gettersTest() {
        assertEquals("testi", area.getName());
        assertEquals("kokeillaan nyt toimiiko", area.getDescription());
        assertTrue(area.getFindings().isEmpty());
        assertEquals(4, area.getNeighbors().size());
        assertTrue(area.getMonster() == null);
    }

    @Test
    public void areaToString() {
        assertEquals("testi", area.toString());
    }

    @Test
    public void showTest() {
        assertEquals("Täällä ei ole mitään mielenkiintoista.", area.show());
        Item item = new Item("item", "itemirator");
        area.putFinding(item);
        assertEquals("Näet jotain mielenkiintoista: ITEM.", area.show());
        assertFalse(area.getFindings().isEmpty());
        area.giveSomeItem();
        assertEquals("Täällä ei ole mitään mielenkiintoista.", area.show());
        assertTrue(area.getFindings().isEmpty());
        Helper helper = new Helper("helper", "helpirator");
        area.putFinding(helper);
        assertEquals("Näet jotain mielenkiintoista: HELPER.", area.show());
        assertFalse(area.getFindings().isEmpty());
        area.removeHelper(helper);
        assertEquals("Täällä ei ole mitään mielenkiintoista.", area.show());
        assertTrue(area.getFindings().isEmpty());
        area.putFinding(item);
        area.putFinding(helper);
        assertEquals("Näet jotain mielenkiintoista: ITEM, HELPER.", area.show());
    }

    @Test
    public void testMonster() {
        assertTrue(area.getMonster() == null);
        assertEquals("Täällä ei ole hirviöitä.", area.showMonster());
        Monster test = new Monster("testi", "kokeillaan nyt toimiiko");
        area.putMonster(test);
        assertFalse(area.getMonster() == null);
        assertEquals("Edessäsi on hirvittävä TESTI. Se sanoo: 'kokeillaan nyt toimiiko'.", area.showMonster());
        area.removeMonster();
        assertTrue(area.getMonster() == null);
        assertEquals("Täällä ei ole hirviöitä.", area.showMonster());
    }

    @Test
    public void testNeighbors() {
        assertTrue(area.randomNeighbor() == null);
        assertTrue(area.getNeighbors().size() == 4);
        Area neighbor = new Area("naapuri", "testi");
        area.putNeighbors(neighbor, null, null, null);
        assertEquals(neighbor, area.getNeighbors().get(Direction.NORTH));
        area.putNeighbors(null, neighbor, null, null);
        assertEquals(neighbor, area.getNeighbors().get(Direction.EAST));
        area.putNeighbors(null, null, neighbor, null);
        assertEquals(neighbor, area.getNeighbors().get(Direction.WEST));
        area.putNeighbors(null, null, null, neighbor);
        assertEquals(neighbor, area.getNeighbors().get(Direction.SOUTH));
    }

    @Test
    public void testItems() {
        assertTrue(area.getFindings().isEmpty());
        Item item = new Item("testi", "testi");
        area.putFinding(item);
        assertFalse(area.getFindings().isEmpty());
        assertEquals(item, area.giveSomeItem());
        assertTrue(area.getFindings().isEmpty());
        assertTrue(area.giveSomeItem() == null);
        Helper helper = new Helper("testi", "testi");
        area.putFinding(helper);
        assertTrue(area.giveSomeItem() == null);
    }

    @Test
    public void testRemovingHelpers() {
        assertTrue(area.getFindings().isEmpty());
        Helper helper = new Helper("testi", "testi");
        Item item = new Item("testi", "testi");
        Helper helper2 = new Helper("testi2", "testi2");
        area.removeHelper(helper);
        assertTrue(area.getFindings().isEmpty());
        area.putFinding(helper);
        assertFalse(area.getFindings().isEmpty());
        area.removeHelper(helper2);
        assertFalse(area.getFindings().isEmpty());
        area.removeHelper(helper);
        assertTrue(area.getFindings().isEmpty());
        area.putFinding(item);
        assertFalse(area.getFindings().isEmpty());
    }

    @Test
    public void testSpeakHelper() {
        Helper helper = new Helper("testi", "testi");
        Item item = new Item("testi", "testi");
        Player player = new Player();
        assertTrue(area.speakWithNewHelper(player) == null);
        area.putFinding(helper);
        assertEquals(helper, area.speakWithNewHelper(player));
        player.speakWith(helper);
        assertTrue(area.speakWithNewHelper(player) == null);
        area.putFinding(item);
        assertTrue(area.speakWithNewHelper(player) == null);
    }

    @Test
    public void testFindHelper() {
        Helper helper = new Helper("testi", "testi");
        Item item = new Item("testi", "testi");
        assertTrue(area.findHelper() == null);
        area.putFinding(item);
        assertTrue(area.findHelper() == null);
        area.putFinding(helper);
        assertEquals(helper, area.findHelper());
    }

    @Test
    public void hashTest() {
        Area area1 = new Area("testi", "testi");
        Area area2 = new Area("testi", "testi2");
        Area area3 = new Area("testi3", "testi");
        assertEquals(area1.hashCode(), area2.hashCode());
        assertFalse(area1.hashCode() == area3.hashCode());
        assertFalse(area2.hashCode() == area3.hashCode());
    }

    @Test
    public void equalTest() {
        Area area1 = new Area("testi", "testi");
        Area area2 = new Area("testi", "testi2");
        Area area3 = new Area("testi3", "testi");
        assertTrue(area1.equals(area2));
        assertFalse(area1.equals(area3));
        assertFalse(area2.equals(area3));
        assertFalse(area1.equals(null));
        assertFalse(area1.equals("testi"));
    }
}
