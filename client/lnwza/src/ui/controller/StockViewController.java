package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

/**
 *
 * @author SE-lnwza
 */
public class StockViewController {
    
    @FXML
    private Button bt_add;

    @FXML
    private Button bt_edit;

    @FXML
    private Button bt_update;

    @FXML
    private Button bt_delete;

    @FXML
    private TableColumn<?, ?> tb_checkbox;

    @FXML
    private TableColumn<?, Integer> tb_id;

    @FXML
    private TableColumn<?, ?> tb_photo;

    @FXML
    private TableColumn<?, String> tb_name;

    @FXML
    private TableColumn<?, String> tb_color;

    @FXML
    private TableColumn<?, Integer> tb_type;

    @FXML
    private TableColumn<?, Integer> tb_qty;

    @FXML
    private TableColumn<?, Double> tb_price;
    
     @FXML
    private Label lb_summary;
}
