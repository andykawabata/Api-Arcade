package controllers;

import apiarcade.RunApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DataStoreAdapter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import models.User;

/**
 *
 * @author RyanFromSchool
 */
public class ForgotPasswordController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField newPassword;
    @FXML
    private JFXTextField confirmPassword;
    @FXML
    private JFXButton _btnResetPassword;
    @FXML
    private Label _errorLabel;

    private RunApp run;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    @FXML
    private void _btnCancel(ActionEvent event) throws IOException {
        System.out.println("Returned to login view");
        RunApp.showLoginView();
    }

    //The reset password button
    @FXML
    private void _btnUpdateInfoAndReturn() throws IOException, Exception {
        //STILL NEEDS TO HANDLE USERNAME NOT EXISTING

        System.out.println("Reset Password clicked");
        String newPass = newPassword.getText();
        String newPassConfirm = confirmPassword.getText();
        String givenUsername = username.getText();

        if(!newPassConfirm.equals(newPass)){
            _errorLabel.setText("Passwords Dont Match");
            return;
        }

        //IF USER NOT IN DATABASE
        User user = User.loadByUsername(givenUsername);
        if(user == null){
            _errorLabel.setText("Username doesn't exist");
            return;
        }

        //IF INCORRECT PASSWORD
        if(!user.passwordMatches(newPass)){
            _errorLabel.setText("Incorrect Password");
            return;
        }
        String uuid = user.getUuid();
        Map<String, String> newPasswordMap = new HashMap<>();
        newPasswordMap.put("password", newPass);

        DataStoreAdapter.updateObject(newPasswordMap, uuid, User.TABLE);
        RunApp.showLoginView();
    }

        @FXML
    void _fieldConfPassword(KeyEvent event) {
        if (checkFieldsPopulated()){
            _btnResetPassword.setDisable(false);
        }
    }

    @FXML
    void _fieldPassword(KeyEvent event) {
        if (checkFieldsPopulated()){
            _btnResetPassword.setDisable(false);
        }
    }

    @FXML
    void _fieldUsername(KeyEvent event) {
        if (checkFieldsPopulated()){
            _btnResetPassword.setDisable(false);
        }
    }

    //returns true if all text fields have text in them
    boolean checkFieldsPopulated(){
        String [] fieldsPopulated = new String[3];
        fieldsPopulated[0] = username.getText();
        fieldsPopulated[1] = newPassword.getText();
        fieldsPopulated[2] = confirmPassword.getText();

        for (String i : fieldsPopulated)
            if (i == null || i.equals(""))
                return false;
        return true;
    }


}
