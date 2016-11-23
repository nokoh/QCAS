package qcas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Shay McDowell // Scene 6 // Teacher Report Scene
 */
public class Scene6Controller implements Initializable {

    
    Scene scene; 
    Stage homeStage;
    
    @FXML 
    private Button printToPDFButton;
    @FXML 
    private Button returnHomeButton; 
   
     /**
     * printToPDF() prints a PDF of the Report if the Print To PDF Button is clicked
     * 
     */
//    
//    
//        @FXML
//    public  void printToPDF(){
//        PrinterJob printToPDF = PrinterJob.createPrinterJob();
//             if(printToPDF != null){
//                printToPDF.showPrintDialog(scene.getWindow()); // Window must be your main Stage
//                printToPDF.printPage(homeStage);
//                printToPDF.endJob();
 

        
    
    /**
        *  The returnHome() method switches back to the login screen.
        * 
     * @throws java.io.IOException
        **/
            @FXML
        public void returnHome() throws IOException{
            returnHomeButton.setOnAction(h ->{
                
                try {
                    returnHomeButtonClicked();
                } catch (IOException ex) {
                    Logger.getLogger(Scene6Controller.class.getName()).log(Level.SEVERE, null, ex);
                }                 
            } ); {
         }       
        }
     /**
     * 
     * @throws java.io.IOException
      */
            @FXML
        public void returnHomeButtonClicked() throws IOException{
            FXMLLoader f = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
                Parent scene6 = f.load();
                LoginScreenController ls = f.<LoginScreenController>getController();
                
                Scene LoginScreen = new Scene(scene6);
                homeStage = (Stage) returnHomeButton.getScene().getWindow();
                homeStage.hide();
                homeStage.setScene(LoginScreen);
                homeStage.show();
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