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
 * @author Shay McDowell // Scene 6 // Teacher Report Scene
 */
public class Scene6Controller implements Initializable {

    
    Scene scene; 
    
    @FXML 
    private Button printToPDFButton;
    @FXML 
    private Button returnHomeButton; 
   
     /**
     * printToPDF() prints a PDF of the Report if the Print To PDF Button is clicked
     * 
     */
    public static void printToPDF(){
        
        
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