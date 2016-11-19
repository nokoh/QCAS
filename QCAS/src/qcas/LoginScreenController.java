/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qcas;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Shay
 */
public class LoginScreenController implements Initializable {
    
    Scene scene;
    
    @FXML
    private Button teacherLogin; 
    
    @FXML 
    private TextField userIDField;
    @FXML
    private PasswordField passwordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void verifyLogin(){
        
        
        Stage primaryStage = (Stage)teacherLogin.getScene().getWindow();
        teacherLogin.setOnAction(e -> {
            
            String username;
            String password;
            
            username = userIDField.getText();
            password = passwordField.getText();
            
            
            
        });
        
        
    }
    
}
