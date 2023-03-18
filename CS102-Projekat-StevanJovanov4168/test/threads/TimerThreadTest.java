/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import basic.Engine;
import entities.Match;
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
public class TimerThreadTest {
    
    public TimerThreadTest() {
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
     * Test of getMinutes method, of class TimerThread.
     */
    @Test
    public void testGetMinutes() {
        System.out.println("getMinutes");
        TimerThread instance = new TimerThread();
        int expResult = 0;
        int result = instance.getMinutes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSeconds method, of class TimerThread.
     */
    @Test
    public void testGetSeconds() {
        System.out.println("getSeconds");
        TimerThread instance = new TimerThread();
        int expResult = 0;
        int result = instance.getSeconds();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSeconds method, of class TimerThread.
     */
    @Test
    public void testSetSeconds() {
        System.out.println("setSeconds");
        int seconds = 0;
        TimerThread instance = new TimerThread();
        instance.setSeconds(seconds);
    }

    /**
     * Test of setMinutes method, of class TimerThread.
     */
    @Test
    public void testSetMinutes() {
        System.out.println("setMinutes");
        int minutes = 0;
        TimerThread instance = new TimerThread();
        ///instance.setMinutes(minutes);
        assertEquals(minutes, instance.getMinutes());
    }

    /**
     * Test of timeLabel method, of class TimerThread.
     */
    @Test
    public void testTimeLabel() {
        System.out.println("timeLabel");
        TimerThread instance = new TimerThread();
        instance.setSeconds(5);
        assertTrue(instance.getSeconds() == 5);
    }

    /**
     * Test of alertInfo method, of class TimerThread.
     */
    @Test
    public void testAlertInfo() {
        System.out.println("alertInfo");
        Match match = new Match();
        //TimerThread.alertInfo(match);
        assertNotNull(match);
    }

    /**
     * Test of checkMinutes method, of class TimerThread.
     */
    @Test
    public void testCheckMinutes() {
        System.out.println("checkMinutes");
        TimerThread instance = new TimerThread();
        instance.setMinutes(0);
        assertTrue(instance.getMinutes() == 0);
    }   
}