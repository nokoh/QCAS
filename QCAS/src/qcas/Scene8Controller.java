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
 *     Scene 8 // Click Begin Quiz to Start Screen // For Student Only
 * 
 *          // Dummy Scene that starts the quiz when button is pressed. 
 */
public class Scene8Controller implements Initializable {
    
    Scene scene; 
    
    @FXML 
    private Button beginQuizButton;
    
    /**
     * 
     * The beginQuiz() method simply begins quiz once button is pressed 
     */
    
    public void beginQuiz(){
        
        
    }

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