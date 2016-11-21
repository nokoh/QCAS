package qcas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
/**
 * FXML Controller class
 *
 * @author Shay
 * 
 *      //Scene 12 // True False Question Scene // Student Only 
 * 
 *          One of Four Question Type Scenes for the Quiz Program 
 * 
 */
public class Scene12Controller implements Initializable {

    Scene scene;
    
    @FXML 
    private Button trueButton;
    @FXML 
    private Button falseButton;
    @FXML 
    private Button nextButton;
   
    @FXML 
    private Label TFStatementLabel;  
    
    @FXML 
    private Pagination pagination;
    
    
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }     
}