/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.Match;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author steva
 */
public class MatchDatabase {

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

    public static boolean insertMatchStats(Match match) {

        String query = "INSERT INTO `fmanager`.`match` (`home_team`, `home_team_score`, `away_team`, `away_team_score`, `m_stadium`, `referee`, `attendance`) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement st = con.prepareStatement(query);) {

            st.setString(1, match.getHomeTeam().getName());
            st.setInt(2, match.getHomeScore());
            st.setString(3, match.getAwayTeam().getName());
            st.setInt(4, match.getAwayScore());
            st.setString(5, match.getHomeTeam().getStadium());
            st.setString(6, match.getReferee().getName());
            st.setInt(7, match.getAttendance());

            st.executeUpdate();

            return true;

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return false;
    }
}
