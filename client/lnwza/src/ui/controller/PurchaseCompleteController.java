package ui.controller;
import application.SceneLoader;
import application.entity.ImageButton;
import application.handler.OrderHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
/**
 *
 * @author SE-lnwza
 */
public class PurchaseCompleteController {

    @FXML
    private Label lb_orderno;
    
    @FXML
    private AnchorPane anchorpane;
    
    //@FXML
    //private Button bt_ok;
    
    @FXML
    protected void initialize() {
        ImageButton bt_ok = new ImageButton("/ui/resources/images/button/ButtonOK.png");
        bt_ok.setOnAction((event) -> {
            ok();
        });
        anchorpane.getChildren().add(bt_ok);
        
        lb_orderno.setText(OrderHandler.getLastId().toString());
    }

    
    void ok() {
        SceneLoader.setPCBody("PurchaseHome");
    }
    
}
