package controllers;

/*
*Last updated on 11/21/20
*
*
*FXML Controller class
*
*Contributing authors
*@author Francisco
 */
import apiarcade.RunApp;
import factories.GameFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.LoginSession;
import models.User;

public class MainGameController implements Initializable {

    @FXML
    private Label userID;
    @FXML
    private Label highScore;

    private User user = LoginSession.currentUser;

    /**
     *
     * @param _url
     * @param _rb
     */
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
        // Works to display the username
        userID.setText("Hello! " + LoginSession.currentUser.getUsername());
    }

    @FXML
    private void _userLogOut(ActionEvent _event) throws IOException {
        System.out.println("User logged out");
        RunApp.showLoginView();
    }

    @FXML
    private void _gameOneStart(ActionEvent _event) throws IOException {
        GameFactory.setCurrentGame(GameFactory.GAME_ONE);
        RunApp.showGenericGame();
    }

    @FXML
    private void _gameTwoStart(ActionEvent _event) throws IOException {
        GameFactory.setCurrentGame(GameFactory.GAME_TWO);
        RunApp.showGenericGame();
    }

    @FXML
    void _leaderboardLink(ActionEvent _event) throws IOException {
        System.out.println("User went to leaderboards");
        RunApp.showLeaderboardsView();
    }
}
