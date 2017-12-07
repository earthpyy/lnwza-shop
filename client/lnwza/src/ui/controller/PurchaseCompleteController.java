package ui.controller;
import application.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
/**
 *
 * @author SE-lnwza
 */
public class PurchaseCompleteController extends Fillable<Long> {

    @FXML
    private Label lb_orderno;

    @FXML
    private Button bt_ok;
    
    @Override
    public void fill(Long id) {
        lb_orderno.setText(id.toString());
    }

    @FXML
    void ok(ActionEvent event) {
        SceneLoader.setPCBody("PurchaseHome");
    }
    
}
