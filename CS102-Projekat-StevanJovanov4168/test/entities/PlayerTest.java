/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author steva
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCountry method, of class Player.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        Player instance = new Player("J J", "Forward", "Serbia", 9, 0.99, 2);
        String expResult = "Serbia";
        String result = instance.getCountry();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPosition method, of class Player.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Player instance = new Player("J J", "Forward", "Serbia", 9, 0.99, 2);
        String expResult = "Forward";
        String result = instance.getPosition();
        assertEquals(expResult, result);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testSetSkill(){
        System.out.println("setSkill");
        Player p = new Player();
        p.setSkill(-5); 
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testSetForm(){
        System.out.println("setForm");
        Player p = new Player();
        p.setForm(1.04); 
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testSetPTeamID(){
        System.out.println("setPTeamID");
        Player p = new Player();
        p.setPTeamId(25); 
    }
}
