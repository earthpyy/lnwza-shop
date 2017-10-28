
package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

/**
 *
 * @author pompr_000
 */
public class OrderDetailController {
    @FXML
    private TextField tf_orderno;

    @FXML
    private TextField tf_trackingno;

    @FXML
    private Button bt_update;

    @FXML
    private Button bt_cancle;

    @FXML
    private TableColumn<?, Integer> tb_id;

    @FXML
    private TableColumn<?, String> tb_name;

    @FXML
    private TableColumn<?, Integer> tb_qty;

    @FXML
    private TableColumn<?, Double> tb_price;

    @FXML
    private TableColumn<?, String> tb_date;

    @FXML
    private TableColumn<?, Integer> tb_status;
}
