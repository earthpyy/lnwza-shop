package ui.controller;

import application.Bag;
import application.entity.CreditPayment;
import application.entity.InternetPayment;
import application.entity.Payment;
import application.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
/**
 *
 * @author SE-lnwza
 */
public class PurchaseCheckoutController {
    
    @FXML
    private TextField tf_qty;

    @FXML
    private TextField tf_total;

    @FXML
    private Button bt_cancel;

    @FXML
    private Button bt_credit;

    @FXML
    private Button bt_internet;
    
    private double total;
    
    @FXML
    protected void initialize() {
        // do something
    }
    
    void fill(Integer quantity, Double total) {
        this.total = total;
        tf_qty.setText(quantity.toString());
        tf_total.setText("฿" + total.toString());
    }

    @FXML
    void cancel(ActionEvent event) {
        SceneLoader.setPCBody("PurchaseBag");
    }

    @FXML
    void pay(ActionEvent event) {
        String method = (String) ((Button) event.getSource()).getUserData();
        // TODO: make it dynamically
        Payment payment = null;
        if (method.equals("credit")) {
            payment = new CreditPayment(Payment.SHOP_ID, total);
        } else if (method.equals("internet")) {
            payment = new InternetPayment(Payment.SHOP_ID, total);
        }
        
        toggleButton();
        Bag.setPayment(payment);
        Bag.getPayment().pay();
    }
    
    void toggleButton() {
        boolean value = !bt_credit.disableProperty().get();
        bt_credit.setDisable(value);
        bt_internet.setDisable(value);
        bt_cancel.setDisable(value);
        // TODO: disable stage instead
    }
    
}
