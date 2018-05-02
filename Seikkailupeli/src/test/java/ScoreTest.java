
import adventuregame.domain.Score;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class ScoreTest {

    Score score;

    @Before
    public void setup() {
        score = new Score("testi", 10);
    }

    @Test
    public void getters() {
        assertEquals("testi", score.getName());
        assertEquals(10, score.getPoints());
    }
}
