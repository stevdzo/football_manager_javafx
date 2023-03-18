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
public class Player extends Person {

    private String country;
    private String position;
    private int skill;
    private double form;
    private int PTeamId;
    private Team team;

    public Player() {
    }

    public Player(String name, String position, String country, int skill, double form, Team team) {

        super(name);
        this.position = position;
        this.country = country;
        this.skill = skill;
        this.form = form;
        this.team = team;
    }

    public Player(String name, String position, String country, int skill, double form, int PTeamId) {

        super(name);
        this.position = position;
        this.country = country;
        this.skill = skill;
        this.form = form;
        this.PTeamId = PTeamId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        if (skill > 9 || skill < 5) {
            throw new IllegalArgumentException("Invalid skill rating!");
        } else {
            this.skill = skill;
        }
    }

    public double getForm() {
        return form;
    }

    public void setForm(double form) {
        if (form > 1 || form < 0.8) {
            throw new IllegalArgumentException("Invalid form!");
        } else {
            this.form = form;
        }
    }

    //mnozi skill i form i daje konacni rejting
    public double getPerformance() {
        return this.getForm() * this.getSkill();
    }

    public int getPTeamId() {
        return PTeamId;
    }

    public void setPTeamId(int PTeamId) {
        if (PTeamId > 20 || PTeamId < 1) {
            throw new IllegalArgumentException("Invalid team ID");
        } else {
            this.PTeamId = PTeamId;
        }
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
