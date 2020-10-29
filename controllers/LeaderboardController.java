package controllers;
/*
*Last updated on 10/29/20
*
*Controls the links and buttons of leaderboards fxml file
*Yes there are two buttons to play game for the user's convenience
*
*Contributing authors
*@author Ryan
*/
import apiarcade.RunApp;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import models.LoginSession;
import models.User;

public class LeaderboardController  implements Initializable {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private JFXButton _btnPlayNow;
    private User user = LoginSession.currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    void _mainGamesLink(ActionEvent event) throws IOException {
        _mainGamesPlayNow(event);
    }

    @FXML
    void _mainGamesPlayNow(ActionEvent event) throws IOException {
        RunApp.showMainGameView();
    }

    @FXML
    void _userLogOut(ActionEvent event) throws IOException {
        System.out.println("User logged out");
        RunApp.showLoginView();
    }

    @FXML
    void _viewProfile(ActionEvent event) throws IOException {
        System.out.println("Move to profile view");
        //RunApp.showProfileView();
    }


}
