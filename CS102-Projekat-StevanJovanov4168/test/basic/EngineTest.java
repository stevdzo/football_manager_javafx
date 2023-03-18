/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import basic.Engine;
import entities.Match;
import entities.Player;
import entities.Team;
import entities.TeamStats;
import java.util.ArrayList;
import java.util.List;
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
public class EngineTest {

    List<Player> roster = new ArrayList<>();

    public EngineTest() {
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
     * Test of scored method, of class Engine.
     */
    @Test
    public void testScored() {
        System.out.println("scored");
        Match match = new Match(new Team("Arsenal", new TeamStats(1, 1, 1)),
                new Team("Chelsea", new TeamStats(1, 1, 1)), 41234);
        String side = "home";
        int result = Engine.scored(match, side);
        assertEquals(0, result);
    }

    /**
     * Test of calculateStatsFor method, of class Engine.
     */
    @Test
    public void testCalculateStatsFor() {
        System.out.println("calculateStatsFor");
        Team team = new Team("Arsenal", new TeamStats(5, 1, 1));
        int minDefence = 4;
        assertTrue(team.getTeamStats().getDefence() > minDefence);
    }

    /**
     * Test of getScorer method, of class Engine.
     */
    @Test
    public void testPList() {
        System.out.println("player added");
        Player p = new Player("Milan Milanovic", "Defender", "Serbia", 9, 0.99, 2);
        roster.add(p);
        assertEquals(1, roster.size());
    }
}
