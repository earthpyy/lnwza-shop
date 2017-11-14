package ui.controller;

import application.entity.Transaction;
import application.handler.TransactionHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author SE-lnwza
 */
public class TransactionViewController {

    @FXML
    private ComboBox<?> cb_month;

    @FXML
    private ComboBox<?> cb_year;

    @FXML
    private Button bt_add;

    @FXML
    private Button bt_edit;

    @FXML
    private Button bt_delete;
    
    @FXML
    private TableView<Transaction> table_view;

    @FXML
    private TableColumn<Transaction, String> tb_date;

    @FXML
    private TableColumn<Transaction, String> tb_title;

    @FXML
    private TableColumn<Transaction, Double> tb_amount;

    @FXML
    private TableColumn<Transaction, Double> tb_total;
    
    @FXML
    protected void initialize() {
        tb_date.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
        tb_title.setCellValueFactory(new PropertyValueFactory<>("description"));
        tb_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
//        tb_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        TransactionHandler.load();
        ObservableList<Transaction> data = FXCollections.observableArrayList(TransactionHandler.getData());
        table_view.setItems(data);
    }
}
