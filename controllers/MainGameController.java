/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class MainGameController implements Initializable {

    @FXML
    private Label userID;
    @FXML
    private Label highScore;

    private User user = LoginSession.currentUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Works to display the username but breaks guest login
        userID.setText("Hello! " + LoginSession.currentUser.getUsername());
    }

    @FXML

    private void _userLogOut(ActionEvent event) throws IOException {
        System.out.println("User logged out");
        RunApp.showLoginView();
    }

    @FXML
    private void _gameOneStart(ActionEvent event) throws IOException {
        GameFactory.setCurrentGame(GameFactory.GAME_ONE);
        RunApp.showgenericGame();
    }

    @FXML
    private void _gameTwoStart(ActionEvent event) throws IOException {
        GameFactory.setCurrentGame(GameFactory.GAME_TWO);
        RunApp.showgenericGame();
    }


    @FXML
    void _leaderboardLink(ActionEvent event) throws IOException {
        System.out.println("User went to leaderboards");
        RunApp.showLeaderboardsView();
    }
}
