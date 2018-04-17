
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import seikkailupeli.domain.Area;
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
        assertEquals("testi",area.getName());
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
    public void show() {
        assertEquals("Täällä ei ole mitään mielenkiintoista.", area.show());
    }
    
    @Test
    public void getNullLocation() {
        assertTrue(area.getLocation()==null);
    }
    
    @Test
    public void setLocation() {
        Location l = new Location(0,0);
        area.setLocation(l);
        assertEquals(l, area.getLocation());
    }
}
