/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author steva
 */
public class PlayerDatabase {
    
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
    
    public static List<Player> loadPlayers(int id) {
        
        List<Player> listOfPlayers = new ArrayList<>();
        
        String query = "SELECT * FROM fmanager.player WHERE p_team_id = ?;";
        
        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement st = con.prepareStatement(query);) {
                      
            st.setInt(1, id);
            
            ResultSet rs = st.executeQuery();
                      
            while (rs.next()) {
                
                listOfPlayers.add(new Player(rs.getString("name"),
                        rs.getString("position"),
                        rs.getString("country"),
                        rs.getInt("skill"),
                        rs.getDouble("form"),
                        rs.getInt("p_team_id")
                ));
            }
            rs.close();
                       
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        } catch (IllegalArgumentException i){
        
            System.out.println(i.getMessage());
        }
        return listOfPlayers;
    }
}
