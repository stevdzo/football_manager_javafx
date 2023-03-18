/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.Team;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author steva
 */
public class TeamDatabase {

    private final static String url = "jdbc:mysql://localhost:3307/fmanager?useSSL=false&allowPublicKeyRetrieval=true";
    private final static String user = "root";
    private final static String password = "1234";

    public static boolean testConnection() {
        
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            return true;
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        return false;
    }

    public static List<Team> addTeams() {

        List<Team> listOfTeams = new ArrayList<>();

        String query = "SELECT * FROM fmanager.team;";

        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement st = con.prepareStatement(query);) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                listOfTeams.add(new Team(rs.getString("team_name"), 
                        rs.getString("stadium"), 
                        rs.getInt("year_of_establishment"), 
                        rs.getString("manager"),
                        rs.getString("team_crest"),
                        rs.getInt("team_id")
                ));
            }
            rs.close();

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return listOfTeams;
    }

    public static TreeSet<String> addTeamNames() {

        TreeSet<String> teamNames = new TreeSet<>();

        String query = "SELECT team_name FROM fmanager.team;";

        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement st = con.prepareStatement(query);) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                teamNames.add(rs.getString("team_name"));
            }
            rs.close();

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return teamNames;
    }
}
