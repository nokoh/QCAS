package qcas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Shay
 * 
 * 
 *     Scene 7 // Select Desired Number of Questions Screen // For Student
 * 
 *       ** Student Can Choose to one of the 4 choices for # of Quiz Questions
 * 
 * 
 */
public class Scene7Controller implements Initializable {

    Scene scene; 
    
    @FXML 
    private Button eightQuetionsButton; 
    @FXML 
    private Button sixteenQuestionsButton;
    @FXML 
    private Button twentyfourQuestionsButton;
    @FXML 
    private Button thirtytwoQuestionsButton;
    
    
    
    
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