/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apiarcade;

import com.opencsv.CSVWriter;
import db.CSVConnector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;

import prototypes.HttpClientTest;
import prototypes.JsonJavaTest;
import prototypes.JsonJavaTest2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import models.User;

/**
 *
 * @author andyk
 */
public class App{
    
    public static void main(String[] args) throws IOException, InterruptedException, JSONException, Exception{
       
        //HttpClientTest.getHttpResponseAndPrint();
        //JsonJavaTest.getJokeCategoryAndPrint();
        //JsonJavaTest2.getTwopartJokesAndPrint();
        //RunLoginView.main(args);
        RunApp.main(args);
 
    }
    
}
