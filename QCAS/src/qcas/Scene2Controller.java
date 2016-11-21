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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Shay McDowell // Scene 2 // Difficulty Selection Screen
 * 
 */
public class Scene2Controller implements Initializable {

    Scene scene;
    Connection con;
    String fileName;
    
    @FXML
    private Button easyDifficulty;
    private Button mediumDifficulty;
    private Button hardDifficulty;
    private Button mixedDifficulty;
    private TextField noOfQuestions;
    private Label sessionName = new Label();
    
    LoginScreenController userSession = new LoginScreenController();

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
        
    }
    
    public void selectDifficulty(){
        
        int num;

        String easy = "";
        String medium = "medium";        
        String hard = "hard";
        String mixed = "mixed";
        String selection;
        
      //  Quiz newQuiz = new Quiz(num, );
        easyDifficulty.setOnAction(e -> {
            Stage screenStage = (Stage) easyDifficulty.getScene().getWindow();
            String title = screenStage.getTitle().toString().trim();
            System.out.println(title);
            
        });
        
    }
    
    public void generateQuiz(){
        
        
        
        
    }
}