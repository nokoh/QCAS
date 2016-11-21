/*
 * Login Screen - Scene 1 // Login Screen // Home // 
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
 * @author Shay
 * 
 *          Home Screen  //  Login Screen - Scene 1 // For Teacher and Student
 */
public class LoginScreenController implements Initializable {
    
    Scene scene;
    
    @FXML
    private Button login; 
    
    @FXML 
    private TextField userIDField;
    @FXML
    private PasswordField passwordField;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void verifyLogin(){
        
        
        Stage primaryStage = (Stage)login.getScene().getWindow();
        login.setOnAction(e -> {
            
            String username;
            String password;
            
            username = userIDField.getText();
            password = passwordField.getText();
                
          
        }); 
    }
}