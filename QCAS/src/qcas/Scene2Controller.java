package qcas;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Shay 
 * 
 *             // Scene 2 // Difficulty Selection Screen // For Student
 * 
 */
public class Scene2Controller implements Initializable {

    Scene scene;
    Connection con;
    String fileName;
    
    @FXML
    private Button easyButton;
    @FXML
    private Button mediumButton;
    @FXML
    private Button hardButton;
    @FXML
    private Button mixedButton;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }
    
    public void selectDifficulty(){
        
        int num;

        String easy = "";
        String medium = "medium";        
        String hard = "hard";
        String mixed = "mixed";
        String selection;
        
      //  Quiz newQuiz = new Quiz(num, );
        easyButton.setOnAction(e -> {
               
        });  
    }
    
    public void generateQuiz(){
        
           
    }
}