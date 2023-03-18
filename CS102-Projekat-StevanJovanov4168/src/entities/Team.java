/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

/**
 *
 * @author steva
 */
public class Team {

    private String name;
    private String stadium;
    private int yearOfEstablishment;
    private String crest;
    private String manager;
    private TeamStats teamStats;
    private List<Player> roster;
    private int teamId;

    public Team() {

    }

    public Team(String name) {

        this.name = name;
    }

    public Team(String name, TeamStats teamStats) {

        this.name = name;
        this.teamStats = teamStats;
    }

    public Team(String name, String stadium, int yearOfEstablishment, String manager, String crest, int teamId) {

        this.name = name;
        this.stadium = stadium;
        this.yearOfEstablishment = yearOfEstablishment;
        this.manager = manager;
        this.crest = crest;
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadium() {
        return stadium;
    }

    public int getYearOfEstablishment() {
        return yearOfEstablishment;
    }

    public void setYearOfEstablishment(int yearOfEstablishment) {
        this.yearOfEstablishment = yearOfEstablishment;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getCrest() {
        return crest;
    }

    public void setCrest(String crest) {
        this.crest = crest;
    }

    public TeamStats getTeamStats() {
        return teamStats;
    }

    public void setTeamStats(TeamStats teamStats) {
        this.teamStats = teamStats;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public List<Player> getRoster() {
        return roster;
    }

    public void setRoster(List<Player> roster) {
        this.roster = roster;
    }

    @Override
    public String toString() {
        return name;
    }
}