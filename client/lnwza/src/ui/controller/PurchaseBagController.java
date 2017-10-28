package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
/**
 *
 * @author Naremx
 */
public class PurchaseBagController {
    
    @FXML
    private TableColumn<?, ?> tb_photo;

    @FXML
    private TableColumn<?, String> tb_detail;

    @FXML
    private TableColumn<?, Double> tb_price;

    @FXML
    private TableColumn<?, Integer> tb_qty;

    @FXML
    private TableColumn<?, Double> tb_subtotal;

    @FXML
    private TableColumn<?, ?> tb_remove;
}
