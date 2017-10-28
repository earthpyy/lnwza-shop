package application.handler;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

import application.DatabaseConnection;
import application.DatabaseConnectionMySQL;
import application.list.Agent;

/**
 *
 * @author SE-lnwza
 */
public class AgentHandler {
    
    private static ArrayList<Agent> agents;
    private static final Connection con = DatabaseConnectionMySQL.getConnection();
    
    public static ArrayList<Agent> getData() {
        return agents;
    }
    
    public static void load() {
        agents = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM `agents`";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                agents.add(new Agent(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            ps.close();
            rs.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
//    public static void load() {
//    }
    
    public static void add(String firstName, String lastName, String address, String tel) {
        // TODO: next sprint
    }
    
    public static void delete(int agentId) {
        // TODO: next sprint
    }
    
}
