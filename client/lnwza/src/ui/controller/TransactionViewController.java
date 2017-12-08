package ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import application.entity.Transaction;
import application.handler.TransactionHandler;
import application.MyDate;
import java.text.DecimalFormat;
import javafx.scene.control.TableCell;

/**
 *
 * @author SE-lnwza
 */
public class TransactionViewController {

    @FXML
    private ComboBox<String> cb_month;

    @FXML
    private ComboBox<Integer> cb_year;

    @FXML
    private Button bt_add;

    @FXML
    private Button bt_edit;

    @FXML
    private Button bt_delete;

    @FXML
    private Button bt_go;
    
    @FXML
    private TableView<Transaction> table_view;

    @FXML
    private TableColumn<Transaction, String> tb_date;
    
    @FXML
    private TableColumn<Transaction, String> tb_time;

    @FXML
    private TableColumn<Transaction, String> tb_title;

    @FXML
    private TableColumn<Transaction, Double> tb_income;

    @FXML
    private TableColumn<Transaction, Double> tb_outcome;

    @FXML
    private TableColumn<Transaction, Double> tb_total;
    
    @FXML
    protected void initialize() {
        // sprint 1
        cb_month.getItems().setAll(MyDate.MONTH);
        cb_year.getItems().setAll(MyDate.YEAR);
        cb_month.getSelectionModel().select(MyDate.getCurrentMonthIndex());
        cb_year.getSelectionModel().selectLast();
        
        tb_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tb_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        
        tb_title.setCellValueFactory(new PropertyValueFactory<>("description"));
        tb_title.setCellFactory((TableColumn<Transaction, String> col) ->
            new TableCell<Transaction, String>() {
                @Override
                public void updateItem(String des, boolean empty) {
                    super.updateItem(des, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        Transaction tran = getTableView().getItems().get(getIndex());
                        switch (tran.getType()) {
                            case ORDER:
                                setText("[AUTO] Sold order #" + des);
                                break;
                            case STOCK:
                                setText("[AUTO] Update stock of product #" + des);
                                break;
                            default:
                                setText(des);
                                break;
                        }
                    }
                }
            }
        );
        
        tb_income.setCellValueFactory(new PropertyValueFactory<>("income"));
        tb_outcome.setCellValueFactory(new PropertyValueFactory<>("outcome"));
        tb_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        tb_total.setCellFactory((TableColumn<Transaction, Double> col) ->
            new TableCell<Transaction, Double>() {
                @Override
                public void updateItem(Double total, boolean empty) {
                    super.updateItem(total, empty);
                    if (total == null || empty) {
                        setText(null);
                    } else {
                        total = Math.round(total * 100.0) / 100.0;
                        setText(total.toString());
                    }
                }
            }
        );
        
        TransactionHandler.load();
        ObservableList<Transaction> data = FXCollections.observableArrayList(TransactionHandler.getDataFromCurrentMonth());
        table_view.setItems(data);
    }
}
