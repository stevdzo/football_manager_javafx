/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author steva
 */
public class Statistics {

    private int homeTeamChances;
    private int awayTeamChances;

    public Statistics() {
        super();
        this.homeTeamChances = 0;
        this.awayTeamChances = 0;
    }

    public int getHomeTeamChances() {
        return homeTeamChances;
    }

    public void setHomeTeamChances(int homeTeamChances) {
        this.homeTeamChances = homeTeamChances;
    }

    public int getAwayTeamChances() {
        return awayTeamChances;
    }

    public void setAwayTeamChances(int awayTeamChances) {
        this.awayTeamChances = awayTeamChances;
    }
}
