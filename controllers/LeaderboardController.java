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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.LoginSession;
import models.Score;
import models.User;

public class LeaderboardController  implements Initializable {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private JFXButton _btnPlayNow;
    @FXML
    private Label userID;
    @FXML
    private Label currentGame1Score;
    @FXML
    private Label currentGame2Score;

    private User user = LoginSession.currentUser;
    private Score userScore;
    private final int game1ID = 1;
    private final int game2ID = 2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userID.setText("Hello! " + user.getUsername());
        try {
            currentGame1Score.setText("Your Score: " + Score.getScore(user.getUuid(), game1ID));
        } catch (Exception ex) {
            Logger.getLogger(LeaderboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentGame2Score.setText("Your Score: " + 0);
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
