
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import seikkailupeli.domain.Player;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author strajama
 */
public class PlayerTest {
 
    Player p;
    
    @Before
    public void setup() {
        p = new Player();
    }
    
    @Test
    public void bag() {
        assertEquals("Reppusi on tyhjä.",p.bag());
    }
    
    @Test
    public void nullArea() {
        assertTrue(p.getArea()==null);
    }
    
    @Test
    public void emptyItems() {
        assertTrue(p.getItems().isEmpty());
    }
}
