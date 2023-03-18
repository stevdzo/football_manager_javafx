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
public class TeamStats {

    private int defence;
    private int midfield;
    private int attack;

    public TeamStats() {
    
    }
    
    public TeamStats(int defence, int midfield, int attack) {
        super();
        this.defence = defence;
        this.midfield = midfield;
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getMidfield() {
        return midfield;
    }

    public void setMidfield(int midfield) {
        this.midfield = midfield;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public String toString() {
        //return "(" + defence + ", " + midfield + ", " + attack + ")";
        return "Defence: " + defence + "\nMidfield: " + midfield + "\nAttack: " + attack;
    }
    
}
