package ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import application.SceneLoader;
import application.entity.Order;
import application.handler.OrderHandler;

/**
 *
 * @author SE-lnwza
 */

public class OrderUpdateController {

    @FXML
    private TextField tf_orderno;

    @FXML
    private TextField tf_trackno;

    @FXML
    private TextField tf_date;
    
//    @FXML
//    private TextField tf_time;

    @FXML
    private TextField tf_status;

    @FXML
    private Button bt_confirm;
    
    private Order order;
    
    void fill(Order od) {
        set(od);
        tf_orderno.setText(order.getId().toString());
        tf_trackno.setText(order.getTrackNo());
        // TODO: add time textfield
        tf_date.setText(order.getOrderDate());
//        tf_time.setText(order.getOrderTime());
        tf_status.setText(order.getLastStatusName());
    }
    
    @FXML
    void update() {
        order.addNextStatus();
        OrderHandler.update(order);
        SceneLoader.closePopup();
        SceneLoader.setBody("OrderView");
    }
    
    void set(Order od) {
        order = od;
    }

}