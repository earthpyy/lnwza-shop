package ui.controller;

import application.DatabaseConnection;
import application.handler.AgentHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import application.list.Agent;

/**
 *
 * @author SE-lnwza
 */
public class AgentViewController {

    @FXML
    private TableView<Agent> tableview;

    @FXML
    private TableColumn<Agent, Integer> tb_id;

    @FXML
    private TableColumn<Agent, String> tb_first;

    @FXML
    private TableColumn<Agent, String> tb_last;

    @FXML
    private TableColumn<Agent, String> tb_address;

    @FXML
    private TableColumn<Agent, String> tb_tel;
    
    private ObservableList<Agent> data;
    
    @FXML
    protected void initialize() {
        tb_id.setCellValueFactory(new PropertyValueFactory<>("agentId"));
        tb_first.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tb_last.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tb_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        tb_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        
        AgentHandler.load();
        data = FXCollections.observableArrayList(AgentHandler.getData());
        tableview.setItems(data);
    }

}