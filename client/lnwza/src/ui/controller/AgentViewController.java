package ui.controller;

import application.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.list.AgentList;

/**
 *
 * @author SE-lnwza
 */
public class AgentViewController {

    @FXML
    private TableView<AgentList> tableview;

    @FXML
    private TableColumn<AgentList, Integer> tb_id;

    @FXML
    private TableColumn<AgentList, String> tb_first;

    @FXML
    private TableColumn<AgentList, String> tb_last;

    @FXML
    private TableColumn<AgentList, String> tb_address;

    @FXML
    private TableColumn<AgentList, String> tb_tel;
    
    private final Connection con = DatabaseConnection.getConnection();
    private ObservableList<AgentList> data;
    
    @FXML
    protected void initialize() {
        tb_id.setCellValueFactory(new PropertyValueFactory<>("agentId"));
        tb_first.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tb_last.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tb_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        tb_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        
        loadData();
    }
    
    protected void loadData() {
        data = FXCollections.observableArrayList();
        
        try {
            String sql = "SELECT * FROM `agents`";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                data.addAll(new AgentList(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            ps.close();
            rs.close();
            
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}