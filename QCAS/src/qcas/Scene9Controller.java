package qcas;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Shay
 * 
 * 
 *       Scene 9 // Multiple Choice Questions Screen // Question Type *** MC***
 * 
 *          1 of 4 Screens Used for Quiz // Student Use Only
 */
public class Scene9Controller implements Initializable {
    
    Scene scene; 
    
    @FXML 
    private Label MCQuestionDescriptionLable;
    
    
    @FXML 
    private Button AButton;
    @FXML 
    private Button BButton;
    @FXML 
    private Button CButton;
    @FXML 
    private Button DButton;
    
    
    
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
     MCQuestionDescriptionLable.setText("Baloon");
       
    }    
}