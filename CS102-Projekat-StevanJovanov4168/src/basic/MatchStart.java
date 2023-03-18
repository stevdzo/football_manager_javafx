/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import database.PlayerDatabase;
import database.TeamDatabase;
import threads.TimerThread;
import entities.Match;
import entities.Player;
import entities.Referee;
import entities.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import threads.DownloadThread;

/**
 *
 * @author steva
 */
public class MatchStart extends Application {

    Image img1, img2;
    ImageView iv1 = new ImageView(img1);
    ImageView iv2 = new ImageView(img2);
    ComboBox<String> comboFirst = new ComboBox();
    ComboBox<String> comboSecond = new ComboBox();
    Team homeTeam = new Team();
    Team awayTeam = new Team();
    Match match;
    Thread thread;
    Random r = new Random();
    TreeSet<String> teams = new TreeSet<>();
    List<Player> homePlayers = new ArrayList<>();
    List<Player> awayPlayers = new ArrayList<>();
    List<Referee> listOfReferees = new ArrayList<>();
    Referee referee = new Referee();
    TableView homeTableView = new TableView();
    TableView awayTableView = new TableView();
    Stage homeStage = new Stage();
    Stage awayStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #C3DAC3");

        HBox hbHome = new HBox(20);
        HBox hbAway = new HBox(20);
        HBox hbMiddle = new HBox(20);
        HBox hbBottom = new HBox(20);

        root.setCenter(hbMiddle);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        hbMiddle.getChildren().add(gridPane);
        hbMiddle.setAlignment(Pos.CENTER);

        Button btnStart = new Button("Start");
        Button btnInfo = new Button("Match Info");
        Button btnStats = new Button("Match Stats");
        Button btnConfirm = new Button("Confirm");
        //dok korisnik ne odabere ekipu, ne moze da klikne na Match Info, i Confirm dugme
        btnInfo.setDisable(true);
        btnConfirm.setDisable(true);

        hbBottom.getChildren().addAll(btnInfo, btnConfirm, btnStart, btnStats);
        hbBottom.setAlignment(Pos.CENTER);

        hbHome.getChildren().addAll(comboFirst, iv1);
        hbHome.setAlignment(Pos.CENTER);

        hbAway.getChildren().addAll(iv2, comboSecond);
        hbAway.setAlignment(Pos.CENTER);

        teams = TeamDatabase.addTeamNames();

        comboFirst.getItems().addAll(teams);
        comboSecond.getItems().addAll(teams);

        comboSecond.setDisable(true);

        listOfReferees = Match.addReferees();

        Label lab1 = new Label("Info");
        lab1.setTextFill(Color.web("#E29930"));
        lab1.setStyle("-fx-font-weight: bold");
        Label lab2 = new Label("Info o drugom timu");
        lab2.setTextFill(Color.web("#E29930"));
        lab2.setStyle("-fx-font-weight: bold");

        TextArea textArea = new TextArea("Welcome to Football Manager Simulator 2020\nPlease select your team.");
        textArea.setPrefRowCount(15);
        textArea.setPrefColumnCount(50);
        textArea.setEditable(false);
        textArea.setStyle("-fx-font-weight: bold");

        Label scoreFirst = new Label("0");
        Label dots1 = new Label(" : ");
        Label scoreSecond = new Label("0");

        scoreFirst.setId("label-score");
        dots1.setId("label-score");
        scoreSecond.setId("label-score");

        Label labMin = new Label("00");
        Label dots2 = new Label(" : ");
        Label labSec = new Label("00");

        labMin.setId("label-time");
        dots2.setId("label-time");
        labSec.setId("label-time");

        Label teamName1 = new Label("Team name: ");
        Label teamName2 = new Label("Team name: ");
        Label showTeamName1 = new Label("");
        Label showTeamName2 = new Label("");
        Label stadium1 = new Label("Team stadium: ");
        Label stadium2 = new Label("Team stadium: ");
        Label showStadium1 = new Label("");
        Label showStadium2 = new Label("");
        Label manager1 = new Label("Team manager: ");
        Label manager2 = new Label("Team manager: ");
        Label showManager1 = new Label("");
        Label showManager2 = new Label("");
        Label year1 = new Label("Foundation date: ");
        Label year2 = new Label("Foundation date: ");
        Label showYear1 = new Label("");
        Label showYear2 = new Label("");
        Label homeTeamStats = new Label("Team stats: ");
        Label awayTeamStats = new Label("Team stats: ");
        Label showHomeTeamStats = new Label("");
        Label showAwayTeamStats = new Label("");
        Label homeTeamFormation = new Label("Team formation: ");
        Label awayTeamFormation = new Label("Team formation: ");
        Label showHomeTeamFormation = new Label("");
        Label showAwayTeamFormation = new Label("");

