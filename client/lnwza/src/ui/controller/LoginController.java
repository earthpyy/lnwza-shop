package ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import application.SceneLoader;
import application.Session;
import application.entity.User;
import application.handler.UserHandler;

/**
 *
 * @author SE-lnwza
 */
public class LoginController {
    
    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField tf_password;
    
    @FXML
    private Button bt_login;

    @FXML
    private Text bt_forget;
    
    @FXML
    protected void initialize() {
        bt_login.disableProperty().bind(tf_username.textProperty().isEmpty().or(tf_password.textProperty().isEmpty()));
        bt_login.setDefaultButton(true);
    }
    
    @FXML
    void login(ActionEvent event) {
        User user = UserHandler.getUser(tf_username.getText().trim(), tf_password.getText().trim());
        if (user != null) {
            Session.setCurrentUser(user);
            if (Session.isOwner()) {
                UserHandler.updateLoggedIn(user.toOwner());
            }
            SceneLoader.loadMain();
        } else {
            // TODO: show alert!
            System.out.println(user + "/" + tf_username.getText().trim() + "/" + tf_password.getText().trim());
        }
    }

    @FXML
    void forget(MouseEvent event) {
        // TODO: next sprint
        // TODO: change it to button!
    }
    
//    @FXML
//    void checkNull(KeyEvent event) {
//        if ((tf_username.getText().trim().isEmpty() || tf_password.getText().trim().isEmpty()) && !bt_login.isDisabled()) {
//            bt_login.setDisable(true);
//        } else if (!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty() && bt_login.isDisabled()) {
//            bt_login.setDisable(false);
//        }
//    }
    
}
