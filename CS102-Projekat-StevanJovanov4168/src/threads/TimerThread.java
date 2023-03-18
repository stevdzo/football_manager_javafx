/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import database.MatchDatabase;
import entities.Match;
import entities.Team;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import basic.Engine;

/**
 *
 * @author steva
 */
public class TimerThread implements Runnable {

    private Label labMin;
    private Label labSec;
    private Label scoreFirst;
    private Label scoreSecond;
    private int scoreIter1 = 0;
    private int scoreIter2 = 0;
    private int chanceIter1 = 0;
    private int chanceIter2 = 0;
    private int seconds = 0;
    private int minutes;
    private TextArea textArea;
    private Match match;
    private String str = "";
    private Team homeTeam;
    private Team awayTeam;
    private boolean play = true;

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public TimerThread() {
    }

    public TimerThread(Label labMin, Label labSec, Label scoreFirst, Label scoreSecond, TextArea textArea, Match match, Team homeTeam, Team awayTeam) {

        this.labMin = labMin;
        this.labSec = labSec;
        this.scoreFirst = scoreFirst;
        this.scoreSecond = scoreSecond;
        this.textArea = textArea;
        this.match = match;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    @Override
    public void run() {

        while (play) {

            try {
                Platform.runLater(() -> {

                    checkMinutes();
                    timeLabel();
                    checkTime();
                });
                Thread.sleep(15);

            } catch (InterruptedException ex) {

                ex.printStackTrace();
            }
        }
    }

    //ispisuje HALF-TIME na 45:00 i FULL-TIME na 90:00
    public void checkTime() {

        if (minutes == 44 && seconds == 59) {

            str += getMinutes() + 1 + ". min | HALF-TIME\n";
            textArea.setText(str);
            minutes = 45;
            seconds = 0;
        }

        //kraj meca kada minutes dodje do 90, izbacuje se prozorce sa rezultatom
        //i info meca se salje u bazu
        if (labMin.getText().equals("90")) {

            str += getMinutes() + ". min | FULL-TIME\n";
            textArea.setText(str);
            play = false;
            alertInfo(match);
            MatchDatabase.insertMatchStats(match);
        }
    }

    //posto je vreme utakmice prikazano u labelama u obliku 00:00, 
    //kada varijable seconds i minutes postanu dvocifrene, brisu se ove nule ispred
    public void timeLabel() {

        labSec.setText("0" + seconds);

        if (seconds > 9) {
            labSec.setText("" + seconds);
        }

        labMin.setText("0" + minutes);

        if (minutes > 9) {
            labMin.setText("" + minutes);
        }
    }

    //info na kraju meca
    public static void alertInfo(Match match) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Match");
        alert.setHeaderText("Full-Time");
        alert.setContentText("Match result: "
                + match.getHomeTeam()
                + " " + match.getHomeScore()
                + " : " + match.getAwayScore()
                + " " + match.getAwayTeam());
        alert.showAndWait();
    }

    //metoda koja svakog minuta poziva scored(...) metodu klase Engine, koja proverava 
    //da li je pao gol 
    public void mainEngine() {

        if (Engine.scored(match, "home") == 1) {
            match.setHomeScore(match.getHomeScore() + 1);
            str += minutes + ". min | Goal for "
                    + match.getHomeTeam().toString()
                    + "! Goal scorer: "
                    + Engine.getScorer(homeTeam.getRoster())
                    + "\n";
            textArea.setText(str);
            scoreIter1++;
            chanceIter1++;
            scoreFirst.setText(scoreIter1 + "");

        } else if (Engine.scored(match, "home") == 2) {

            str += getMinutes() + ". min | Missed opportunity for "
                    + match.getHomeTeam().toString()
                    + "! \n";
            textArea.setText(str);
            chanceIter1++;
        }
        if (Engine.scored(match, "away") == 1) {
            match.setAwayScore(match.getAwayScore() + 1);
            str += minutes + ". min | Goal for "
                    + match.getAwayTeam().toString()
                    + "! Goal scorer: "
                    + Engine.getScorer(awayTeam.getRoster())
                    + "\n";
            textArea.setText(str);
            scoreIter2++;
            chanceIter2++;
            scoreSecond.setText(scoreIter2 + "");
        } else if (Engine.scored(match, "away") == 2) {

            str += getMinutes() + ". min | Missed opportunity for "
                    + match.getAwayTeam().toString()
                    + "! \n";
            textArea.setText(str);
            chanceIter2++;
        }
        match.getStatistics().setHomeTeamChances(chanceIter1);
        match.getStatistics().setAwayTeamChances(chanceIter2);
    }

    public void checkMinutes() {

        seconds++;

        //kada je seconds == 59, var minutes se povecava tj. prelazi se u sledeci minut, 
        //svakog minuta proverava se, da li je pao gol, 
        //to zavisi od parametara tima koji stoje u TeamStats klasi
        //ukoliko padne gol, rezultat se menja
        if (seconds == 60) {

            mainEngine();
            seconds = 0;
            minutes++;
        }
    }
}
