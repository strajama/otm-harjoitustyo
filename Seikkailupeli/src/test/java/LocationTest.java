
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import seikkailupeli.domain.Location;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author strajama
 */
public class LocationTest {

    Location location;

    @Before
    public void setup() {
        location = new Location(0, 0);
    }

    @Test
    public void getI() {
        assertEquals(0, location.getI());
    }

    @Test
    public void getJ() {
        assertEquals(0, location.getJ());
    }

    @Test
    public void setI() {
        location.setI(1);
        assertEquals(1, location.getI());
    }

    @Test
    public void setJ() {
        location.setJ(1);
        assertEquals(1, location.getJ());
    }
}
