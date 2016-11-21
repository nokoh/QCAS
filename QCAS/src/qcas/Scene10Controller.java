package qcas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 * @author Shay 
 * 
 * 
 *          // Scene 10 // Fill in the Blank Scene // For Student Only 
 * 
 *          One of the Question Type Screens for the Quiz
 * 
 */
public class Scene10Controller implements Initializable {

    
    Scene scene; 
    
    @FXML 
    private Button nextButton;
    @FXML 
    private TextField userAnswerField;
    @FXML
    private Label FIBQuestionDescriptionLabel;
    
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