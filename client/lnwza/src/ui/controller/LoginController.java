package ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import application.SceneLoader;
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
    private Text txt_error;
    
    @FXML
    protected void initialize() {
        bt_login.disableProperty().bind(tf_username.textProperty().isEmpty().or(tf_password.textProperty().isEmpty()));
        bt_login.setDefaultButton(true);
        txt_error.setVisible(false);
    }
    
    @FXML
    void login(ActionEvent event) {
        User user = UserHandler.getUser(tf_username.getText().trim(), tf_password.getText().trim());
        if (user != null) {
            if (!user.isLoggedIn()) {
                UserHandler.logIn(user);
                if (UserHandler.getCurrentUser().isOwner()) {
                    UserHandler.updateLastLoggedIn(user.toOwner());
                }
                SceneLoader.loadMain();
//                txt_error.setVisible(false);
            } else {
                showLogged();
            }
        } else {
            showWrong();
        }
    }
    
    void showWrong() {
        txt_error.setVisible(true);
        txt_error.setText("Error : Wrong Username or Password!");
    }
    
    void showLogged() {
        txt_error.setVisible(true);
        txt_error.setText("Error : This account already logged in!");
    }

    @FXML
    void forget(MouseEvent event) {
        // TODO: next sprint
        // TODO: change it to button!
    }
    
}
