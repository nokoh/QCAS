/*
 * Login Screen - Scene 1 // Login Screen // Home // 
 */
package qcas;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * FXML Controller class
 * @author Shay
 * 
 *          Home Screen  //  Login Screen - Scene 1 // For Teacher and Student
 */
public class LoginScreenController implements Initializable {

    Connection connection;
    Scene scene;

   
    @FXML 
    private Button teacherLogin;
        
   
    private TextField userIDField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Text loginMessage = new Text();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    }
