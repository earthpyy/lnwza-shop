package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

/**
 *
 * @author pompr_000
 */
public class OrderViewOwnerController {
     @FXML
    private TextField tf_search;

    @FXML
    private TableColumn<?, String> tb_date;

    @FXML
    private TableColumn<?, Integer> tb_orderno;

    @FXML
    private TableColumn<?, String> tb_name;

    @FXML
    private TableColumn<?, Integer> tb_status;
}
