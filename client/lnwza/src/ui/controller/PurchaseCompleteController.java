package ui.controller;
import application.SceneLoader;
import application.handler.OrderHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
/**
 *
 * @author SE-lnwza
 */
public class PurchaseCompleteController {

    @FXML
    private Label lb_orderno;

    @FXML
    private Button bt_ok;
    
    @FXML
    protected void initialize() {
        lb_orderno.setText(OrderHandler.getLastId().toString());
    }

    @FXML
    void ok(ActionEvent event) {
        SceneLoader.setPCBody("PurchaseHome");
    }
    
}
