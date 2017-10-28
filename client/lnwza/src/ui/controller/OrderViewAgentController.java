package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
/**
 *
 * @author Naremx
 */
public class OrderViewAgentController {
    
    @FXML
    private TextField tf_search;

    @FXML
    private TableColumn<?, Integer> tb_date;

    @FXML
    private TableColumn<?, Integer> tb_ordernum;

    @FXML
    private TableColumn<?, String> tb_status;
    
}
