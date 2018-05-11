
import adventuregame.domain.Area;
import adventuregame.domain.Helper;
import adventuregame.domain.Item;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import adventuregame.domain.Player;
import static org.junit.Assert.assertFalse;

public class PlayerTest {

    private Player player;

    @Before
    public void setup() {
        player = new Player();
    }

    @Test
    public void bagItemsTest() {
        assertEquals("Reppusi on tyhjä.", player.bag());
        Item test = new Item("testi", "testing");
        player.putInBag(test);
        assertEquals("Repussasi on jotain ", player.bag());
        player.removeFromBag(test);
        assertEquals("Reppusi on tyhjä.", player.bag());
    }

    @Test
    public void testAreas() {
        assertTrue(player.getArea() == null);
        Area test = new Area("testi", "testing");
        player.setArea(test);
        assertEquals(test, player.getArea());
    }

    @Test
    public void itemsTest() {
        assertTrue(player.getItems().isEmpty());
        Item test = new Item("testi", "testing");
        player.putInBag(test);
        assertFalse(player.getItems().isEmpty());
        player.removeFromBag(test);
        assertTrue(player.getItems().isEmpty());
    }

    @Test
    public void helpersTest() {
        assertTrue(player.getHelpers().isEmpty());
        Helper test = new Helper("testi", "testing");
        assertFalse(player.spokenWith("testi"));
        player.speakWith(test);
        assertTrue(player.spokenWith("testi"));
        assertFalse(player.getHelpers().isEmpty());
    }
}
