/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apiarcade;

import java.io.IOException;

import org.json.JSONException;

import prototypes.HttpClientTest;
import prototypes.JsonJavaTest;
import prototypes.JsonJavaTest2;
import loginview.LoginView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author andyk
 */
public class App{
    
    public static void main(String[] args) throws IOException, InterruptedException, JSONException{
       
        HttpClientTest.getHttpResponseAndPrint();
        JsonJavaTest.getJokeCategoryAndPrint();
        JsonJavaTest2.getTwopartJokesAndPrint();
    }
    
}
