
package qcas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * 
 * FXML Controller class
 *@author Shay
 * 
 * 
 * 
 *      Scene 5 // Return Home Screen After File is Uploaded by Teacher
 * 
 *              // Dummy Scene only for Teacher - Returns to Login Screen
 * 
 * 
 */
public class Scene5Controller implements Initializable {

    Scene scene;
    
    @FXML
    private Button returnHomeButton;  
    
    
    /**
     * 
     * Return Home Method returns to Login Screen when the Return Home Button 
     * is pressed by the user
     * 
     */
    public void returnHome(){
        
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