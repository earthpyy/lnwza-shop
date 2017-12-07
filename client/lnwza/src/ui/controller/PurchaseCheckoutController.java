package ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import application.Bag;
import application.payment.CreditPayment;
import application.payment.InternetPayment;
import application.payment.Payment;
import application.SceneLoader;
import application.entity.ImageButton;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author SE-lnwza
 */
public class PurchaseCheckoutController extends Fillable<Double> {
    
    @FXML
    private TextField tf_qty;

    @FXML
    private TextField tf_total;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private AnchorPane anchorPaneCredit;
    
    @FXML
    private AnchorPane anchorPaneeInternet;
    
    private double total;
    
    @FXML
    protected void initialize() {
        ImageButton bt_credit = new ImageButton("/ui/resources/images/button/ButtonCredit.png");
        bt_credit.setOnAction((event) -> {
        payCredit();
      });
        anchorPaneCredit.getChildren().add(bt_credit);
        
        ImageButton bt_internet = new ImageButton("/ui/resources/images/button/ButtonInternet.png");
        bt_internet.setOnAction((event) -> {
        payInternet();
      });
        anchorPaneeInternet.getChildren().add(bt_internet);
        
        ImageButton bt_cancel = new ImageButton("/ui/resources/images/button/ButtonCancel.png");
        bt_cancel.setOnAction((event) -> {
        cancel();
      });
        anchorPane.getChildren().add(bt_cancel);
        
    }
    
    @Override
    void fill(Double total) {
        this.total = total;
        tf_qty.setText(Bag.getInstance().getTotalAmount().toString());
        tf_total.setText("$" + total.toString());
    }

    
    void cancel() {
        SceneLoader.setPCBody("PurchaseBag");
    }

    void payCredit() {
        Payment payment = new CreditPayment(Bag.getInstance(), total);
        payment.pay();
    }

    void payInternet() {
        Payment payment = new InternetPayment(Bag.getInstance(), total);
        payment.pay();
    }
    
//    void toggleButton() {
//        boolean value = !bt_credit.disableProperty().get();
//        bt_credit.setDisable(value);
//        bt_internet.setDisable(value);
//        bt_cancel.setDisable(value);
//    }
    
}