        teamName1.setId("label-info");
        teamName2.setId("label-info");
        showTeamName1.setId("label-info");
        showTeamName2.setId("label-info");
        stadium1.setId("label-info");
        stadium2.setId("label-info");
        showStadium1.setId("label-info");
        showStadium2.setId("label-info");
        manager1.setId("label-info");
        manager2.setId("label-info");
        showManager1.setId("label-info");
        showManager2.setId("label-info");
        year1.setId("label-info");
        year2.setId("label-info");
        showYear1.setId("label-info");
        showYear2.setId("label-info");
        homeTeamStats.setId("label-info");
        awayTeamStats.setId("label-info");
        showHomeTeamStats.setId("label-info");
        showAwayTeamStats.setId("label-info");
        homeTeamFormation.setId("label-info");
        awayTeamFormation.setId("label-info");
        showHomeTeamFormation.setId("label-info");
        showAwayTeamFormation.setId("label-info");

        showHomeTeamFormation.setStyle("-fx-underline: true;");
        showAwayTeamFormation.setStyle("-fx-underline: true;");

        GridPane gridInfoFirst = new GridPane();
        gridInfoFirst.add(teamName1, 0, 0);
        gridInfoFirst.add(showTeamName1, 1, 0);
        gridInfoFirst.add(stadium1, 0, 1);
        gridInfoFirst.add(showStadium1, 1, 1);
        gridInfoFirst.add(manager1, 0, 2);
        gridInfoFirst.add(showManager1, 1, 2);
        gridInfoFirst.add(year1, 0, 3);
        gridInfoFirst.add(showYear1, 1, 3);
        gridInfoFirst.add(homeTeamStats, 0, 4);
        gridInfoFirst.add(showHomeTeamStats, 1, 4);
        gridInfoFirst.add(homeTeamFormation, 0, 5);
        gridInfoFirst.add(showHomeTeamFormation, 1, 5);
        gridInfoFirst.setPrefWidth(600);
        gridInfoFirst.setPrefHeight(150);
        gridInfoFirst.setVgap(20);
        gridInfoFirst.setId("pane-style");

        gridInfoFirst.setAlignment(Pos.CENTER);
        gridInfoFirst.setHalignment(teamName1, HPos.LEFT);
        gridInfoFirst.setHalignment(stadium1, HPos.LEFT);
        gridInfoFirst.setHalignment(manager1, HPos.LEFT);
        gridInfoFirst.setHalignment(showManager1, HPos.LEFT);
        gridInfoFirst.setHalignment(homeTeamStats, HPos.LEFT);
        gridInfoFirst.setHalignment(homeTeamFormation, HPos.LEFT);

        gridInfoFirst.setHalignment(showTeamName1, HPos.LEFT);
        gridInfoFirst.setHalignment(showStadium1, HPos.LEFT);
        gridInfoFirst.setHalignment(showManager1, HPos.LEFT);
        gridInfoFirst.setHalignment(showHomeTeamStats, HPos.LEFT);
        gridInfoFirst.setHalignment(showHomeTeamFormation, HPos.LEFT);

        GridPane gridInfoSecond = new GridPane();
        gridInfoSecond.add(teamName2, 0, 0);
        gridInfoSecond.add(showTeamName2, 1, 0);
        gridInfoSecond.add(stadium2, 0, 1);
        gridInfoSecond.add(showStadium2, 1, 1);
        gridInfoSecond.add(manager2, 0, 2);
        gridInfoSecond.add(showManager2, 1, 2);
        gridInfoSecond.add(year2, 0, 3);
        gridInfoSecond.add(showYear2, 1, 3);
        gridInfoSecond.add(awayTeamStats, 0, 4);
        gridInfoSecond.add(showAwayTeamStats, 1, 4);
        gridInfoSecond.add(awayTeamFormation, 0, 5);
        gridInfoSecond.add(showAwayTeamFormation, 1, 5);
        gridInfoSecond.setPrefWidth(600);
        gridInfoSecond.setPrefHeight(150);
        gridInfoSecond.setVgap(20);
        gridInfoSecond.setId("pane-style");

        gridInfoSecond.setAlignment(Pos.CENTER);
        gridInfoSecond.setHalignment(teamName2, HPos.LEFT);
        gridInfoSecond.setHalignment(manager2, HPos.LEFT);
        gridInfoSecond.setHalignment(stadium2, HPos.LEFT);
        gridInfoSecond.setHalignment(awayTeamStats, HPos.LEFT);
        gridInfoSecond.setHalignment(awayTeamFormation, HPos.LEFT);

