package qcas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Shay McDowell
 * 
 * 
 * 
 *          // Scene 13 // Student Report // Student Only 
 * 
 *    Report of Student Grades // Can Print to PDF or Return to Login Screen
 * 
 * 
 */
public class Scene13Controller implements Initializable {

    Scene scene; 
    
    @FXML 
    private Button printToPDFButton;
    @FXML 
    private Button returnHomeButton;
    
    @FXML 
    private BarChart barChartStudent;
    
    @FXML 
    private Label numberCorrectLabel;
    @FXML 
    private Label numberIncorrectLabel;
    
    
    
    
    
    
    /**
     * Initializes the controller class.
     * 
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }     
}