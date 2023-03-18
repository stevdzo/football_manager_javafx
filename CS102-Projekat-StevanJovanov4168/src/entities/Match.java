/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author steva
 */
public class Match {

    private Team homeTeam;
    private Team awayTeam;
    private int attendance;
    private int homeScore;
    private int awayScore;
    private int matchTime;
    private Statistics statistics;
    private Referee referee;

    public Match() {
    }

    public Match(Team homeTeam, Team awayTeam, int attendance) {
        super();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.attendance = attendance;
        this.homeScore = 0;
        this.awayScore = 0;
        this.matchTime = 0;
        this.statistics = new Statistics();
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public int getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(int matchTime) {
        this.matchTime = matchTime;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public static final List<Referee> addReferees() {

        List<Referee> listOfReferees = new ArrayList<>();

        listOfReferees.add(new Referee("Martin Atkinson"));
        listOfReferees.add(new Referee("Mike Dean"));
        listOfReferees.add(new Referee("Michael Oliver"));
        listOfReferees.add(new Referee("Anthony Taylor"));
        listOfReferees.add(new Referee("Kevin Frend"));
        listOfReferees.add(new Referee("Jonathan Moss"));
        listOfReferees.add(new Referee("Andre Marriner"));
        listOfReferees.add(new Referee("Lee Mason"));

        return listOfReferees;
    }
}
