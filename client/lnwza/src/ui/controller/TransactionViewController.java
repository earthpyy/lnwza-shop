package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;

/**
 *
 * @author pompr_000
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
    private TableColumn<?, String> tb_date;

    @FXML
    private TableColumn<?, ?> tb_title;

    @FXML
    private TableColumn<?, ?> tb_transac;

    @FXML
    private TableColumn<?, Double> tb_total;
}