        gridInfoSecond.setHalignment(showTeamName2, HPos.LEFT);
        gridInfoSecond.setHalignment(showStadium2, HPos.LEFT);
        gridInfoSecond.setHalignment(showManager2, HPos.LEFT);
        gridInfoSecond.setHalignment(showAwayTeamStats, HPos.LEFT);
        gridInfoSecond.setHalignment(showAwayTeamFormation, HPos.LEFT);

        //bira se domaci tim, 
        //nakon odabira popunjavaju se osnovne informacije o timu
        comboFirst.setOnAction(e -> {

            homeTeam = new Team();
            for (Team t : TeamDatabase.addTeams()) {

                if (comboFirst.getSelectionModel().getSelectedItem().equals(t.getName())) {

                    homeTeam = t;

                    //postavlja se grb domaceg tima
                    img1 = new Image(t.getCrest(), 100, 100, true, true);
                    iv1.setImage(img1);

                    homePlayers = PlayerDatabase.loadPlayers(t.getTeamId());
                    t.setRoster(homePlayers);
                    homeTeam.setTeamStats(Engine.calculateStatsFor(t));

                    homeTableView.getItems().clear();
                    homeTableView.getItems().addAll(homePlayers);

                    homeStage.setTitle(t.getName());

                    showTeamName1.setText(t.getName());
                    showStadium1.setText(t.getStadium());
                    showManager1.setText(t.getManager());
                    showYear1.setText(t.getYearOfEstablishment() + ".");
                    showHomeTeamStats.setText(t.getTeamStats().toString());
                    showHomeTeamFormation.setText(String.valueOf(formation(homePlayers, "Defender")
                            + " | " + String.valueOf(formation(homePlayers, "Midfielder"))
                            + " | " + String.valueOf(formation(homePlayers, "Forward"))));

                    btnConfirm.setDisable(false);
                    
                    textArea.setText("Click confirm to continue");
                }
            }
        });
        updateTable(homeTableView);
        //bira se drugi tim
        comboSecond.setOnAction(o -> {

            awayTeam = new Team();
            for (Team t : TeamDatabase.addTeams()) {

                if (comboSecond.getSelectionModel().getSelectedItem().equals(t.getName())) {

                    awayTeam = t;

                    //postavlja se grb gostujuceg tima
                    img2 = new Image(t.getCrest(), 100, 100, true, true);
                    iv2.setImage(img2);

                    awayPlayers = PlayerDatabase.loadPlayers(t.getTeamId());
                    t.setRoster(awayPlayers);
                    awayTeam.setTeamStats(Engine.calculateStatsFor(t));

                    awayTableView.getItems().clear();
                    awayTableView.getItems().addAll(awayPlayers);

                    awayStage.setTitle(t.getName());

                    showTeamName2.setText(t.getName());
                    showStadium2.setText(t.getStadium());
                    showManager2.setText(t.getManager());
                    showYear2.setText(t.getYearOfEstablishment() + ".");
                    showAwayTeamStats.setText(t.getTeamStats().toString());
                    showAwayTeamFormation.setText(String.valueOf(formation(awayPlayers, "Defender")
                            + " | " + String.valueOf(formation(awayPlayers, "Midfielder"))
                            + " | " + String.valueOf(formation(awayPlayers, "Forward"))));

                    textArea.setText(homeTeam.getName() + " vs " + awayTeam.getName() + "\nClick start to start the match");
                }
            }
        });
        updateTable(awayTableView);

