/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import entities.Match;
import entities.Player;
import entities.TeamStats;
import entities.Statistics;
import entities.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author steva
 */
public class Engine {

    //metoda koja proverava da li je stvorena sansa, ukoliko je stvorena, da li je pao gol. Vraca 0, 1 ili 2. 
    public static int scored(Match match, String side) {

        int cm = 4; // koeficijent raspodela sansi u odnosu na sredine timova (1 - 4)
        int ca = 4; // koeficijent realizacije sansi u golove (1 - 4)

        TeamStats attackingTeam;
        TeamStats defendingTeam;

        Random rnd = new Random();

        if (side.equals("home")) {
            attackingTeam = match.getHomeTeam().getTeamStats();
            defendingTeam = match.getAwayTeam().getTeamStats();
        } else {
            attackingTeam = match.getAwayTeam().getTeamStats();
            defendingTeam = match.getHomeTeam().getTeamStats();
        }
        //distribucija sansi u odnosu na jacinu sredine - jaci tim ce imati vise sansi
        int mid = (attackingTeam.getMidfield() / (attackingTeam.getMidfield() - (attackingTeam.getMidfield() - defendingTeam.getMidfield()) / cm)
                * (attackingTeam.getDefence() * 2 + attackingTeam.getMidfield() * 3 + attackingTeam.getAttack() * 2));

        //distribucija sansi u odnosu napada prema odbrani
        int att = attackingTeam.getAttack() * (ca + 5) / (attackingTeam.getAttack() - (attackingTeam.getAttack() - defendingTeam.getDefence()) / ca);
        if (att > 10) att = 10; //ukoliko je att parametar veci od 10, ogranicavamo ga na 10 jer je to max dozvoljena vrednost u kalkulaciji sansi    
        Statistics tempStats = match.getStatistics();
        //da li je stvorena sansa za gol (veci mid parametar znaci manji opseg random broja i vecu sansu da iskaz bude tacan)
        if (rnd.nextInt(80 - mid) + 1 < attackingTeam.getMidfield() / 2) {

            match.setStatistics(tempStats);
            //provera da li je realizovana sansa (veci att parametar znaci manji opseg random broja i vecu sansu da iskaz bude tacan)
            if (rnd.nextInt(46 - att * ca) + 1 < attackingTeam.getAttack() / 2) {
                //vraca 1 ukoliko je realizovana sansa
                return 1;
            } else {
                //vraca 2 ukoliko nije realizovana sansa
                return 2;
            }
        }
        //vraca 0 ukoliko se nije stvorila sansa
        return 0;
    }

    //podrazumeva da objekat Team ima definisan atribut rooster koji sadrzi tacno 11 igraca
    //vraca jacinu tima u odnosu na karakteristike igraca (skill i forma)
    public static TeamStats calculateStatsFor(Team team) {

        TeamStats teamStats = new TeamStats();

        double tempDef = 0;
        double tempMid = 0;
        double tempFor = 0;

        int countDef = 0;
        int countMid = 0;
        int countFor = 0;

        //prolazi kroz ceo spisak igraca
        for (Player player : team.getRoster()) {

            //gleda poziciju svakog igraca
            switch (player.getPosition()) {
                //golmana racuna u prosek odbrane sa znacajnim udelom
                case "Goalkeeper":

                    tempDef += player.getPerformance() * 5;
                    countDef += 5;
                    break;

                case "Defender":

                    tempDef += player.getPerformance();
                    countDef++;
                    break;

                case "Midfielder":

                    tempMid += player.getPerformance();
                    countMid++;
                    break;

                case "Forward":

                    tempFor += player.getPerformance();
                    countFor++;
                    break;

                default:

                    System.out.println("Error?");
                    break;
            }
        }
        //kalkulacija - ako ima manje od 2 igraca na nekoj poziciji, stats te pozicije se umanjuje za 1
        if (countFor < 2) {
            teamStats.setAttack((int) Math.round(tempFor / countFor) - 1);
        } else {
            teamStats.setAttack((int) Math.round(tempFor / countFor));
        }
        if (countMid < 2) {
            teamStats.setMidfield((int) Math.round(tempMid / countMid) - 1);
        } else {
            teamStats.setMidfield((int) Math.round(tempMid / countMid));
        }
        if (countDef < 7) {
            teamStats.setDefence((int) Math.round(tempDef / countDef) - 1);
        } else {
            teamStats.setDefence((int) Math.round(tempDef / countDef));
        }
        return teamStats;
    }

    //vraca igraca koji je postigao gol. ulazni parametar je Team.getRooster(), izlazni Player  
    public static Player getScorer(List<Player> roster) {

        Random rnd = new Random();
        String position;
        List<Player> goalCandidates = new ArrayList<>();
        Player pl = new Player();
        //odlucuje se koja pozicija igraca je postigla gol. Najvecu sansu imaju napadaci (5/8), veznjaci (1/4), odbrambeni (1/8)
        switch (rnd.nextInt(9)) {
            case 0:
                position = "Defender";
                break;
            case 1:
                position = "Midfielder";
                break;
            case 2:
                position = "Midfielder";
                break;
            default:
                position = "Forward";
                break;
        }
        for (Player player : roster) {
            //ukoliko je igrac na poziciji koja je postigla gol, stavljamo ga u listu igraca koji su kandidati za strelca
            if (player.getPosition().equals(position)) {
                goalCandidates.add(player);
            }
        }
        if (!goalCandidates.isEmpty()) {
            //biramo strelca slucajnim izborom igraca koji su clanovi liste kandidata 
            pl = goalCandidates.get(rnd.nextInt(goalCandidates.size()));
        }
        return pl;
    }
}
