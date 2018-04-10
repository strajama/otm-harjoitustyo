/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import seikkailupeli.domain.World;

/**
 *
 * @author strajama
 */
public class WorldTest {

    World world;

    public WorldTest() {
    }

    @Before
    public void setUp() {
        world = new World(3, 4);
    }

    @Test
    public void worldSize() {
        assertEquals(12, world.getSize());
    }
}