        //mec pocinje
        btnStart.setOnAction(n -> {

            if (comboFirst.getSelectionModel().getSelectedItem() == null || comboSecond.getSelectionModel().getSelectedItem() == null) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error while starting the match...");
                alert.setContentText("Please select both teams.");
                alert.showAndWait();
            } else {

                btnInfo.setDisable(false);
                //kreira se objekat klase Match, dodaju se odabrani timovi i random kapacitet stadiona 
                match = new Match(homeTeam, awayTeam, r.nextInt(32384) + 42495);

                TimerThread timerThread = new TimerThread(labMin, labSec, scoreFirst, scoreSecond, textArea, match, homeTeam, awayTeam);
                this.thread = new Thread(timerThread);
                //mec pocinje
                this.thread.start();
                textArea.setText("The match has started!");
                comboFirst.setDisable(true);
                comboSecond.setDisable(true);
                btnStart.setDisable(true);
                btnStats.setDisable(false);

                referee = chooseReferee(listOfReferees);
            }
        });

        //nit koja skida informacije o temperaturi i danasnji datum
        DownloadThread weatherThread = new DownloadThread();
        Thread thread = new Thread(weatherThread);
        thread.start();

        btnInfo.setOnAction(e -> {

            matchInfo(weatherThread);
        });

        //klikom na ovo dugme, zakljucava se izbor domaceg tima i dobija se random protivnik
        btnConfirm.setOnAction(e -> {

            btnConfirm.setDisable(true);
            comboFirst.setDisable(true);
            comboSecond.getSelectionModel().select(r.nextInt(teams.size()));
            if (comboFirst.getSelectionModel().getSelectedItem().equals(comboSecond.getSelectionModel().getSelectedItem())) {

                comboSecond.getSelectionModel().select(r.nextInt(teams.size()));
            } else {
                comboSecond.setDisable(true);
            }
        });

        //klikom na ove dve labele prikazuju se startnih 11 igraca timova koji igraju
        showHomeTeamFormation.setOnMouseClicked(e -> {

            homeStage.close();
            newScene(homeTableView, homeStage);
        });

        showAwayTeamFormation.setOnMouseClicked(e -> {

            awayStage.close();
            newScene(awayTableView, awayStage);
        });

        btnStats.setDisable(true);
        //poziva se metoda koja prikazuje info meca
        btnStats.setOnAction(e -> {

            matchStats();
        });

        HBox hb1 = new HBox();
        hb1.setAlignment(Pos.CENTER);
        hb1.getChildren().addAll(scoreFirst, dots1, scoreSecond);

        HBox hb2 = new HBox();
        hb2.setAlignment(Pos.CENTER);
        hb2.getChildren().addAll(labMin, dots2, labSec);

        VBox vb1 = new VBox(20);
        vb1.getChildren().addAll(hb1, hb2);
        vb1.setId("pane-style");

        gridPane.add(hbHome, 0, 1);
        gridPane.add(hbAway, 2, 1);
        gridPane.add(gridInfoFirst, 0, 2);
        gridPane.add(vb1, 1, 1);
        gridPane.add(gridInfoSecond, 2, 2);
        gridPane.add(textArea, 1, 2);
        gridPane.add(hbBottom, 1, 3);
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.setHalignment(hbHome, HPos.CENTER);
        gridPane.setHalignment(hbAway, HPos.CENTER);
        gridPane.setHalignment(hbBottom, HPos.CENTER);

        Scene scene = new Scene(root, 1920, 1056);
        scene.getStylesheets().add("ProjectStyle.css");

        primaryStage.setTitle("Football Manager Simulator 2020");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.getIcons().add(new Image("FM20.png"));
    }

    //broji igrace tima po pozicijama i predstavlja labelu u obliku x | y | z
    public static int formation(List<Player> list, String str) {

        int count = 0;

        for (Player p : list) {

            if (p.getPosition().equals(str)) {
                count++;
            }
        }
        return count;
    }

    //bira random sudiju iz liste
    public Referee chooseReferee(List<Referee> list) {

        Referee ref = list.get(r.nextInt(list.size()));
        match.setReferee(ref);

        return ref;
    }

    //prozor koji iskace na klik dugmeta, prikazuje neki info o mecu
    public void matchInfo(DownloadThread weatherThread) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Match info");
        alert.setContentText("Referee: " + referee + "\nStadium: " + homeTeam.getStadium() + "\nAttendance: "
                + match.getAttendance() + "\n"
                + weatherThread.getStrWeather() + "\nToday's date: "
                + weatherThread.getStrDate()
        );
        alert.showAndWait();

    }

    //prikazuje broj golova i sansi obe ekipe
    public void matchStats() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Match stats");
        alert.setContentText("Chances for " + match.getHomeTeam() + ": " + match.getStatistics().getHomeTeamChances()
                + "\nChances for " + match.getAwayTeam() + ": " + match.getStatistics().getAwayTeamChances()
                + "\nGoals for " + match.getHomeTeam() + ": " + match.getHomeScore()
                + "\nGoals for " + match.getAwayTeam() + ": " + match.getAwayScore()
        );
        alert.showAndWait();
    }

    //popunjava tabelu igracima izabranog tima
    public void updateTable(TableView tableView) {

        TableColumn<String, Player> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<String, Player> column2 = new TableColumn<>("Country");
        column2.setCellValueFactory(new PropertyValueFactory<>("Country"));

        TableColumn<String, Player> column3 = new TableColumn<>("Position");
        column3.setCellValueFactory(new PropertyValueFactory<>("Position"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
    }

    //nova scena za prikaz tabele startnih 11
    public void newScene(TableView tableView, Stage stage) {

        StackPane sp = new StackPane();

        sp.getChildren().add(tableView);
        Scene homeScene = new Scene(sp, 500, 500);

        stage.setScene(homeScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
