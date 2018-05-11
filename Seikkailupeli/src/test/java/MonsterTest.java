
import adventuregame.domain.Area;
import adventuregame.domain.Monster;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class MonsterTest {

    private Monster monster;

    @Before
    public void setup() {
        monster = new Monster("testi", "toimiiko");
    }

    @Test
    public void getters() {
        assertEquals("testi", monster.getName());
        assertEquals("toimiiko", monster.getSlogan());
        assertEquals(5, monster.getLife());
        assertTrue(monster.getArea() == null);
    }

    @Test
    public void getterSetterArea() {
        Area test = new Area("testi", "toimiiko");
        monster.setArea(test);
        assertEquals(test, monster.getArea());
    }

    @Test
    public void testToString() {
        assertEquals("TESTI huutaa TOIMIIKO!", monster.toString());
    }

    @Test
    public void testHitting() {
        monster.hitMonster(0);
        assertEquals(5, monster.getLife());
        monster.hitMonster(1);
        assertEquals(4, monster.getLife());
        monster.hitMonster(-1);
        assertEquals(5, monster.getLife());
    }

    @Test
    public void testDeath() {
        assertFalse(monster.isDead());
        monster.hitMonster(10);
        assertTrue(monster.isDead());
    }

}
