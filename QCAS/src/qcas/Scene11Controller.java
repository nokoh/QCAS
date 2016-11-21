package qcas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Shay 
 * 
 *                  
 *               Scene 11 // Multiple Answer Screen // Student Use Only 
 * 
 *          One of the Four Types of Question Screens for the Quiz
 * 
 */
public class Scene11Controller implements Initializable {

    Pane question = new Pane();
    
    Scene scene;
    
    @FXML 
    private Button AButton;
    @FXML 
    private Button BButton;
    @FXML 
    private Button CButton;
    @FXML 
    private Button DButton;
    @FXML 
    private Button nextButton;
    
    @FXML 
    private Label MAQuestionDescriptionLabel; 
    
    @FXML 
    private Label MAOptionALabel; 
    @FXML 
    private Label MAOptionBLabel; 
    @FXML 
    private Label MAOptionCLabel; 
    @FXML 
    private Label MAOptionDLabel;  
    
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